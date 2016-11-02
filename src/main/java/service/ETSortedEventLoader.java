package service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.ETBadFormatException;
import eye.ETEye;
import event.ETEvent;
import event.ETSortedEventList;

public class ETSortedEventLoader {
	
	public static ETSortedEventList fromTextFile(File file) throws FileNotFoundException, IOException, ETBadFormatException
	{
		ETSortedEventList samples = new ETSortedEventList();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				ETEvent sample = parseEventFromText(line);
				samples.addIgnore(sample);
			}
		}
		
		return samples;
	}
	
	private static ETEvent parseEventFromText(String rawData) throws ETBadFormatException
	{
		try {
			String[] eventData = rawData.split(" ");
			
			long startTime = Long.parseLong(eventData[0]);
			long endTime = Long.parseLong(eventData[1]);
			ETEye eye = ETEye.fromChar(eventData[2].charAt(0));
			double posX = Double.parseDouble(eventData[3]);
			double posY = Double.parseDouble(eventData[4]);
			
			ETEvent event = new ETEvent(startTime, endTime, eye, posX, posY);
			
			return event;
		}
		catch(Exception e) {
			throw new ETBadFormatException(
					"The event file is badly formatted.\r\n" 											+
					"The row with content \""															+
					rawData																				+
					"\" does not conform to the expected format.\r\n"									+
					"Expected format: " 																+
					"start_time(long) end_time(long) eye(char) pos_x(double) pos_y(double)"												
			);
		}
	}
}
