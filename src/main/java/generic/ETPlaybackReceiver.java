package generic;

import java.util.Iterator;

public class ETPlaybackReceiver<E extends ChronologicComparable<E>> extends ETReceiver<E> {

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
	protected ETResponse<E> getNextFromSource() {
		if(elementsIter.hasNext()) {
			return new ETResponse<E>(ETResponseType.NEW_DATA, elementsIter.next());
		}
		else {
			return new ETResponse<E>(ETResponseType.SOURCE_DEPLETED, null);
		}
	}
	
	public void reset() {
		elementsIter = elements.iterator();
	}
	
	public void setElements(ETChronologicCollection<E> elements) {
		this.elements = elements;
		elementsIter = elements.iterator();
	}

}
