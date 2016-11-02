package service;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import static org.junit.Assert.*;

import event.ETEvent;
import event.ETSortedEventList;
import eye.ETEye;
import exception.ETBadFormatException;

import java.io.File;

public class ETSortedEventLoaderTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void fromTextFile_correctFormatting_loadsAllEvents() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testevents_goodformat.txt").toURI());
		ETSortedEventList events = ETSortedEventLoader.fromTextFile(eventFile);

		assertEquals(2, events.size());
		
		ETEvent event1 = events.get(0);
		ETEvent event2 = events.get(1);
		final double DELTA = 1e-9;
		
		assertEquals(1, event1.getStartTime());
		assertEquals(1, event1.getEndTime());
		assertEquals(ETEye.LEFT, event1.getEye());
		assertEquals(1.1, event1.getPositionX(), DELTA);
		assertEquals(1.1, event1.getPositionY(), DELTA);
		
		assertEquals(2, event2.getStartTime());
		assertEquals(2, event2.getEndTime());
		assertEquals(ETEye.RIGHT, event2.getEye());
		assertEquals(2, event2.getPositionX(), DELTA);
		assertEquals(2, event2.getPositionY(), DELTA);
	}
	
	@Test
	public void fromTextFile_notEnoughFields_throwsException() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testevents_not_enough_fields.txt").toURI());
		
		exception.expect(ETBadFormatException.class);
		
		ETSortedEventLoader.fromTextFile(eventFile);
	}
	
	@Test
	public void fromTextFile_unparsableFields_throwsException() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testevents_unparsable_fields.txt").toURI());
		
		exception.expect(ETBadFormatException.class);
		
		ETSortedSampleLoader.fromTextFile(eventFile);
	}
	
}
