package event;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import event.ETEvent;
import event.ETSortedEventList;
import eye.ETEye;
import event.ETPlaybackEventReceiver;

public class ETPlackbackEventReceiverTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void next_noEventBatch_throwsException() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		
		thrown.expect(NoSuchElementException.class);
		eventReceiver.next();
	}
	
	@Test
	public void next_emptyEventBatch_throwsException() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList emptyEventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyEventList);
		
		thrown.expect(NoSuchElementException.class);
		eventReceiver.next();
	}
	
	@Test
	public void next_orderedEventBatch_returnsEveryEvent() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		assertSame(eventReceiver.next(), eventList.get(0));
		assertSame(eventReceiver.next(), eventList.get(1));
		assertSame(eventReceiver.next(), eventList.get(2));
	}
	
	@Test
	public void next_orderedEventBatch_throwsExceptionAfterBatchDepleted() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		assertNotNull(eventReceiver.next());
		assertNotNull(eventReceiver.next());
		assertNotNull(eventReceiver.next());
		
		thrown.expect(NoSuchElementException.class);
		eventReceiver.next();
	}
	
	@Test
	public void next_orderedEventBatchDepletedResetCalled_returnsEveryEvent() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		for(int i = 0; i < 3; ++i) {
			eventReceiver.next();
		}
		
		eventReceiver.reset();
		
		assertSame(eventReceiver.next(), eventList.get(0));
		assertSame(eventReceiver.next(), eventList.get(1));
		assertSame(eventReceiver.next(), eventList.get(2));
	}
	
	@Test
	public void next_setNewEmptyEventBatch_throwsException() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		ETSortedEventList emptyeventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyeventList);
		
		thrown.expect(NoSuchElementException.class);
		eventReceiver.next();
	}
	
	@Test
	public void hasNext_noEventBatch_returnsFalse() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		
		assertFalse(eventReceiver.hasNext());
	}
	
	@Test
	public void hasNext_emptyEventBatch_returnsFalse() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList emptyEventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyEventList);
		
		assertFalse(eventReceiver.hasNext());
	}
	
	@Test
	public void hasNext_orderedEventBatch_returnsTrue() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		assertTrue(eventReceiver.hasNext());
	}
	
	@Test
	public void hasNext_orderedEventBatch_returnsFalseAfterBatchDepleted() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		assertNotNull(eventReceiver.next());
		assertNotNull(eventReceiver.next());
		assertNotNull(eventReceiver.next());
		
		assertFalse(eventReceiver.hasNext());
	}
	
	@Test
	public void hasNext_orderedEventBatchDepletedResetCalled_returnsTrue() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		for(int i = 0; i < 3; ++i) {
			eventReceiver.next();
		}
		
		eventReceiver.reset();
		
		assertTrue(eventReceiver.hasNext());
	}
	
	@Test
	public void hasNext_setNewEmptyEventBatch_returnsFalse() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		ETSortedEventList emptyeventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyeventList);
		
		assertFalse(eventReceiver.hasNext());
	}
	
	private ETSortedEventList createTestEventBatch() {
		ETSortedEventList events = new ETSortedEventList();
		ETEvent event1 = new ETEvent(0, 1, ETEye.LEFT, 0, 0);
		ETEvent event2 = new ETEvent(1, 2, ETEye.LEFT, 1, 1);
		ETEvent event3 = new ETEvent(2, 3, ETEye.LEFT, 2, 2);
		events.addIgnore(event1);
		events.addIgnore(event2);
		events.addIgnore(event3);
		return events;
	}
}
