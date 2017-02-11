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
		
		public ETRecordingRunnable(ETReceiver<E> receiver,
								   ETChronologicCollection<E> accumulatorCollection)
		{
			this.receiver = receiver;
			this.accumulatorCollection = accumulatorCollection;
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				ETResponse<E> response = receiver.getNext();
				if(response.getType() == ETResponseType.NEW_DATA) {
					synchronized (accumulatorCollection) { accumulatorCollection.add(response.getData()); }
				}
			}
		}
		
	}
	
	private class ETFixedPollrateRecordingRunnable implements Runnable {
		private ETReceiver<E> receiver;
		private ETChronologicCollection<E> accumulatorCollection;
		private long pollrate;
		
		public ETFixedPollrateRecordingRunnable(ETReceiver<E> receiver,
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
				if(response.getType() == ETResponseType.NEW_DATA) {
					synchronized (accumulatorCollection) { accumulatorCollection.add(response.getData()); }
				}
				try {
					Thread.sleep(pollrate);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		
	}
	
	public ETRecorder(ETReceiver<E> receiver) {
		this.receiver = receiver;
	}
	
	public void startRecording() {
		if(recordingThread != null && recordingThread.getState() != Thread.State.TERMINATED) {
			throw new ETRecordingException("Can not start new recording while previous recording is still running.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETRecordingRunnable(receiver, accumulatorCollection));
		recordingThread.start();
	}
	
	public void startRecording(long pollrate) {
		if(recordingThread != null && recordingThread.getState() != Thread.State.TERMINATED) {
			throw new ETRecordingException("Can not start new recording while previous recording has not finished.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETFixedPollrateRecordingRunnable(receiver, accumulatorCollection, pollrate));
		recordingThread.start();
	}
	
	public void stopRecording() {
		recordingThread.interrupt();
	}
	
	public ETChronologicCollection<E> getRecordedData() {
		return getRecordedData(2000);
	}
	
	public ETChronologicCollection<E> getRecordedData(long timeout) {
		try
		{
			recordingThread.join(timeout);
			if(recordingThread.isAlive()) {
				throw new ETRecordingException("Timeout while waiting for the recording thread to return.");
			}
		}
		catch (InterruptedException e)
		{
			throw new ETRecordingException("Got interrupted while waiting for the recording thread to return.", e);
		}
		
		synchronized(accumulatorCollection) { return accumulatorCollection; }
	}
	
}
