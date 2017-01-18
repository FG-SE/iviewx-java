package persistence;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import static org.junit.Assert.*;

import event.ETEvent;
import eye.ETEye;
import generic.ETChronologicCollection;
import persistence.ETEventLoader;
import exception.ETFileFormatException;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class ETEventLoaderTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void fromEyetrackingEventFile_correctFormatting_loadsAllEvents() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./persistence/testevents_goodformat.ete").toURI());
		ETChronologicCollection<ETEvent> eventCollection = ETEventLoader.fromEyetrackingEventFile(eventFile);
		List<ETEvent> eventList = new ArrayList<>(eventCollection);

		assertEquals(2, eventCollection.size());
		
		ETEvent event1 = eventList.get(0);
		ETEvent event2 = eventList.get(1);
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
	public void fromEyetrackingEventFile_notEnoughFields_throwsException() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./persistence/testevents_not_enough_fields.ete").toURI());
		
		exception.expect(ETFileFormatException.class);
		exception.expectMessage("badly formatted");
		
		ETEventLoader.fromEyetrackingEventFile(eventFile);
	}
	
	@Test
	public void fromEyetrackingEventFile_unparsableFields_throwsException() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./persistence/testevents_unparsable_fields.ete").toURI());
		
		exception.expect(ETFileFormatException.class);
		exception.expectMessage("badly formatted");
		
		ETEventLoader.fromEyetrackingEventFile(eventFile);
	}
	
	@Test
	public void fromEyetrackingEventFile_badFileExtension_throwsException() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./persistence/testevents_bad_extension.badext").toURI());
		
		exception.expect(ETFileFormatException.class);
		exception.expectMessage("Wrong file extension");
		
		ETEventLoader.fromEyetrackingEventFile(eventFile);
	}
	
	@Test
	public void fromEyetrackingEventFile_comments_commentsAreIgnored() throws Exception {
		File eventFile = new File(ClassLoader.getSystemClassLoader().getResource("./persistence/testevents_comments.ete").toURI());
		ETChronologicCollection<ETEvent> events = ETEventLoader.fromEyetrackingEventFile(eventFile);
		
		assertEquals(2, events.size());
	}
	
}
