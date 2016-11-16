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

/** Loads eyetracking events from persistent sources to be used with {@link event.ETPlaybackEventReceiver}.
 *  <p>
 *  Currently the following persistent formats are supported:
 *  <ul>
 *    <li><strong>Textfile</strong> (see {@link #fromTextFile(File) fromTextFile})
 *  </ul>
 *  <p>
 *  See the respective load functions for further information.
 * 
 *  @author Luca Fuelbier
 */
public class ETSortedEventLoader {
	
	/** Loads eyetracking events from a text file.
	 *  <p>
	 *  A line in the text file has to contain all of the information of an eyetracking event.
	 *  Each line has to contain the following values in order, separated by a single whitespace:
	 *  <ol>
	 *    <li>Start time, (long)</li>
	 *    <li>End time, (long)</li>
	 *    <li>Eye, (char, 'l' or 'r')</li>
	 *    <li>Gaze position X, (double)</li>
	 *    <li>Gaze position Y, (double)</li>
	 *  </ol>
	 *  <p>
	 *  Events that are not stored in correct chronological order are ignored and not added to the
	 *  resulting list.
	 *  <p>
	 *  Lines starting with <strong>#</strong> are considered comments and are ignored.
	 *  
	 *  @param file Textfile containing the eyetracking event information
	 *  @return Sorted event list generated from the text files content
	 *  @throws FileNotFoundException If the file could not be found
	 *  @throws IOException If an error occurred during I/O
	 *  @throws ETBadFormatException If the text file is incorrectly formatted
	 */
	public static ETSortedEventList fromTextFile(File file) throws FileNotFoundException, IOException, ETBadFormatException
	{
		ETSortedEventList samples = new ETSortedEventList();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				if(line.startsWith("#"))
					continue;
				ETEvent sample = parseEventFromText(line);
				samples.addIgnore(sample);
			}
		}
		
		return samples;
	}
	
	/** Parses a String containing eyetracking event data into a {@link event.ETEvent} object.
	 *  <p>
	 *  For the correct format of the String, see {@link #fromTextFile(File) fromTextFile}.
	 *  
	 *  @param rawData String containing eyetracking event data
	 *  @return Eyetracking event
	 *  @throws ETBadFormatException If the String is incorrectly formatted
	 */
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
