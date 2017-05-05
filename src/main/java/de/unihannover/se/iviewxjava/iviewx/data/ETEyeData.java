package de.unihannover.se.iviewxjava.iviewx.data;

/** Eyetracking data for one eye.
 *  <p>
 *  Contains various fields for eyetracking data, the important ones being 
 *  eye position and gaze coordinates.
 *  <p>
 *  It's important to note, that the eye position is a 3-dimensional coordinate 
 *  of the eyes actual position. The gaze coordinate is the 2-dimensional screen
 *  coordinate that is more commonly needed.
 * 
 *  @author Luca Fuelbier
 */
public class ETEyeData {
	
	private double diameter;
	private double eyePosX;
	private double eyePosY;
	private double eyePosZ;
	private double gazeX;
	private double gazeY;
	
	/** Constructs a new eyetracking data object.
	 * 
	 * @param diameter Pupil diameter [mm]
	 * @param eyePosX Horizontal position of the eye relative to the camera [mm]
	 * @param eyePosY Vertical position of the eye relative to the camera [mm]
	 * @param eyePosZ Distance to the camera [mm]
	 * @param gazeX Horizontal gaze position on the screen [px]
	 * @param gazeY Vertical gaze position on the screen [px]
	 */
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
	
	/** Returns the pupils diameter in mm.
	 * 
	 *  @return The pupils diameter in mm
	 */
	public double getDiameter() {
		return diameter;
	}
	
	/** Returns the horizontal position of the eye relative to the camera in mm.
	 * 
	 *  @return Horizontal position of the eye in mm
	 */
	public double getEyePositionX() {
		return eyePosX;
	}
	
	/** Returns the vertical position of the eye relative to the camera in mm.
	 * 
	 *  @return The vertical position of the eye in mm
	 */
	public double getEyePositionY() {
		return eyePosY;
	}
	
	/** Return the distance of the eye to the camera in mm.
	 * 
	 *  @return The distance to the eye in mm
	 */
	public double getEyePositionZ() {
		return eyePosZ;
	}
	
	/** Returns the horizontal gaze position on the screen in pixel.
	 * 
	 *  @return The horizonal gaze position in px
	 */
	public double getGazeX() {
		return gazeX;
	}
	
	/** Returns the vertical gaze position on the screen in pixel.
	 * 
	 *  @return The vertical gaze position in px
	 */
	public double getGazeY() {
		return gazeY;
	}
	
	/** Creates a zero initialized eyetracking data object.
	 * 
	 *  @return Zero initialized ETEyeData object
	 */
	public static ETEyeData createDefault() {
		return new ETEyeData(0, 0, 0, 0, 0, 0);
	}
	
}
