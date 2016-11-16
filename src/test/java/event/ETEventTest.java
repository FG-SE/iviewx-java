package event;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import event.ETEvent;
import eye.ETEye;

public class ETEventTest {

	@Test
	public void compareTo_otherEventStartedLater_returnsNegativeInt() {
		ETEvent eventEarly = createEvent(0);
		ETEvent eventLate = createEvent(10);
		
		assertTrue(eventEarly.compareTo(eventLate) < 0);
	}
	
	@Test
	public void compareTo_otherEventStartedSameTime_returnsZero() {
		ETEvent event1 = createEvent(10);
		ETEvent event2 = createEvent(10);
		
		assertTrue(event1.compareTo(event2) == 0);
	}
	
	@Test
	public void compareTo_otherEventStartedEarlier_returnsPositiveInt() {
		ETEvent eventLate = createEvent(10);
		ETEvent eventEarly = createEvent(0);
		
		assertTrue(eventLate.compareTo(eventEarly) > 0);
	}
	
	private ETEvent createEvent(long startTimestamp) {
		return new ETEvent(startTimestamp,
						   startTimestamp + 1,
						   ETEye.LEFT,
						   0, 0);
	}
	
}