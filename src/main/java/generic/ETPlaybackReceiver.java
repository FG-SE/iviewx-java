package generic;

import java.util.Iterator;

public class ETPlaybackReceiver<E extends ChronologicComparable<E>> implements ETReceiver<E> {

	ETChronologicCollection<E> elements;
	Iterator<E> elementsIter;
	ETStabilizationStrategy<E> stabilizationStrategy;
	
	public ETPlaybackReceiver() {
		this(new ETChronologicCollection<>(), new ETDefaultStabilizationStrategy<>());
	}
	
	public ETPlaybackReceiver(ETChronologicCollection<E> elements, ETStabilizationStrategy<E> strategy) {
		this.elements = elements;
		elementsIter = elements.iterator();
		this.stabilizationStrategy = strategy;
	}

	@Override
	public E next() {
		return elementsIter.next();
	}
	
	@Override
	public boolean hasNext() {
		return elementsIter.hasNext();
	}
	
	@Override
	public void setStabilizationStrategy(ETStabilizationStrategy<E> strategy) {
		this.stabilizationStrategy = strategy;
	}
	
	public void reset() {
		elementsIter = elements.iterator();
	}
	
	public void setElements(ETChronologicCollection<E> elements) {
		this.elements = elements;
		elementsIter = elements.iterator();
	}

}
