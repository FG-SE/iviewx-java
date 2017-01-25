package event;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import event.ETEvent;
import event.ETPlaybackEventReceiver;
import exception.ETRecordingException;
import event.ETEventRecorder;
import eye.ETEye;
import generic.ETChronologicCollection;

public class ETEventRecorderTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void getRecordedEvents_noStop_finiteReceiverCollection_returnsAllEvents() {
		ETEventReceiver receiver = new ETPlaybackEventReceiver(createEventCollection(3));
		ETEventRecorder recorder = new ETEventRecorder(receiver);
		
		recorder.startRecording();
		ETChronologicCollection<ETEvent> events = recorder.getRecordedEvents();
		
		List<ETEvent> eventList = new ArrayList<>(events);
		
		assertEquals(0, eventList.get(0).getStartTime());
		assertEquals(1, eventList.get(1).getStartTime());
		assertEquals(2, eventList.get(2).getStartTime());
	}
	
	@Test
	public void getRecordedEvents_fixedPollrate_noStop_finiteReceiverCollection_returnsAllEvents() {
		ETEventReceiver receiver = new ETPlaybackEventReceiver(createEventCollection(3));
		ETEventRecorder recorder = new ETEventRecorder(receiver);
		
		recorder.startRecording(5);
		ETChronologicCollection<ETEvent> events = recorder.getRecordedEvents();
		
		List<ETEvent> eventList = new ArrayList<>(events);
		
		assertEquals(0, eventList.get(0).getStartTime());
		assertEquals(1, eventList.get(1).getStartTime());
		assertEquals(2, eventList.get(2).getStartTime());
	}
	
	@Test
	public void getRecordedEvents_noStop_infiniteReceiverCollection_producesTimeout() {
		ETEventReceiver receiver = new InfiniteCollectionReceiver();
		ETEventRecorder recorder = new ETEventRecorder(receiver);
		
		thrown.expect(ETRecordingException.class);
		
		recorder.startRecording();
		recorder.getRecordedEvents(50);
	}
	
	@Test
	public void getRecordedEvents_fixedPollrate_noStop_infiniteReceiverCollection_producesTimeout() {
		ETEventReceiver receiver = new InfiniteCollectionReceiver();
		ETEventRecorder recorder = new ETEventRecorder(receiver);
		
		thrown.expect(ETRecordingException.class);
		
		recorder.startRecording(10);
		recorder.getRecordedEvents(50);
	}
	
	@Test
	public void getRecordedEvents_stop_infiniteReceiverCollection_stopsRecording() throws Exception {
		ETEventReceiver receiver = new InfiniteCollectionReceiver();
		ETEventRecorder recorder = new ETEventRecorder(receiver);
		
		recorder.startRecording();
		Thread.sleep(25);
		recorder.stopRecording();
		ETChronologicCollection<ETEvent> events1 = recorder.getRecordedEvents();
		
		assertTrue(events1.size() > 0);
		
		Thread.sleep(25);
		ETChronologicCollection<ETEvent> events2 = recorder.getRecordedEvents();
		
		assertEquals(events1.size(), events2.size());
	}
	
	@Test
	public void getRecordedEvents_fixedPollrate_stop_infiniteReceiverCollection_stopsRecording() throws Exception {
		ETEventReceiver receiver = new InfiniteCollectionReceiver();
		ETEventRecorder recorder = new ETEventRecorder(receiver);
		
		recorder.startRecording(5);
		Thread.sleep(25);
		recorder.stopRecording();
		ETChronologicCollection<ETEvent> events1 = recorder.getRecordedEvents();
		
		assertTrue(events1.size() > 0);
		
		Thread.sleep(25);
		ETChronologicCollection<ETEvent> events2 = recorder.getRecordedEvents();
		
		assertEquals(events1.size(), events2.size());
	}
	
	private ETChronologicCollection<ETEvent> createEventCollection(int size) {
		ETChronologicCollection<ETEvent> events = new ETChronologicCollection<>();
		
		for(int i = 0; i < size; i++) {
			events.add(createEvent(i));
		}
		
		return events;
	}
	
	private ETEvent createEvent(long startTimestamp) {
		return new ETEvent(startTimestamp,
						   startTimestamp + 1,
						   ETEye.LEFT,
						   0, 0);
	}
	
	private class InfiniteCollectionReceiver implements ETEventReceiver {
		private int count = 0;

		@Override
		public boolean hasNext() {
			return true;
		}

		@Override
		public ETEvent next() {
			ETEvent event = createEvent(count);
			count++;
			return event;
		}
	}

}
