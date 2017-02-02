package generic;

public class ETDefaultStabilizationStrategy<E> implements ETStabilizationStrategy<E> {

	@Override
	public E stabilize(E element) {
		return element;
	}

}
