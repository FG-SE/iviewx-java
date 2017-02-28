package generic;

import exception.ETRecordingException;
import generic.ETChronologicCollection;

public class ETRecorder<E extends ChronologicComparable<E>> {
	
	private ETReceiver<E> receiver;
	private ETChronologicCollection<E> accumulatorCollection;
	private Thread recordingThread;
	
	private class ETRecordingRunnable implements Runnable {	
		private ETReceiver<E> receiver;
		private ETChronologicCollection<E> accumulatorCollection;
		private long pollrate;
		
		public ETRecordingRunnable(ETReceiver<E> receiver,
								   ETChronologicCollection<E> accumulatorCollection,
								   long pollrate)
		{
			this.receiver = receiver;
			this.accumulatorCollection = accumulatorCollection;
			this.pollrate = pollrate;
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				ETResponse<E> response = receiver.getNext();
				
				switch(response.getType()) {
				case NEW_DATA:
					synchronized (accumulatorCollection) { accumulatorCollection.add(response.getData()); }
					break;
				case NO_NEW_DATA_AVAILABLE:
					break;
				case SOURCE_DEPLETED:
					Thread.currentThread().interrupt();
					break;
				}
				
				if(pollrate > 0) {
					try {
						Thread.sleep(pollrate);
					} catch (InterruptedException e) {
						Thread.currentThread().interrupt();
					}
				}
			}
		}
		
	}
	
	public ETRecorder(ETReceiver<E> receiver) {
		this.receiver = receiver;
	}
	
	public void startRecording() {
		if(recordingThread != null && recordingThread.isAlive()) {
			throw new ETRecordingException("Can not start new recording while previous recording is still running.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETRecordingRunnable(receiver, accumulatorCollection, 0));
		recordingThread.start();
	}
	
	public void startRecording(long pollrate) {
		if(recordingThread != null && recordingThread.isAlive()) {
			throw new ETRecordingException("Can not start new recording while previous recording has not finished.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETRecordingRunnable(receiver, accumulatorCollection, pollrate));
		recordingThread.start();
	}
	
	public void stopRecording() {
		stopRecordingThread();
	}
	
	public boolean isRunning() {
		if(recordingThread == null) {
			return false;
		}
		else {
			return recordingThread.isAlive();
		}
	}
	
	public ETChronologicCollection<E> getRecordedData() {
		return getRecordedData(2000);
	}
	
	public ETChronologicCollection<E> getRecordedData(long timeout) {
		stopRecordingThread();
		
		synchronized(accumulatorCollection) { return accumulatorCollection; }
	}
	
	private void stopRecordingThread() {
		if(recordingThread == null) {
			return;
		}
		
		try
		{
			recordingThread.interrupt();
			recordingThread.join(2000);
			if(recordingThread.isAlive()) {
				throw new ETRecordingException("Timeout while waiting for the recording thread to return.");
			}
		}
		catch (InterruptedException e)
		{
			throw new ETRecordingException("Got interrupted while waiting for the recording thread to return.", e);
		}
	}
	
}
