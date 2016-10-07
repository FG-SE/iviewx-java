package sample;

import java.util.Iterator;

/** Receives eyetracking samples from a ETSortedSampleList while simulating 
 *  a realtime eyetracker.
 *  <p>
 *  In contrast to the ETPlaybackSampleReceiver, this SampleReceiver returns 
 *  samples based on their actual timestamp. A internal timer is used to approximate 
 *  the elapsed time and return the appropriate eyetracking sample. If there are 
 *  timestamp-holes (no recorded data for a specific timestamp), the eyetracking with 
 *  the next-higher timestamp will be used until the timer has caught up.
 *  <p>
 *  This SampleReceiver has to be reset right before starting to poll eyetracking 
 *  samples. Resetting resets the internal timer, aswell as the list iteration, 
 *  so you can easily loop over a pre-recorded eyetracking sample data multiple times, 
 *  for example for live demonstrations.
 *  <p>
 *  Resetting this SampleReceiver and performing additional operations, before starting 
 *  to poll eyetracking samples can result in skipped samples. Make sure to call the 
 *  reset() method right before starting your poll-loop. This might change in a future 
 *  version of this library.
 * 
 *  @author Luca Fuelbier
 */
public class ETTimedSampleReceiver implements ETSampleReceiver {
	
	private ETSortedSampleList samples;
	private Iterator<ETSample> samplesIter;
	private ETSample currentSample;
	
	private ETTimer timer;
	private long startTime;
	
	/** Constructs a new ETTimedSampleReceiver.
	 *  <p>
	 *  The sample list will initially be empty and has to be set manually.
	 * 
	 *  @param timer Timer for timing simulation
	 */
	public ETTimedSampleReceiver(ETTimer timer) {
		this(timer, new ETSortedSampleList());
	}
	
	/** Constructs a new ETTimedSampleReceiver.
	 * 
	 *  @param timer Timer for timing simulation
	 *  @param samples Ordered list of eyetracking samples
	 */
	public ETTimedSampleReceiver(ETTimer timer, ETSortedSampleList samples) {
		this.timer = timer;
		this.samples = samples;
		
		resetTimer();
		resetSampleIteration();
	}

	/** Retrieves a single eyetracking sample from the sample list.
	 *  <p>
	 *  The sample returned will be the next one in the list matching the 
	 *  simulated time, or null if the list is fully iterated or empty. 
	 *  If there is no sample with a matching timestamp in the list, 
	 *  the sample with the next-higher timestamp will be returned instead 
	 *  (the first sample in the list that has a timestamp higher than the simulated 
	 *  timestamp). This effectively closes timestamp-holes by filling in with the 
	 *  sample next in line. 
	 */
	@Override
	public ETSample getSample() {
		if(samples.size() == 0)
		{
			return null;
		}
		
		long elapsed = timer.getTimeMicro() - startTime;
		
		while(currentSample.getTimestamp() < elapsed)
		{
			if(samplesIter.hasNext())
			{
				currentSample = samplesIter.next();
			}
			else
			{
				return null;
			}
		}
		
		return currentSample;
	}
	
	/** Resets the SampleReceiver. This includes a reset of the timer, aswell 
	 *  as a reset of the list iteration. The timer will start to run instantly after 
	 *  calling this method.
	 *  <p>
	 *  Make sure to call this method right before starting your eyetracking sample 
	 *  poll loop, to avoid skipped samples.
	 */
	public void reset() {
		resetTimer();
		resetSampleIteration();
	}
	
	/** Resets the internal timer. The timer will start to run instantly after calling 
	 *  this method.
	 */
	private void resetTimer() {
		startTime = timer.getTimeMicro();
	}
	
	/** Resets the list iteration to the beginning of the sample list.
	 */
	private void resetSampleIteration() {
		samplesIter = samples.iterator();
		
		if(samplesIter.hasNext())
			currentSample = samplesIter.next();
		else
			currentSample = null;
	}
	
	/** Sets the sample list that will be used for sample retrieval.
	 * 
	 *  @param samples Sample list
	 */
	public void setSamples(ETSortedSampleList samples) {
		this.samples = samples;
		resetSampleIteration();
	}

}
