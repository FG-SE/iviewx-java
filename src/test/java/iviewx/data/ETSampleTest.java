package iviewx.data;

import org.junit.Test;
import static org.junit.Assert.*;

import iviewx.data.ETEyeData;
import iviewx.data.ETSample;

public class ETSampleTest {

	@Test
	public void chrCompareTo_otherSampleLater_returnsNegativeInt() {
		ETSample sampleEarly = createSample(0);
		ETSample sampleLate = createSample(10);
		
		assertTrue(sampleEarly.chrCompareTo(sampleLate) < 0);
	}
	
	@Test
	public void chrCompareTo_otherSampleSameTime_returnsZero() {
		ETSample sample1 = createSample(10);
		ETSample sample2 = createSample(10);
		
		assertTrue(sample1.chrCompareTo(sample2) == 0);
	}
	
	@Test
	public void chrCompareTo_otherSampleEarlier_returnsPositiveInt() {
		ETSample sampleLate = createSample(10);
		ETSample sampleEarly = createSample(0);
		
		assertTrue(sampleLate.chrCompareTo(sampleEarly) > 0);
	}
	
	private ETSample createSample(long timestamp) {
		return new ETSample(ETEyeData.createDefault(), ETEyeData.createDefault(), timestamp);
	}
}
