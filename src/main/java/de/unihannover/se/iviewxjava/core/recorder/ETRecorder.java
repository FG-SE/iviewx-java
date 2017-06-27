package de.unihannover.se.iviewxjava.core.recorder;

import de.unihannover.se.iviewxjava.core.chronologic.ChronologicComparable;
import de.unihannover.se.iviewxjava.core.chronologic.ETChronologicCollection;
import de.unihannover.se.iviewxjava.core.receiver.ETReceiver;
import de.unihannover.se.iviewxjava.core.receiver.response.ETResponse;
import de.unihannover.se.iviewxjava.core.receiver.response.ETResponseType;
import de.unihannover.se.iviewxjava.exception.ETRecordingException;

/** Records data from a {@link ETReceiver}.
 *  The recording is run asynchronously in another thread.
 *  <p>
 *  Calling {@link #startRecording() startRecording} will start the
 *  recording. If no fixed pollrate is specified, the recorder will
 *  retrieve data as fast as possible from the receiver.
 *  <p>
 *  Calling {@link #stopRecording() stopRecording} will stop the
 *  currently running recording thread.
 *  <p>
 *  Calling {@link #getRecordedData() getRecordedData} will return the
 *  recorded data in a {@link ETChronologicCollection}. If the
 *  recording thread is still running, this will stop the thread before
 *  returning the data.
 *  <p>
 *  Only new data will be added to the recorded collection.
 *  If the source is depleted, {@link #stopRecording() stopRecording}
 *  will return all recorded data up until that point.
 * 
 *  @author Luca Fuelbier
 */
public class ETRecorder<E extends ChronologicComparable<E>> {
	
	private ETReceiver<E> receiver;
	private ETChronologicCollection<E> accumulatorCollection;
	private Thread recordingThread;
	
	/** Runnable that manages the data recording. */
	private class ETRecordingRunnable implements Runnable {	
		private ETReceiver<E> receiver;
		private ETChronologicCollection<E> accumulatorCollection;
		private long pollrate;
		
		/** Constructs a new ETRecordingRunnable with the given
		 *  receiver, accumulator collection and pollrate.
		 *  <p>
		 *  The accumulator collection will be used to store the
		 *  recorded data. The collection access is synchronized,
		 *  so the collection can be used by another thread, after
		 *  this Runnable has finished its work.
		 *  <p>
		 *  The pollrate is the delay (in milliseconds) between each
		 *  request to the receiver. A pollrate of 0 will record data
		 *  as fast as possible.
		 * 
		 *  @param receiver Data receiver
		 *  @param accumulatorCollection Accumulator collection
		 *  @param pollrate Pollrate [ms]
		 */
		public ETRecordingRunnable(ETReceiver<E> receiver,
								   ETChronologicCollection<E> accumulatorCollection,
								   long pollrate)
		{
			this.receiver = receiver;
			this.accumulatorCollection = accumulatorCollection;
			this.pollrate = pollrate;
		}

		/** Runs the recording.
		 *  <p>
		 *  Only new data will be added to the accumulator collection.
		 *  If the source is depleted, or the current thread is
		 *  interrupted, the recording will finish.
		 */
		@Override
		public void run() {
			while(!Thread.currentThread().isInterrupted()) {
				ETResponse<E> response;

				try {
					response = receiver.getNext();
				} catch (Exception e) {
					System.out.println("An exception has occurred in the recording thread:");
					e.printStackTrace();
					System.out.println("Stopping recording.");
					Thread.currentThread().interrupt();
					continue;
				}
				
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
	
	/** Constructs a new ETRecorder with the given receiver.
	 * 
	 *  @param receiver Data receiver
	 */
	public ETRecorder(ETReceiver<E> receiver) {
		this.receiver = receiver;
	}
	
	/** Starts the asynchronous recording.
	 *  <p>
	 *  The recorder will record as much data as possible.
	 *  Beware that this will use a lot of processing power.
	 *  
	 *  @throws ETRecordingException if there is already a recording thread running
	 */
	public void startRecording() {
		if(recordingThread != null && recordingThread.isAlive()) {
			throw new ETRecordingException("Can not start new recording while previous recording is still running.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETRecordingRunnable(receiver, accumulatorCollection, 0));
		recordingThread.start();
	}
	
	/** Start the asynchronous recording.
	 *  <p>
	 *  The recorder will pause for the specified time
	 *  between each request to the receiver. This will
	 *  reduce the stress on your CPU for sources, that
	 *  often respond with {@link ETResponseType#NO_NEW_DATA_AVAILABLE}
	 *  when requesting new data without a time delay.
	 * 
	 *  @param pollrate Time delay between each request to the receiver
	 *  
	 *  @throws ETRecordingException if there is already a recording thread running
	 */
	public void startRecording(long pollrate) {
		if(recordingThread != null && recordingThread.isAlive()) {
			throw new ETRecordingException("Can not start new recording while previous recording has not finished.");
		}
		
		accumulatorCollection = new ETChronologicCollection<>();
		recordingThread = new Thread(new ETRecordingRunnable(receiver, accumulatorCollection, pollrate));
		recordingThread.start();
	}
	
	/** Stops the recording.
	 *  <p>
	 *  The default timeout for this method is 2 seconds.
	 *  
	 *  @throws ETRecordingException if a timeout occurred
	 */
	public void stopRecording() {
		stopRecordingThread(2000);
	}
	
	/** Stops the recording with the given timeout.
	 * 
	 *  @param timeout Timeout [ms]
	 *  
	 *  @throws ETRecordingException if a timeout occurred
	 */
	public void stopRecording(long timeout) {
		stopRecordingThread(timeout);
	}
	
	/** Returns if the recorder is currently recording.
	 * 
	 *  @return <em>true</em>, if the recorder is recording, else <em>false</em>
	 */
	public boolean isRunning() {
		if(recordingThread == null) {
			return false;
		}
		else {
			return recordingThread.isAlive();
		}
	}
	
	/** Returns the recorded data.
	 *  <p>
	 *  The default timeout for this method is 2 seconds.
	 *  <p>
	 *  If the recording is still running, the recording will
	 *  be stopped before returning the data.
	 * 
	 *  @return Recorded data
	 *  
	 *  @throws ETRecordingException if a timeout occurred
	 */
	public ETChronologicCollection<E> getRecordedData() {
		return getRecordedData(2000);
	}
	
	/** Returns the recorded data with the given timeout.
	 *  <p>
	 *  If the recording is still running, the recording will
	 *  be stopped before returning the data.
	 *  
	 *  @param timeout Timeout [ms]
	 * 
	 *  @return Recorded data
	 *  
	 *  @throws ETRecordingException if a timeout occurred
	 */
	public ETChronologicCollection<E> getRecordedData(long timeout) {
		stopRecordingThread(timeout);
		
		synchronized(accumulatorCollection) { return accumulatorCollection; }
	}
	
	/** Stops the recording thread with the given timeout.
	 * 
	 *  @param timeout Timeout [ms]
	 *  
	 *  @throws ETRecordingException if a timeout occurred
	 */
	private void stopRecordingThread(long timeout) {
		if(recordingThread == null) {
			return;
		}
		
		try
		{
			recordingThread.interrupt();
			recordingThread.join(timeout);
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
