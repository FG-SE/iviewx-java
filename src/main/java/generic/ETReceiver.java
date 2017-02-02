package generic;

import java.util.Iterator;

public interface ETReceiver<E extends ChronologicComparable<E>> extends Iterator<E> {
	
	@Override
	public boolean hasNext();
	
	@Override
	public E next();
	
	public void setStabilizationStrategy(ETStabilizationStrategy<E> strategy);
	
}
