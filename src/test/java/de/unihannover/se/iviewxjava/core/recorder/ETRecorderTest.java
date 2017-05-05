package de.unihannover.se.iviewxjava.core.recorder;

import org.junit.Before;
import org.junit.Test;

import de.unihannover.se.iviewxjava.core.chronologic.ChronologicComparable;
import de.unihannover.se.iviewxjava.core.receiver.ETReceiver;
import de.unihannover.se.iviewxjava.core.receiver.response.ETResponse;
import de.unihannover.se.iviewxjava.core.receiver.response.ETResponseType;
import de.unihannover.se.iviewxjava.core.recorder.ETRecorder;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.awaitility.Awaitility.*;

public class ETRecorderTest {
	
	private interface Element extends ChronologicComparable<Element> {}
	private abstract class ElementReceiver extends ETReceiver<Element> {}
	
	private Element element0;
	private Element element1;
	
	private ETResponse<Element> responseNew0;
	private ETResponse<Element> responseNew1;
	private ETResponse<Element> responseNoNew;
	private ETResponse<Element> responseDepleted;
	
	@Before
	public void setup() {
		element0 = mock(Element.class);
		element1 = mock(Element.class);
		
		responseNew0 	 = new ETResponse<>(ETResponseType.NEW_DATA, element0);
		responseNoNew    = new ETResponse<>(ETResponseType.NO_NEW_DATA_AVAILABLE, null);
		responseNew1     = new ETResponse<>(ETResponseType.NEW_DATA, element1);
		responseDepleted = new ETResponse<>(ETResponseType.SOURCE_DEPLETED, null);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void recording_finiteSource_recordsElementsUntilDepleted() {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted);
		
		ETRecorder<Element> recorder = new ETRecorder<>(receiver);
		recorder.startRecording();
		
		await().until(() -> recorder.isRunning() == false);
		
		List<Element> recordedElements = new ArrayList<>(recorder.getRecordedData());
		
		assertEquals(2, recordedElements.size());
		assertEquals(element0, recordedElements.get(0));
		assertEquals(element1, recordedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void recording_fixedPollrate_finiteSource_recordsElementsUntilDepleted() {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted);
		
		ETRecorder<Element> recorder = new ETRecorder<>(receiver);
		recorder.startRecording(1);
		
		await().until(() -> recorder.isRunning() == false);
		
		List<Element> recordedElements = new ArrayList<>(recorder.getRecordedData());
		
		assertEquals(2, recordedElements.size());
		assertEquals(element0, recordedElements.get(0));
		assertEquals(element1, recordedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void recording_finiteSourceWithNoNew_recordsElementsUntilDepletedAndSkipsNoNew() {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNoNew, responseNew1, responseNoNew, responseDepleted);
		
		ETRecorder<Element> recorder = new ETRecorder<>(receiver);
		recorder.startRecording();
		
		await().until(() -> recorder.isRunning() == false);
		
		List<Element> recordedElements = new ArrayList<>(recorder.getRecordedData());
		
		assertEquals(2, recordedElements.size());
		assertEquals(element0, recordedElements.get(0));
		assertEquals(element1, recordedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void recording_fixedPollrate_finiteSourceWithNoNew_recordsElementsUntilDepletedAndSkipsNoNew() {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNoNew, responseNew1, responseNoNew, responseDepleted);
		
		ETRecorder<Element> recorder = new ETRecorder<>(receiver);
		recorder.startRecording(1);
		
		await().until(() -> recorder.isRunning() == false);
		
		List<Element> recordedElements = new ArrayList<>(recorder.getRecordedData());
		
		assertEquals(2, recordedElements.size());
		assertEquals(element0, recordedElements.get(0));
		assertEquals(element1, recordedElements.get(1));
	}

}
