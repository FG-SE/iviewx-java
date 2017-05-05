package de.unihannover.se.iviewxjava.iviewx.data;

import org.junit.Test;
import static org.junit.Assert.*;

import de.unihannover.se.iviewxjava.iviewx.data.ETEvent;
import de.unihannover.se.iviewxjava.iviewx.data.ETEye;

public class ETEventTest {

	@Test
	public void chrCompareTo_otherEventStartedLater_returnsNegativeInt() {
		ETEvent eventEarly = createEvent(0);
		ETEvent eventLate = createEvent(10);
		
		assertTrue(eventEarly.chrCompareTo(eventLate) < 0);
	}
	
	@Test
	public void chrCompareTo_otherEventStartedSameTime_returnsZero() {
		ETEvent event1 = createEvent(10);
		ETEvent event2 = createEvent(10);
		
		assertTrue(event1.chrCompareTo(event2) == 0);
	}
	
	@Test
	public void chrCompareTo_otherEventStartedEarlier_returnsPositiveInt() {
		ETEvent eventLate = createEvent(10);
		ETEvent eventEarly = createEvent(0);
		
		assertTrue(eventLate.chrCompareTo(eventEarly) > 0);
	}
	
	private ETEvent createEvent(long startTimestamp) {
		return new ETEvent(startTimestamp,
						   startTimestamp + 1,
						   ETEye.LEFT,
						   0, 0);
	}
	
}
