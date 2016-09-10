package sample;

import java.util.Iterator;

public class ETTimedSampleReceiver implements ETSampleReceiver {
	
	private ETSortedSampleList samples;
	private Iterator<ETSample> samplesIter;
	private ETSample currentSample;
	
	private ETTimer timer;
	private long startTime;
	
	public ETTimedSampleReceiver(ETTimer timer) {
		this.timer = timer;
		
		samples = new ETSortedSampleList();
		samplesIter = samples.iterator();
		
		reset();
	}

	@Override
	public ETSample getSample() {
		if(samples.size() == 0)
		{
			return null;
		}
		
		long elapsed = timer.getTime() - startTime;
		
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
	
	public void reset() {
		resetTimer();
		resetSampleIteration();
	}
	
	private void resetTimer() {
		startTime = timer.getTime();
	}
	
	private void resetSampleIteration() {
		samplesIter = samples.iterator();
		
		if(samplesIter.hasNext())
			currentSample = samplesIter.next();
		else
			currentSample = null;
	}
	
	public void setSamples(ETSortedSampleList samples) {
		this.samples = samples;
		resetSampleIteration();
	}

}
