package generic;

public class ETResponse<E> {
	
	private ETResponseType type;
	private E data;
	
	public ETResponse(ETResponseType type, E data) {
		this.type = type;
		this.data = data;
	}
	
	public ETResponseType getType() {
		return type;
	}
	
	public E getData() {
		return data;
	}
	
}
