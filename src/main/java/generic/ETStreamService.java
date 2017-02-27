package generic;

import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import exception.ETRecordingException;
import exception.ETStreamException;
import io.reactivex.Flowable;

public class ETStreamService<E extends ChronologicComparable<E>> {
	
	private ETReceiver<E> receiver;
	private FlowableProcessor<E> publisher;
	private Thread streamThread;
	
	private class ETStreamRunnable implements Runnable {
		private ETReceiver<E> receiver;
		private FlowableProcessor<E> publisher;
		private long pollrate;
		
		public ETStreamRunnable(ETReceiver<E> receiver, FlowableProcessor<E> publishSubject, long pollrate) {
			this.receiver = receiver;
			this.publisher = publishSubject;
			this.pollrate = pollrate;
		}

		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				ETResponse<E> response = receiver.getNext();
				
				switch(response.getType()) {
				case NEW_DATA:
					publisher.onNext(response.getData());
					break;
				case NO_NEW_DATA_AVAILABLE:
					break;
				case SOURCE_DEPLETED:
					publisher.onComplete();
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
	
	public ETStreamService(ETReceiver<E> receiver) {
		this.receiver = receiver;
		
		PublishProcessor<E> publishProcessor = PublishProcessor.create();
		this.publisher = publishProcessor.toSerialized();
	}
	
	public void start() {
		stopRecordingThread();
		streamThread = new Thread(new ETStreamRunnable(receiver, publisher, 0));
		streamThread.start();
	}
	
	public void start(long pollrate) {
		stopRecordingThread();
		streamThread = new Thread(new ETStreamRunnable(receiver, publisher, pollrate));
		streamThread.start();
	}
	
	public void stop() {
		stopRecordingThread();
	}
	
	public void shutdown() {
		stopRecordingThread();
		publisher.onComplete();
	}
	
	public Flowable<E> getStream() {
		return publisher;
	}
	
	private void stopRecordingThread() {
		if(streamThread == null) {
			return;
		}
		
		try
		{
			streamThread.interrupt();
			streamThread.join(2000);
			if(streamThread.isAlive()) {
				throw new ETStreamException("Timeout while waiting for the polling thread to return.");
			}
		}
		catch (InterruptedException e)
		{
			throw new ETStreamException("Got interrupted while waiting for the polling thread to return.", e);
		}
	}

}
