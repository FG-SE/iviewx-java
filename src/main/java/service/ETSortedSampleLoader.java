package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import data.ETEyeData;
import data.ETSample;
import data.ETSortedSampleList;

public class ETSortedSampleLoader {

	public static ETSortedSampleList fromTextFile(File file) throws FileNotFoundException, IOException {
		ETSortedSampleList samples = new ETSortedSampleList();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				ETSample sample = parseSampleFromText(line);
				samples.addIgnore(sample);
			}
		}
		
		return samples;
	}
	
	private static ETSample parseSampleFromText(String rawData) {
		String[] sampleData = rawData.split(" ");
		
		long timestamp = Long.parseLong(sampleData[0]);
		double leftGazeX = Double.parseDouble(sampleData[1]);
		double leftGazeY = Double.parseDouble(sampleData[2]);
		double rightGazeX = Double.parseDouble(sampleData[3]);
		double rightGazeY = Double.parseDouble(sampleData[4]);
		
		ETEyeData leftEye = new ETEyeData(0, 0, 0, 0, leftGazeX, leftGazeY);
		ETEyeData rightEye = new ETEyeData(0, 0, 0, 0, rightGazeX, rightGazeY);
		ETSample sample = new ETSample(leftEye, rightEye, timestamp);
		
		return sample;
	}
}
