package generic;

public abstract class ETReceiver<E extends ChronologicComparable<E>> {
	
	private ETStabilizationStrategy<E> stabilizationStrategy;
	
	public ETReceiver() {
		this.stabilizationStrategy = new ETDefaultStabilizationStrategy<>();
	}
	
	public ETReceiver(ETStabilizationStrategy<E> strategy) {
		this.stabilizationStrategy = strategy;
	}
	
	protected abstract ETResponse<E> getNextFromSource();
	
	public ETResponse<E> getNext() {
		ETResponse<E> response = getNextFromSource();
		
		if(response.getData() == null) {
			return response;
		}
		
		E stabilizedData = stabilizationStrategy.stabilize(response.getData());
		return new ETResponse<E>(response.getType(), stabilizedData);
	}
	
	public void setStabilizationStrategy(ETStabilizationStrategy<E> strategy) {
		this.stabilizationStrategy = strategy;
	}
	
}
