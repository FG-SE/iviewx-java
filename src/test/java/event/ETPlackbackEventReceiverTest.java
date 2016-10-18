package event;

import org.junit.Test;
import static org.junit.Assert.*;

import event.ETEvent;
import event.ETSortedEventList;
import eye.ETEye;
import event.ETPlaybackEventReceiver;

public class ETPlackbackEventReceiverTest {

	@Test
	public void getEvent_noEventBatch_returnsNull() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		
		assertNull(eventReceiver.getEvent());
	}
	
	@Test
	public void getEvent_emptyEventBatch_returnsNull() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList emptyEventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyEventList);
		
		assertNull(eventReceiver.getEvent());
	}
	
	@Test
	public void getEvent_emptyEventBatch_subsequentCallsReturnNull() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList emptyEventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyEventList);
		
		assertNull(eventReceiver.getEvent());
		assertNull(eventReceiver.getEvent());
	}
	
	@Test
	public void getEvent_orderedEventBatch_returnsEveryEvent() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		assertSame(eventReceiver.getEvent(), eventList.get(0));
		assertSame(eventReceiver.getEvent(), eventList.get(1));
		assertSame(eventReceiver.getEvent(), eventList.get(2));
	}
	
	@Test
	public void getEvent_orderedEventBatch_returnsNullAfterBatchDepleted() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		assertNotNull(eventReceiver.getEvent());
		assertNotNull(eventReceiver.getEvent());
		assertNotNull(eventReceiver.getEvent());
		
		assertNull(eventReceiver.getEvent());
	}
	
	@Test
	public void getEvent_orderedEventBatchDepletedResetCalled_returnsEveryEvent() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		for(int i = 0; i < 3; ++i) {
			eventReceiver.getEvent();
		}
		
		eventReceiver.reset();
		
		assertSame(eventReceiver.getEvent(), eventList.get(0));
		assertSame(eventReceiver.getEvent(), eventList.get(1));
		assertSame(eventReceiver.getEvent(), eventList.get(2));
	}
	
	@Test
	public void getEvent_setNewEmptyEventBatch_returnsNull() {
		ETPlaybackEventReceiver eventReceiver = new ETPlaybackEventReceiver();
		ETSortedEventList eventList = createTestEventBatch();
		eventReceiver.setEvents(eventList);
		
		ETSortedEventList emptyeventList = new ETSortedEventList();
		eventReceiver.setEvents(emptyeventList);
		
		assertNull(eventReceiver.getEvent());
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
