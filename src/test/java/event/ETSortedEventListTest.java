package event;

import org.junit.Test;
import static org.junit.Assert.*;

import event.ETEvent;
import event.ETSortedEventList;
import eye.ETEye;

public class ETSortedEventListTest {
	
	@Test
	public void addIgnore_emptyList_insertsEvent() {
		ETSortedEventList events = new ETSortedEventList();
		
		ETEvent event = createEvent(0, 1);
		events.addIgnore(event);
		
		assertSame(event, events.get(0));
		assertEquals(1, events.size());
	}
	
	@Test
	public void addIgnore_eventsInList_insertsEventWithNewerTimestamp() {
		ETSortedEventList events = new ETSortedEventList();
		
		ETEvent event1 = createEvent(0, 1);
		ETEvent event2 = createEvent(2, 3);
		ETEvent event3 = createEvent(3, 4);
		
		events.addIgnore(event1);
		events.addIgnore(event2);
		events.addIgnore(event3);
		
		assertSame(event2, events.get(1));
		assertSame(event3, events.get(2));
		assertEquals(3, events.size());
	}
	
	@Test
	public void addIgnore_eventsInList_ignoresEventWithOlderTimestamp() {
		ETSortedEventList events = new ETSortedEventList();
		
		ETEvent event1 = createEvent(0, 1);
		ETEvent event2 = createEvent(0, 2);
		
		events.addIgnore(event1);
		events.addIgnore(event2);
		
		assertSame(event1, events.get(0));
		assertEquals(1, events.size());
	}
	
	private ETEvent createEvent(long startTime, long endTime) {
		return new ETEvent(startTime, endTime, ETEye.LEFT, 0, 0);
	}
	
}
