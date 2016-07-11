package data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

// TODO: Refactor to interface to be able to implement different implementations
//       if performance issues arrive

// Ordered by timestamp
public class ETSortedSampleList implements Iterable<ETSample>{

	private ArrayList<ETSample> samples;
	
	public ETSortedSampleList() {
		samples = new ArrayList<ETSample>();
	}
	
	// Ignores samples that have a lower timestamp than the last
	public void addIgnore(ETSample sample) {
		ETSample last = last();
		
		if(last == null)
			samples.add(sample);
		else if(last.getTimestamp() < sample.getTimestamp())
			samples.add(sample);
		// else ignore
	}
	
	public int size() {
		return samples.size();
	}
	
	public ETSample get(int index) throws IndexOutOfBoundsException{
		return samples.get(index);
	}
	
	private ETSample last() {
		if(samples.size() == 0)
			return null;
		else
			return samples.get(samples.size()-1);
	}

	@Override
	public Iterator<ETSample> iterator() {
		return Collections.unmodifiableList(samples).iterator();
	}
	
}
