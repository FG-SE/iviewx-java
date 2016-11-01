package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import eye.ETEyeData;
import sample.ETSample;
import sample.ETSortedSampleList;
import exception.ETBadFormatException;

public class ETSortedSampleLoader {

	public static ETSortedSampleList fromTextFile(File file) throws FileNotFoundException, IOException, ETBadFormatException
	{
		ETSortedSampleList samples = new ETSortedSampleList();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				ETSample sample = parseSampleFromText(line);
				samples.addIgnore(sample);
			}
		}
		
		return samples;
	}
	
	private static ETSample parseSampleFromText(String rawData) throws ETBadFormatException
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
			throw new ETBadFormatException(
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
