package sample;

import org.junit.Test;

import eye.ETEyeData;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import sample.ETSample;
import sample.ETSortedSampleList;
import sample.experimental.ETTimedSampleReceiver;
import sample.experimental.ETTimer;

public class ETTimedSampleReceiverTest {
	
	@Test
	public void getSample_noSampleBatch_returnsNull() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_emptySampleBatch_returnsNull() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_emptySampleBatch_subsequentCallsReturnNull() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertNull(sampleReceiver.getSample());
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_orderedSampleBatch_returnsEverySample() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertSame(sampleReceiver.getSample(), sampleList.get(0));
		assertSame(sampleReceiver.getSample(), sampleList.get(1));
		assertSame(sampleReceiver.getSample(), sampleList.get(2));
	}
	
	@Test
	public void getSample_orderedSampleBatch_returnsNullAfterBatchDepleted() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertNotNull(sampleReceiver.getSample());
		assertNotNull(sampleReceiver.getSample());
		assertNotNull(sampleReceiver.getSample());
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_orderedSampleBatchDepletedResetCalled_returnsEverySample() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
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
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertNull(sampleReceiver.getSample());
	}
	
	@Test
	public void getSample_setNewOrderedSampleBatch_returnsEverySample() {
		ETTimedSampleReceiver sampleReceiver = new ETTimedSampleReceiver(createTimerMock());
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		ETSortedSampleList newSampleList = createTestSampleBatch();
		sampleReceiver.setSamples(newSampleList);
		
		assertSame(sampleReceiver.getSample(), newSampleList.get(0));
		assertSame(sampleReceiver.getSample(), newSampleList.get(1));
		assertSame(sampleReceiver.getSample(), newSampleList.get(2));
	}
	
	private ETTimer createTimerMock() {
		ETTimer timer = mock(ETTimer.class);
		
		// Timestamps
		long ts0 = 0;
		long ts1 = 1;
		long ts2 = 2;
		long ts3 = 3;
		long ts4 = 4;
		long ts5 = 5;
		long ts6 = 6;
		long ts7 = 7;
		
		when(timer.getTimeMicro())
			.thenReturn(ts0)
			.thenReturn(ts1)
			.thenReturn(ts2)
			.thenReturn(ts3)
			.thenReturn(ts4)
			.thenReturn(ts5)
			.thenReturn(ts6)
			.thenReturn(ts7);
		
		return timer;
	}
	
	private ETSortedSampleList createTestSampleBatch() {
		ETSortedSampleList samples = new ETSortedSampleList();
		ETSample sample1 = new ETSample(new ETEyeData(1,1,1,1,1,1), new ETEyeData(1,1,1,1,1,1), 1);
		ETSample sample2 = new ETSample(new ETEyeData(2,2,2,2,2,2), new ETEyeData(2,2,2,2,2,2), 2);
		ETSample sample3 = new ETSample(new ETEyeData(3,3,3,3,3,3), new ETEyeData(3,3,3,3,3,3), 3);
		samples.addIgnore(sample1);
		samples.addIgnore(sample2);
		samples.addIgnore(sample3);
		return samples;	
	}
}
