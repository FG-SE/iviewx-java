package sample;

import org.junit.Test;
import static org.junit.Assert.*;

import data.ETEyeData;
import sample.ETPlaybackSampleReceiver;
import sample.ETSample;
import sample.ETSortedSampleList;

public class ETPlaybackSampleReceiverTest {
	
	@Test
	public void getSample_noSampleBatch_returnsNull() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		
		assertNull(sampleReceiver.getSample());
	}

	@Test
	public void getSample_emptySampleBatch_returnsNull() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_emptySampleBatch_subsequentCallsReturnNull() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertNull(sampleReceiver.getSample());
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_orderedSampleBatch_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertSame(sampleReceiver.getSample(), sampleList.get(0));
		assertSame(sampleReceiver.getSample(), sampleList.get(1));
		assertSame(sampleReceiver.getSample(), sampleList.get(2));
	}
	
	@Test
	public void getSample_orderedSampleBatch_returnsNullAfterBatchDepleted() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertNotNull(sampleReceiver.getSample());
		assertNotNull(sampleReceiver.getSample());
		assertNotNull(sampleReceiver.getSample());
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_orderedSampleBatchDepletedResetCalled_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		for(int i = 0; i < 3; ++i) {
			sampleReceiver.getSample();
		}
		
		sampleReceiver.reset();
		
		assertSame(sampleReceiver.getSample(), sampleList.get(0));
		assertSame(sampleReceiver.getSample(), sampleList.get(1));
		assertSame(sampleReceiver.getSample(), sampleList.get(2));
	}
	
	@Test
	public void getSample_setNewEmptySampleBatch_returnsNull() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_setNewOrderedSampleBatch_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		ETSortedSampleList newSampleList = createTestSampleBatch();
		sampleReceiver.setSamples(newSampleList);
		
		assertSame(sampleReceiver.getSample(), newSampleList.get(0));
		assertSame(sampleReceiver.getSample(), newSampleList.get(1));
		assertSame(sampleReceiver.getSample(), newSampleList.get(2));
	}
	
	private ETSortedSampleList createTestSampleBatch() {
		ETSortedSampleList samples = new ETSortedSampleList();
		ETSample sample1 = new ETSample(new ETEyeData(0,0,0,0,0,0), new ETEyeData(0,0,0,0,0,0), 0);
		ETSample sample2 = new ETSample(new ETEyeData(1,1,1,1,1,1), new ETEyeData(1,1,1,1,1,1), 1);
		ETSample sample3 = new ETSample(new ETEyeData(2,2,2,2,2,2), new ETEyeData(2,2,2,2,2,2), 2);
		samples.addIgnore(sample1);
		samples.addIgnore(sample2);
		samples.addIgnore(sample3);
		return samples;
	}
}
