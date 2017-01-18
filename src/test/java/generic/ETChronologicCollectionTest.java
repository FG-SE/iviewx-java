package generic;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

import generic.ChronologicComparable;
import generic.ETChronologicCollection;

public class ETChronologicCollectionTest {
	
	private interface Element extends ChronologicComparable<Element> {}
	
	private Element element0;
	private Element element1;
	private Element element2;
	
	@Before
	public void setup() {
		element0 = mock(Element.class);
		element1 = mock(Element.class);
		element2 = mock(Element.class);
		
		when(element0.chrCompareTo(element0)).thenReturn(0);
		when(element0.chrCompareTo(element1)).thenReturn(-1);
		when(element0.chrCompareTo(element2)).thenReturn(-1);
		
		when(element1.chrCompareTo(element0)).thenReturn(1);
		when(element1.chrCompareTo(element1)).thenReturn(0);
		when(element1.chrCompareTo(element2)).thenReturn(-1);
		
		when(element2.chrCompareTo(element0)).thenReturn(1);
		when(element2.chrCompareTo(element1)).thenReturn(1);
		when(element2.chrCompareTo(element2)).thenReturn(0);
	}
	
	@Test
	public void add_addingElementsInOrder_listIsChronologicallySorted() {
		ETChronologicCollection<Element> ccol = new ETChronologicCollection<>();
		
		ccol.add(element0);
		ccol.add(element1);
		ccol.add(element2);
		
		List<Element> elements = new ArrayList<>(ccol);
		
		assertEquals(element0, elements.get(0));
		assertEquals(element1, elements.get(1));
		assertEquals(element2, elements.get(2));
	}
	
	@Test
	public void add_addingElementsOutOfOrder_listIsChronologicallySorted() {
		ETChronologicCollection<Element> ccol = new ETChronologicCollection<>();
		
		ccol.add(element0);
		ccol.add(element2);
		ccol.add(element1);
		
		List<Element> elements = new ArrayList<>(ccol);
		
		assertEquals(element0, elements.get(0));
		assertEquals(element1, elements.get(1));
		assertEquals(element2, elements.get(2));
	}
	
	@Test
	public void add_addingElementsInReverseOrder_listIsChronologicallySorted() {
		ETChronologicCollection<Element> ccol = new ETChronologicCollection<>();
		
		ccol.add(element2);
		ccol.add(element1);
		ccol.add(element0);
		
		List<Element> elements = new ArrayList<>(ccol);
		
		assertEquals(element0, elements.get(0));
		assertEquals(element1, elements.get(1));
		assertEquals(element2, elements.get(2));
	}

}
