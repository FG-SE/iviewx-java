package sample;

import java.util.Iterator;

// TODO: Rename this class, using a name that better indicates the classes functionality

public class ETPlaybackSampleReceiver implements ETSampleReceiver {
	
	ETSortedSampleList samples;
	Iterator<ETSample> samplesIter;
	
	public ETPlaybackSampleReceiver() {
		samples = new ETSortedSampleList();
		reset();
	}

	@Override
	public ETSample getSample() {
		if(samplesIter.hasNext())
			return samplesIter.next();
		else
			return null;
	}
	
	public void reset() {
		samplesIter = samples.iterator();
	}
	
	public void setSamples(ETSortedSampleList samples) {
		this.samples = samples;
		reset();
	}

}
