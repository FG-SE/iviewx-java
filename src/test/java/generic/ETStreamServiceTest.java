package generic;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.Flowable;
import io.reactivex.subscribers.TestSubscriber;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.List;


public class ETStreamServiceTest {
	
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
	public void start_finiteSource_publishesAllElements() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.start();
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(2, receivedElements.size());
		assertEquals(element0, receivedElements.get(0));
		assertEquals(element1, receivedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void start_fixedPollrate_finiteSource_publishesAllElements() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.start(1);
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(2, receivedElements.size());
		assertEquals(element0, receivedElements.get(0));
		assertEquals(element1, receivedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void start_finiteSourceWithNoNew_skipsNoNewAndPublishesAllElements() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNoNew, responseNew1, responseNoNew, responseDepleted);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.start();
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(2, receivedElements.size());
		assertEquals(element0, receivedElements.get(0));
		assertEquals(element1, receivedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void start_fixedPollrate_finiteSourceWithNoNew_skipsNoNewAndPublishesAllElements() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNoNew, responseNew1, responseNoNew, responseDepleted);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.start(1);
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(2, receivedElements.size());
		assertEquals(element0, receivedElements.get(0));
		assertEquals(element1, receivedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void start_finiteSourceWithElementAfterDepleted_finishesPollingAfterDepleted() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted, responseNew0);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.start();
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(2, receivedElements.size());
		assertEquals(element0, receivedElements.get(0));
		assertEquals(element1, receivedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void start_fixedPollrate_finiteSourceWithElementAfterDepleted_finishesPollingAfterDepleted() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted, responseNew0);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.start(1);
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(2, receivedElements.size());
		assertEquals(element0, receivedElements.get(0));
		assertEquals(element1, receivedElements.get(1));
	}
	
	@Test
	@SuppressWarnings("unchecked")
	public void shutdown_finiteSource_immediatelyPublishesOnComplete() throws InterruptedException {
		ElementReceiver receiver = mock(ElementReceiver.class);
		when(receiver.getNext()).thenReturn(responseNew0, responseNew1, responseDepleted);
		
		ETStreamService<Element> streamService = new ETStreamService<>(receiver);
		Flowable<Element> stream = streamService.getStream();
		
		TestSubscriber<Element> testSubscriber = new TestSubscriber<>();
		stream.subscribe(testSubscriber);
		
		streamService.shutdown();
		testSubscriber.await();
		
		testSubscriber.assertNoErrors();
		testSubscriber.assertComplete();
		
		List<Element> receivedElements = testSubscriber.values();
		
		assertEquals(0, receivedElements.size());
	}

}
