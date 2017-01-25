package event;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import exception.ETRecordingException;
import generic.ETChronologicCollection;

public class ETEventRecorder {
	
	private ETEventReceiver receiver;
	private ExecutorService executor;
	private ETChronologicCollection<ETEvent> accumulatorCollection;
	private Future<?> pollProcess;
	
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
					accumulatorCollection.add(ev);
				}
			}
			System.out.println("Finished.");
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
					accumulatorCollection.add(ev);
				}
				try {
					Thread.sleep(pollrate);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			System.out.println("Finished2.");
		}
		
	}
	
	public ETEventRecorder(ETEventReceiver receiver) {
		this.receiver = receiver;
		this.executor = Executors.newSingleThreadExecutor();
	}
	
	public void startRecording() {
		if(pollProcess != null && !pollProcess.isDone()) {
			throw new ETRecordingException("Can not start new recording while previous recording is still running.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		pollProcess = executor.submit(new ETEventRecordingRunnable(receiver, accumulatorCollection));
	}
	
	public void startRecording(long pollrate) {
		if(pollProcess != null && !pollProcess.isDone()) {
			throw new ETRecordingException("Can not start new recording while previous recording has not finished.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		pollProcess = executor.submit(new ETEventFixedPollrateRecordingRunnable(receiver, accumulatorCollection, pollrate));
	}
	
	public void stopRecording() {
		pollProcess.cancel(true);
	}
	
	public ETChronologicCollection<ETEvent> getRecordedEvents() {
		return getRecordedEvents(2000);
	}
	
	public ETChronologicCollection<ETEvent> getRecordedEvents(long timeout) {
		try {
			pollProcess.get(timeout, TimeUnit.MILLISECONDS);
		} catch (CancellationException e) {
			// This is expected, the thread will cleanup itself
		} catch (TimeoutException e) {
			throw new ETRecordingException("A timeout occurred while waiting for the recording thread to return.", e);
		} catch (ExecutionException e) {
			throw new ETRecordingException("An error occurred while polling events to record.", e);
		} catch (InterruptedException e) {
			throw new ETRecordingException("Got interrupted while waiting for the recording thread to return.", e);
		}
		
		return accumulatorCollection;
	}
	
}
