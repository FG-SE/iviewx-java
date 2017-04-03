package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Files;

import core.chronologic.ETChronologicCollection;
import iviewx.data.ETEyeData;
import iviewx.data.ETSample;
import exception.ETFileFormatException;;

/** Loads eyetracking samples from persistent sources to be used with {@link sample.ETPlaybackSampleReceiver}.
 *  <p>
 *  Currently the following persistent formats are supported:
 *  <ul>
 *    <li>
 *    	<strong>Eyetracking Sample File (<em>*.ets</em>)</strong> (see {@link #fromEyetrackingSampleFile(File) fromEyetrackingSampleFile})
 *    </li>
 *  </ul>
 *  <p>
 *  See the respective load functions for further information.
 * 
 *  @author Luca Fuelbier
 */
public class ETSampleLoader {

	/** Loads eyetracking samples from a eyetracking sample file.
	 *  <p>
	 *  Eyetracking sample files are indicated by the <em>*.ets</em> file extension.
	 *  <p>
	 *  Each line in the text file has to contain all the values of a single sample in order,
	 *  separated by a single whitespace:
	 *  <ol>
	 *    <li>Pupil diameter, left eye, (double)</li>
	 *    <li>Position X, left eye, (double)</li>
	 *    <li>Position Y, left eye, (double)</li>
	 *    <li>Position Z, left eye, (double)</li>
	 *    <li>Gaze X, left eye, (double)</li>
	 *    <li>Gaze Y, left eye, (double)</li>
	 *    <li>Pupil diameter, right eye, (double)</li>
	 *    <li>Position X, right eye, (double)</li>
	 *    <li>Position Y, right eye, (double)</li>
	 *    <li>Position Z, right eye, (double)</li>
	 *    <li>Gaze X, right eye, (double)</li>
	 *    <li>Gaze Y, right eye, (double)</li>
	 *    <li>Timestamp, microseconds, (long)</li>
	 *  </ol>
	 *  <p>
	 *  The samples are parsed and stored in a {@link core.chronologic.ETChronologicCollection}.
	 *  Samples that are not in chronological order are inserted as specified by
	 *  {@link core.chronologic.ETChronologicCollection#add}.
	 *  <p>
	 *  Lines starting with a <strong>#</strong> are considered comments and are ignored.
	 *  
	 *  @param file Textfile containing the eyetracking sample information
	 *  @return Sorted sample list generated from the text files content
	 *  @throws FileNotFoundException If the file could not be found
	 *  @throws IOException If an error occured during I/O
	 *  @throws ETFileFormatException If the text file is incorrectly formatted or has an invalid file extension
	 */
	public static ETChronologicCollection<ETSample> fromEyetrackingSampleFile(File file) 
			throws FileNotFoundException, IOException, ETFileFormatException
	{
		String extension = Files.getFileExtension(file.getPath());
		
		if(!extension.equals("ets")) {
			throw new ETFileFormatException(
				String.format("Wrong file extension. Expected \"file.ets\" - Got \"file.%s\".", extension)
			);
		}
		
		ETChronologicCollection<ETSample> samples = new ETChronologicCollection<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				if(line.startsWith("#"))
					continue;
				ETSample sample = parseSampleFromSampleData(line);
				samples.add(sample);
			}
		}
		
		return samples;
	}
	
	/** Parses a String containing eyetracking sample data into a {@link sample.ETSample} object.
	 *  <p>
	 *  For the correct format of the String, see {@link #fromEyetrackingSampleFile}.
	 *  
	 *  @param rawData String containing eyetracking sample data
	 *  @return Eyetracking sample
	 *  @throws ETFileFormatException If the data is incorrectly formatted
	 */
	private static ETSample parseSampleFromSampleData(String rawData) throws ETFileFormatException
	{
		try {
			String[] sampleData = rawData.split(" ");
			
			double diam_L = Double.parseDouble(sampleData[0]);
			double posX_L = Double.parseDouble(sampleData[1]);
			double posY_L = Double.parseDouble(sampleData[2]);
			double posZ_L = Double.parseDouble(sampleData[3]);
			double gazeX_L = Double.parseDouble(sampleData[4]);
			double gazeY_L = Double.parseDouble(sampleData[5]);
			
			double diam_R = Double.parseDouble(sampleData[6]);
			double posX_R = Double.parseDouble(sampleData[7]);
			double posY_R = Double.parseDouble(sampleData[8]);
			double posZ_R = Double.parseDouble(sampleData[9]);
			double gazeX_R = Double.parseDouble(sampleData[10]);
			double gazeY_R = Double.parseDouble(sampleData[11]);
			
			long timestamp = Long.parseLong(sampleData[12]);
			
			ETEyeData leftEye = new ETEyeData(diam_L, posX_L, posY_L, posZ_L, gazeX_L, gazeY_L);
			ETEyeData rightEye = new ETEyeData(diam_R, posX_R, posY_R, posZ_R, gazeX_R, gazeY_R);
			ETSample sample = new ETSample(leftEye, rightEye, timestamp);
			
			return sample;
		}
		catch(Exception e) {
			throw new ETFileFormatException(
					"The sample file is badly formatted.\r\n" 											+
					"The row with content \""															+
					rawData																				+
					"\" does not conform to the expected format.\r\n"									+
					"Expected format: " 																+
					"diam_l(double) pos_x_l(double) pos_y_l(double) pos_z_l(double) "					+
					"gaze_x_l(double) gaze_y_l(double) "												+
					"diam_r(double) pos_x_r(double) pos_y_r(double) pos_z_r(double) "					+
					"gaze_x_r(double) gaze_y_r(double) timestamp(long)"													
			);
		}
	}
}
