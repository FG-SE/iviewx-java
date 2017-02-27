package generic;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import generic.ChronologicComparable;
import generic.ETChronologicCollection;
import generic.ETResponse;

import static generic.ETResponseType.*;

public class ETPlaybackReceiverTest {

	private interface Element extends ChronologicComparable<Element> {}

	private Element element0;
	private Element element1;
	private Element element2;

	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Before
	public void setup() {
		element0 = mock(Element.class);
		element1 = mock(Element.class);
		element2 = mock(Element.class);

		when(element1.chrCompareTo(element0)).thenReturn(1);
		when(element2.chrCompareTo(element1)).thenReturn(1);
	}
	
	@Test
	public void getNext_emptyElementCollection_returnsDepletedResponse() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = new ETChronologicCollection<>();
		receiver.setElements(elementCollection);

		ETResponse<Element> response = receiver.getNext();
		
		assertEquals(SOURCE_DEPLETED, response.getType());
		assertNull(response.getData());
	}
	
	@Test
	public void getNext_elementCollection_returnsEveryElement() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);
		
		List<Element> expectedList = new ArrayList<>(elementCollection);
		
		for(Element expectedElement : expectedList) {
			ETResponse<Element> response = receiver.getNext();
			
			assertEquals(NEW_DATA, response.getType());
			assertSame(expectedElement, response.getData());
		}
	}
	
	@Test
	public void getNext_elementCollection_returnsDepletedResponseAfterCollectionDepleted() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);
		
		for(int i = 0; i < 3; i++) {
			ETResponse<Element> response = receiver.getNext();
			assertEquals(NEW_DATA, response.getType());
			assertNotNull(response.getData());
		}

		ETResponse<Element> depletedResponse = receiver.getNext();
		
		assertEquals(SOURCE_DEPLETED, depletedResponse.getType());
		assertNull(depletedResponse.getData());
	}
	
	@Test
	public void getNext_elementCollectionDepletedResetCalled_returnsEveryElement() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		for(int i = 0; i < 3; i++) {
			ETResponse<Element> response = receiver.getNext();
			assertEquals(NEW_DATA, response.getType());
			assertNotNull(response.getData());
		}

		receiver.reset();

		List<Element> expectedList = new ArrayList<>(elementCollection);
		
		for(Element expectedElement : expectedList) {
			ETResponse<Element> response = receiver.getNext();
			
			assertEquals(NEW_DATA, response.getType());
			assertSame(expectedElement, response.getData());
		}
	}
	
	@Test
	public void getNext_setNewElementCollection_returnsEveryElement() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		ETChronologicCollection<Element> newElementCollection = createTestCollection();
		receiver.setElements(newElementCollection);
		
		List<Element> expectedList = new ArrayList<>(newElementCollection);
		
		for(Element expectedElement : expectedList) {
			ETResponse<Element> response = receiver.getNext();
			
			assertEquals(NEW_DATA, response.getType());
			assertSame(expectedElement, response.getData());
		}
	}

	private ETChronologicCollection<Element> createTestCollection() {
		ETChronologicCollection<Element> elements = new ETChronologicCollection<>();
		elements.add(element0);
		elements.add(element1);
		elements.add(element2);
		return elements;
	}
}
