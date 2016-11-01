package sample;

import java.util.Iterator;

/** Receives eyetracking samples from a ETSortedSampleList.
 *  <p>
 *  If you want to iterate the samples from the list again you will 
 *  have to call the reset() method before requesting a new sample.
 *  <p>
 *  A sorted sample list is used to make sure, that samples are sorted 
 *  beforehand to reduce computation effort while receiving samples.
 * 
 *  @author Luca Fuelbier
 */
public class ETPlaybackSampleReceiver implements ETSampleReceiver {
	
	ETSortedSampleList samples;
	Iterator<ETSample> samplesIter;
	
	ETSampleStabilizationStrategy stabilizationStrategy;
	
	/** Constructs a new Playback SampleReceiver.
	 *  <p>
	 *  The sample list will initially be empty and has to be set manually.
	 */
	public ETPlaybackSampleReceiver() {
		this(new ETSortedSampleList(), new ETPassthroughSampleStabilizationStrategy());
	}
	
	/** Constructs a new Playback SampleReceiver with the given sample stabilization 
	 *  strategy.
	 *  <p>
	 *  The sample list will initially be empty and has to be set manually.
	 */
	public ETPlaybackSampleReceiver(ETSampleStabilizationStrategy strategy) {
		this(new ETSortedSampleList(), strategy);
	}
	
	/** Constructs a new Playback SampleReceiver initializing it 
	 *  with a provided ETSortedSampleList. 
	 * 
	 *  @param samples Sample list
	 */
	public ETPlaybackSampleReceiver(ETSortedSampleList samples) {
		this(samples, new ETPassthroughSampleStabilizationStrategy());
	}
	
	/** Constructs a new Playback SampleReceiver initializing it 
	 *  with a provided ETSortedSampleList and the given sample 
	 *  stabilization strategy.
	 * 
	 *  @param samples Sample list
	 *  @param strategy Sample stabilization strategy
	 */
	public ETPlaybackSampleReceiver(ETSortedSampleList samples, ETSampleStabilizationStrategy strategy) {
		this.samples = samples;
		samplesIter = samples.iterator();
		stabilizationStrategy = strategy;
	}

	/** Retrieves a single eyetracking sample from the sample list.
	 *  <p>
	 *  The sample returned will be the next one in the list, or 
	 *  null if the list is fully iterated or empty.
	 *  <p>
	 *  Note that this class does a full iteration of the sample list. 
	 *  Timestamps do not matter. If you want a SampleReceiver that simulates 
	 *  realtime sample access use ETTimedSampleReceiver instead. 
	 * 
	 *  @return Eyetracking sample
	 */
	@Override
	public ETSample getSample() {
		if(samplesIter.hasNext()) {
			ETSample nextSample = samplesIter.next();
			ETSample stabilizedSample = stabilizationStrategy.stabilize(nextSample);
			return stabilizedSample;
		}
		else {
			return null;
		}
	}
	
	/** Sets the sample stabilization strategy.
	 * 
	 *  Stabilization strategies correct the samples returned by applying a correcting 
	 *  algorithm to the retrieved sample before returning it.
	 * 
	 *  @param strategy Sample stabilization strategy
	 */
	@Override
	public void setStabilizationStrategy(ETSampleStabilizationStrategy strategy) {
		stabilizationStrategy = strategy;
	}
	
	/** Resets the SampleReceiver to the beginning of the sample list.
	 *  <p>
	 *  Resetting essentially means, that you will iterate the sample list again. 
	 *  This can be very helpful if you want to analyze the same set of data multiple times.
	 */
	public void reset() {
		samplesIter = samples.iterator();
	}
	
	/** Sets the sample list that will be used for sample retrieval.
	 * 
	 *  @param samples Sample list
	 */
	public void setSamples(ETSortedSampleList samples) {
		this.samples = samples;
		samplesIter = samples.iterator();
	}

}
