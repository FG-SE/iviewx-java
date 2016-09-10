package event;

import data.ETEye;

public class ETEvent {
	
	private long startTime;
	private long endTime;
	private ETEye eye;
	private double posX;
	private double posY;
	
	public ETEvent(long startTime, long endTime, ETEye eye, long posX, long posY) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.eye = eye;
		this.posX = posX;
		this.posY = posY;
	}
	
	// Timing
	public long getStartTime() {
		return startTime;
	}
	
	public long getEndTime() {
		return endTime;
	}
	
	public long getDuration() {
		return endTime - startTime;
	}
	
	// Eye information
	public ETEye getEye() {
		return eye;
	}
	
	// Position
	public double getPositionX() {
		return posX;
	}
	
	public double getPositionY() {
		return posY;
	}
	
}
