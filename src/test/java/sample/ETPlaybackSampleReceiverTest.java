package sample;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import eye.ETEyeData;

import sample.ETPlaybackSampleReceiver;
import sample.ETSample;
import sample.ETSortedSampleList;

public class ETPlaybackSampleReceiverTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void next_noSampleBatch_throwsException() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		
		thrown.expect(NoSuchElementException.class);
		sampleReceiver.next();
	}

	@Test
	public void next_emptySampleBatch_throwsException() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		thrown.expect(NoSuchElementException.class);
		sampleReceiver.next();
	}
	
	@Test
	public void next_orderedSampleBatch_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertSame(sampleReceiver.next(), sampleList.get(0));
		assertSame(sampleReceiver.next(), sampleList.get(1));
		assertSame(sampleReceiver.next(), sampleList.get(2));
	}
	
	@Test
	public void next_orderedSampleBatch_throwsExceptionAfterBatchDepleted() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());
		
		thrown.expect(NoSuchElementException.class);
		sampleReceiver.next();
	}
	
	@Test
	public void next_orderedSampleBatchDepletedResetCalled_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		for(int i = 0; i < 3; ++i) {
			sampleReceiver.next();
		}
		
		sampleReceiver.reset();
		
		assertSame(sampleReceiver.next(), sampleList.get(0));
		assertSame(sampleReceiver.next(), sampleList.get(1));
		assertSame(sampleReceiver.next(), sampleList.get(2));
	}
	
	@Test
	public void next_setNewOrderedSampleBatch_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		ETSortedSampleList newSampleList = createTestSampleBatch();
		sampleReceiver.setSamples(newSampleList);
		
		assertSame(sampleReceiver.next(), newSampleList.get(0));
		assertSame(sampleReceiver.next(), newSampleList.get(1));
		assertSame(sampleReceiver.next(), newSampleList.get(2));
	}
	
	@Test
	public void hasNext_noSampleBatch_returnsFalse() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		
		assertFalse(sampleReceiver.hasNext());
	}
	
	@Test
	public void hasNext_emptySampleBatch_returnsFalse() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList emptySampleList = new ETSortedSampleList();
		sampleReceiver.setSamples(emptySampleList);
		
		assertFalse(sampleReceiver.hasNext());
	}
	
	@Test
	public void hasNext_orderedSampleBatch_returnsTrue() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertTrue(sampleReceiver.hasNext());
	}
	
	@Test
	public void next_orderedSampleBatch_returnsFalseAfterBatchDepleted() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());
		
		assertFalse(sampleReceiver.hasNext());
	}
	
	@Test
	public void hasNext_orderedSampleBatchDepletedResetCalled_returnsTrue() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		for(int i = 0; i < 3; ++i) {
			sampleReceiver.next();
		}
		
		sampleReceiver.reset();
		
		assertTrue(sampleReceiver.hasNext());
	}
	
	@Test
	public void hasNext_setNewOrderedSampleBatch_returnsTrue() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETSortedSampleList sampleList = createTestSampleBatch();
		sampleReceiver.setSamples(sampleList);
		
		ETSortedSampleList newSampleList = createTestSampleBatch();
		sampleReceiver.setSamples(newSampleList);
		
		assertTrue(sampleReceiver.hasNext());
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
