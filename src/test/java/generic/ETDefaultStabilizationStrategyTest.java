package generic;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ETDefaultStabilizationStrategyTest {
	
	private interface Element extends ChronologicComparable<Element> {}
	
	@Test
	public void stabilize_elementIsPassedThroughUnchanged() {
		ETDefaultStabilizationStrategy<Element> strategy = new ETDefaultStabilizationStrategy<>();
		Element element = mock(Element.class);
		
		assertEquals(element, strategy.stabilize(element));
	}

}
