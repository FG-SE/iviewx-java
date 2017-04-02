package core.reactive;

import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import core.chronologic.ChronologicComparable;
import core.receiver.ETReceiver;
import core.receiver.response.ETResponse;
import exception.ETStreamException;
import io.reactivex.Flowable;

/** Asynchronously running service that publishes data
 *  in a reactive stream.
 *  <p>
 *  This service provides a powerful tool to work
 *  with data from a {@link core.receiver.ETReceiver} by leveraging
 *  the RxJava 2 library.
 *  <p>
 *  To use this service, simply {@link #start() start} it and
 *  subscribe to the reactive stream returned by
 *  {@link #getStream() getStream}.
 *  <p>
 *  If you want to stop the service, call {@link #stop() stop}.
 *  <p>
 *  The service runs asynchronously, so by default, subscriptions
 *  will subscribe on the service thread. It is advised to use
 *  <em>observeOn</em> in combination with a computational scheduler
 *  to make sure you don't block the service with your computations.
 *  Also you will have to keep backpressure in mind, since data
 *  might be produced at a very rapid rate. RxJava provides backpressure
 *  mechanisms (<em>onBackpressureLatest</em> is a great way to handle
 *  eyetracking data streams) to handle these kinds of situations.
 *  
 *  @see <a href="https://github.com/ReactiveX/RxJava">RxJava</a>
 * 
 *  @author Luca Fuelbier
 */
public class ETStreamService<E extends ChronologicComparable<E>> {
	
	private ETReceiver<E> receiver;
	private FlowableProcessor<E> publisher;
	private Thread streamThread;
	
	/** Runnable that manages the publishing of data on the stream. */
	private class ETStreamRunnable implements Runnable {
		private ETReceiver<E> receiver;
		private FlowableProcessor<E> publisher;
		private long pollrate;
		
		/** Constructs a new ETStreamRunnable with the given receiver,
		 *  publisher and pollrate.
		 *  <p>
		 *  The publisher is the FlowableProcessor used to publish
		 *  data to the stream.
		 *  <p>
		 *  The pollrate is the delay (in milliseconds) between each
		 *  request to the receiver. A pollrate of 0 will record stream
		 *  data as fast as possible.
		 * 
		 * 
		 *  @param receiver Data Receiver
		 *  @param publisher Publisher for the stream
		 *  @param pollrate Pollrate [ms]
		 */
		public ETStreamRunnable(ETReceiver<E> receiver, FlowableProcessor<E> publisher, long pollrate) {
			this.receiver = receiver;
			this.publisher = publisher;
			this.pollrate = pollrate;
		}

		/** Runs the stream service, publishing data to the stream.
		 *  <p>
		 *  Only new data will be published to the stream.
		 *  If the source is depleted, or the current thread is
		 *  interrupted, the stream will finish (calls <em>onComplete</em>).
		 */
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
	
	/** Constructs a new ETStreamService with the given receiver.
	 * 
	 *  @param receiver Data receiver
	 */
	public ETStreamService(ETReceiver<E> receiver) {
		this.receiver = receiver;
		
		PublishProcessor<E> publishProcessor = PublishProcessor.create();
		this.publisher = publishProcessor.toSerialized();
	}
	
	/** Starts the stream service.
	 *  <p>
	 *  The service will stream data as fast as possible.
	 *  Beware that this will use a lot of processing power.
	 */
	public void start() {
		if(streamThread != null && streamThread.isAlive()) {
			return;
		}
		
		streamThread = new Thread(new ETStreamRunnable(receiver, publisher, 0));
		streamThread.start();
	}
	
	/** Starts the stream service with the given pollrate.
	 *  <p>
	 *  The service will pause for the specified time
	 *  between each request to the receiver. This will
	 *  reduce the stress on your CPU for sources, that
	 *  often respond with {@link core.receiver.response.ETResponseType#NO_NEW_DATA_AVAILABLE}
	 *  when requesting new data without a time delay.
	 * 
	 *  @param pollrate Time delay between each request to the receiver
	 */
	public void start(long pollrate) {
		if(streamThread != null && streamThread.isAlive()) {
			return;
		}
		
		streamThread = new Thread(new ETStreamRunnable(receiver, publisher, pollrate));
		streamThread.start();
	}
	
	/** Stops the service.
	 *  
	 *  @throws exception.ETStreamException if a timeout occurs when stopping the service
	 */
	public void stop() {
		stopStreamThread();
	}
	
	/** Stops the service and publishes <em>onComplete</em>
	 *  to the stream.
	 * 
	 *  @throws exception.ETStreamException if a timeout occurs when stopping the service
	 */
	public void shutdown() {
		stopStreamThread();
		publisher.onComplete();
	}
	
	/** Returns if the service is currently running.
	 * 
	 *  @return <em>true</em>, if the service is running, else <em>false</em>
	 */
	public boolean isRunning() {
		if(streamThread == null) {
			return false;
		}
		else {
			return streamThread.isAlive();
		}
	}
	
	/** Returns the data stream.
	 *  <p>
	 *  The returned Flowable is part of the RxJava 2
	 *  library and provides the entry point for the
	 *  whole functionality of the reactive framework.
	 * 
	 *  @return Data stream
	 */
	public Flowable<E> getStream() {
		return publisher;
	}
	
	/** Stops the service thread with a default timeout of 2 seconds.
	 *  
	 *  @throws exception.ETStreamException if a timeout occurs when stopping the service
	 */
	private void stopStreamThread() {
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
