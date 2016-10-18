package sample;

import org.junit.Test;

import eye.ETEyeData;

import static org.junit.Assert.*;

import sample.ETSample;
import sample.ETSortedSampleList;

public class ETSortedSampleListTest {
	
	@Test
	public void addIgnore_emptyList_insertsSample() {
		ETSortedSampleList samples = new ETSortedSampleList();
		
		ETSample sample = createSample(0);
		samples.addIgnore(sample);
		
		assertSame(sample, samples.get(0));
		assertEquals(1, samples.size());
	}
	
	@Test
	public void addIgnore_samplesInList_insertssampleWithNewerTimestamp() {
		ETSortedSampleList samples = new ETSortedSampleList();
		
		ETSample sample1 = createSample(0);
		ETSample sample2 = createSample(1);
		
		samples.addIgnore(sample1);
		samples.addIgnore(sample2);
		
		assertSame(sample2, samples.get(1));
		assertEquals(2, samples.size());
	}
	
	@Test
	public void addIgnore_samplesInList_ignoressampleWithOlderTimestamp() {
		ETSortedSampleList samples = new ETSortedSampleList();
		
		ETSample sample1 = createSample(1);
		ETSample sample2 = createSample(0);
		ETSample sample3 = createSample(1);
		
		samples.addIgnore(sample1);
		samples.addIgnore(sample2);
		samples.addIgnore(sample3);
		
		assertSame(sample1, samples.get(0));
		assertEquals(1, samples.size());
	}
	
	private ETSample createSample(long timestamp) {
		return new ETSample(ETEyeData.createDefault(), ETEyeData.createDefault(), timestamp);
	}
	
}
