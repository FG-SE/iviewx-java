package persistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.common.io.Files;

import core.chronologic.ETChronologicCollection;
import exception.ETFileFormatException;
import iviewx.eye.ETEye;
import iviewx.event.ETEvent;

/** Loads eyetracking events from persistent sources to be used with {@link event.ETPlaybackEventReceiver}.
 *  <p>
 *  Currently the following persistent formats are supported:
 *  <ul>
 *    <li>
 *    	<strong>Eyetracking Event File (<em>*.ete</em>)</strong> (see {@link #fromEyetrackingEventFile(File) fromEyetrackingEventFile})
 *    </li>
 *  </ul>
 *  <p>
 *  See the respective load functions for further information.
 * 
 *  @author Luca Fuelbier
 */
public class ETEventLoader {
	
	/** Loads eyetracking events from a eyetracking even file.
	 *  <p>
	 *  Eyetracking event files are indicated by the <em>*.ete</em> file extension.
	 *  <p>
	 *  Each line in the event file has to contain all the values of a single event in order,
	 *  separated by a single whitespace:
	 *  <ol>
	 *    <li>Start time, (long)</li>
	 *    <li>End time, (long)</li>
	 *    <li>Eye, (char, 'l' or 'r')</li>
	 *    <li>Gaze position X, (double)</li>
	 *    <li>Gaze position Y, (double)</li>
	 *  </ol>
	 *  <p>
	 *  The events are parsed and stored in a {@link core.chronologic.ETChronologicCollection}.
	 *  Events that are not in chronological order are inserted as specified by
	 *  {@link core.chronologic.ETChronologicCollection#add}.
	 *  <p>
	 *  Lines starting with a <strong>#</strong> are considered comments and are ignored.
	 *  
	 *  @param file Textfile containing the eyetracking event information
	 *  @return Sorted event list generated from the text files content
	 *  @throws FileNotFoundException If the file could not be found
	 *  @throws IOException If an error occurred during I/O
	 *  @throws ETFileFormatException If the text file is incorrectly formatted or has an invalid file extension
	 */
	public static ETChronologicCollection<ETEvent> fromEyetrackingEventFile(File file) 
			throws FileNotFoundException, IOException, ETFileFormatException
	{
		String extension = Files.getFileExtension(file.getPath());
		
		if(!extension.equals("ete")) {
			throw new ETFileFormatException(
				String.format("Wrong file extension. Expected \"file.ete\" - Got \"file.%s\".", extension)
			);
		}
		
		ETChronologicCollection<ETEvent> events = new ETChronologicCollection<>();
		
		try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
			for(String line = reader.readLine(); line != null; line = reader.readLine()) {
				if(line.startsWith("#"))
					continue;
				ETEvent sample = parseEventFromEventData(line);
				events.add(sample);
			}
		}
		
		return events;
	}
	
	/** Parses a String containing eyetracking event data into a {@link event.ETEvent} object.
	 *  <p>
	 *  For the correct format of the String, see {@link #fromEyetrackingEventFile}.
	 *  
	 *  @param rawData String containing eyetracking event data
	 *  @return Eyetracking event
	 *  @throws ETFileFormatException If the data is incorrectly formatted
	 */
	private static ETEvent parseEventFromEventData(String rawData) throws ETFileFormatException
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
			throw new ETFileFormatException(
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
