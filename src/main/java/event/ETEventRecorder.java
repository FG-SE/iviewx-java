package event;

import exception.ETRecordingException;
import generic.ETChronologicCollection;

public class ETEventRecorder {
	
	private ETEventReceiver receiver;
	private ETChronologicCollection<ETEvent> accumulatorCollection;
	private Thread recordingThread;
	
	private class ETEventRecordingRunnable implements Runnable {	
		private ETEventReceiver receiver;
		private ETChronologicCollection<ETEvent> accumulatorCollection;
		
		public ETEventRecordingRunnable(ETEventReceiver receiver,
										ETChronologicCollection<ETEvent> accumulatorCollection)
		{
			this.receiver = receiver;
			this.accumulatorCollection = accumulatorCollection;
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted() && receiver.hasNext()) {
				ETEvent ev = receiver.next();
				if(ev != null) {
					synchronized (accumulatorCollection) { accumulatorCollection.add(ev); }
				}
			}
		}
		
	}
	
	private class ETEventFixedPollrateRecordingRunnable implements Runnable {
		private ETEventReceiver receiver;
		private ETChronologicCollection<ETEvent> accumulatorCollection;
		private long pollrate;
		
		public ETEventFixedPollrateRecordingRunnable(ETEventReceiver receiver,
													 ETChronologicCollection<ETEvent> accumulatorCollection,
													 long pollrate)
		{
			this.receiver = receiver;
			this.accumulatorCollection = accumulatorCollection;
			this.pollrate = pollrate;
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted() && receiver.hasNext()) {
				ETEvent ev = receiver.next();
				if(ev != null) {
					synchronized (accumulatorCollection) { accumulatorCollection.add(ev); }
				}
				try {
					Thread.sleep(pollrate);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
		
	}
	
	public ETEventRecorder(ETEventReceiver receiver) {
		this.receiver = receiver;
	}
	
	public void startRecording() {
		if(recordingThread != null && recordingThread.getState() != Thread.State.TERMINATED) {
			throw new ETRecordingException("Can not start new recording while previous recording is still running.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETEventRecordingRunnable(receiver, accumulatorCollection));
		recordingThread.start();
	}
	
	public void startRecording(long pollrate) {
		if(recordingThread != null && recordingThread.getState() != Thread.State.TERMINATED) {
			throw new ETRecordingException("Can not start new recording while previous recording has not finished.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETEventFixedPollrateRecordingRunnable(receiver, accumulatorCollection, pollrate));
		recordingThread.start();
	}
	
	public void stopRecording() {
		recordingThread.interrupt();
	}
	
	public ETChronologicCollection<ETEvent> getRecordedEvents() {
		return getRecordedEvents(2000);
	}
	
	public ETChronologicCollection<ETEvent> getRecordedEvents(long timeout) {
		try {
			recordingThread.join();
		} catch (InterruptedException e) {
			throw new ETRecordingException("Got interrupted while waiting for the recording thread to return.", e);
		}
		
		synchronized(accumulatorCollection) { return accumulatorCollection; }
	}
	
}
