package generic;

import org.junit.Test;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import generic.ChronologicComparable;
import generic.ETChronologicCollection;

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
	public void next_emptyElementCollection_throwsException() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = new ETChronologicCollection<>();
		receiver.setElements(elementCollection);

		thrown.expect(NoSuchElementException.class);
		receiver.next();
	}
	
	@Test
	public void next_elementCollection_returnsEveryElement() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);
		
		List<Element> expectedList = new ArrayList<>(elementCollection);

		assertSame(receiver.next(), expectedList.get(0));
		assertSame(receiver.next(), expectedList.get(1));
		assertSame(receiver.next(), expectedList.get(2));
	}
	
	@Test
	public void next_elementCollection_throwsExceptionAfterCollectionDepleted() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		assertNotNull(receiver.next());
		assertNotNull(receiver.next());
		assertNotNull(receiver.next());

		thrown.expect(NoSuchElementException.class);
		receiver.next();
	}
	
	@Test
	public void next_elementCollectionDepletedResetCalled_returnsEveryElement() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		assertNotNull(receiver.next());
		assertNotNull(receiver.next());
		assertNotNull(receiver.next());

		receiver.reset();

		List<Element> expectedList = new ArrayList<>(elementCollection);
		
		assertSame(receiver.next(), expectedList.get(0));
		assertSame(receiver.next(), expectedList.get(1));
		assertSame(receiver.next(), expectedList.get(2));
	}
	
	@Test
	public void next_setNewElementCollection_returnsEveryElement() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		ETChronologicCollection<Element> newElementCollection = createTestCollection();
		receiver.setElements(newElementCollection);
		
		List<Element> expectedList = new ArrayList<>(newElementCollection);

		assertSame(receiver.next(), expectedList.get(0));
		assertSame(receiver.next(), expectedList.get(1));
		assertSame(receiver.next(), expectedList.get(2));
	}
	
	@Test
	public void hasNext_emptyElementCollection_returnsFalse() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = new ETChronologicCollection<>();
		receiver.setElements(elementCollection);

		assertFalse(receiver.hasNext());
	}
	
	@Test
	public void hasNext_elementCollection_returnsTrue() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		assertTrue(receiver.hasNext());
	}
	
	@Test
	public void next_elementCollection_returnsFalseAfterCollectionDepleted() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		assertNotNull(receiver.next());
		assertNotNull(receiver.next());
		assertNotNull(receiver.next());

		assertFalse(receiver.hasNext());
	}
	
	@Test
	public void hasNext_elementCollectionDepletedResetCalled_returnsTrue() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		assertNotNull(receiver.next());
		assertNotNull(receiver.next());
		assertNotNull(receiver.next());
		
		receiver.reset();

		assertTrue(receiver.hasNext());
	}
	
	@Test
	public void hasNext_setNewElementCollection_returnsTrue() {
		ETPlaybackReceiver<Element> receiver = new ETPlaybackReceiver<>();
		ETChronologicCollection<Element> elementCollection = createTestCollection();
		receiver.setElements(elementCollection);

		ETChronologicCollection<Element> newElementCollection = createTestCollection();
		receiver.setElements(newElementCollection);

		assertTrue(receiver.hasNext());
	}

	private ETChronologicCollection<Element> createTestCollection() {
		ETChronologicCollection<Element> elements = new ETChronologicCollection<>();
		elements.add(element0);
		elements.add(element1);
		elements.add(element2);
		return elements;
	}
}
