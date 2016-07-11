package data;

public class ETEyeData {
	
	private double diameter;
	private double eyePosX;
	private double eyePosY;
	private double eyePosZ;
	private double gazeX;
	private double gazeY;
	
	public ETEyeData(double diameter, double eyePosX, double eyePosY, double eyePosZ,
					 double gazeX, double gazeY)
	{
		this.diameter = diameter;
		this.eyePosX = eyePosX;
		this.eyePosY = eyePosY;
		this.eyePosZ = eyePosZ;
		this.gazeX = gazeX;
		this.gazeY = gazeY;
	}
	
	public double getDiameter() {
		return diameter;
	}
	
	public double getEyePositionX() {
		return eyePosX;
	}
	
	public double getEyePositionY() {
		return eyePosY;
	}
	
	public double getEyePositionZ() {
		return eyePosZ;
	}
	
	public double getGazeX() {
		return gazeX;
	}
	
	public double getGazeY() {
		return gazeY;
	}
	
	public static ETEyeData createDefault() {
		return new ETEyeData(0, 0, 0, 0, 0, 0);
	}
	
}
