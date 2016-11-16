package sample;

import org.junit.Test;
import static org.junit.Assert.*;

import sample.ETSample;
import eye.ETEyeData;

public class ETSampleTest {

	@Test
	public void compareTo_otherSampleLater_returnsNegativeInt() {
		ETSample sampleEarly = createSample(0);
		ETSample sampleLate = createSample(10);
		
		assertTrue(sampleEarly.compareTo(sampleLate) < 0);
	}
	
	@Test
	public void compareTo_otherSampleSameTime_returnsZero() {
		ETSample sample1 = createSample(10);
		ETSample sample2 = createSample(10);
		
		assertTrue(sample1.compareTo(sample2) == 0);
	}
	
	@Test
	public void compareTo_otherSampleEarlier_returnsPositiveInt() {
		ETSample sampleLate = createSample(10);
		ETSample sampleEarly = createSample(0);
		
		assertTrue(sampleLate.compareTo(sampleEarly) > 0);
	}
	
	private ETSample createSample(long timestamp) {
		return new ETSample(ETEyeData.createDefault(), ETEyeData.createDefault(), timestamp);
	}
}
