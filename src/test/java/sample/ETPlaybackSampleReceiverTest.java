package sample;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;
import java.util.List;
import java.util.ArrayList;

import eye.ETEyeData;
import generic.ETChronologicCollection;
import sample.ETPlaybackSampleReceiver;
import sample.ETSample;

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
		ETChronologicCollection<ETSample> sampleCollection = new ETChronologicCollection<>();
		sampleReceiver.setSamples(sampleCollection);

		thrown.expect(NoSuchElementException.class);
		sampleReceiver.next();
	}

	@Test
	public void next_orderedSampleBatch_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		List<ETSample> sampleList = new ArrayList<>(sampleCollection);
		sampleReceiver.setSamples(sampleCollection);

		assertSame(sampleReceiver.next(), sampleList.get(0));
		assertSame(sampleReceiver.next(), sampleList.get(1));
		assertSame(sampleReceiver.next(), sampleList.get(2));
	}

	@Test
	public void next_orderedSampleBatch_throwsExceptionAfterBatchDepleted() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(sampleCollection);

		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());

		thrown.expect(NoSuchElementException.class);
		sampleReceiver.next();
	}

	@Test
	public void next_orderedSampleBatchDepletedResetCalled_returnsEverySample() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		List<ETSample> sampleList = new ArrayList<>(sampleCollection);
		sampleReceiver.setSamples(sampleCollection);

		for (int i = 0; i < 3; ++i) {
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
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(sampleCollection);

		ETChronologicCollection<ETSample> newSampleCollection = createTestSampleBatch();
		List<ETSample> newSampleList = new ArrayList<>(newSampleCollection);
		sampleReceiver.setSamples(newSampleCollection);

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
		ETChronologicCollection<ETSample> emptySampleCollection = new ETChronologicCollection<>();
		sampleReceiver.setSamples(emptySampleCollection);

		assertFalse(sampleReceiver.hasNext());
	}

	@Test
	public void hasNext_orderedSampleBatch_returnsTrue() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(sampleCollection);

		assertTrue(sampleReceiver.hasNext());
	}

	@Test
	public void next_orderedSampleBatch_returnsFalseAfterBatchDepleted() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(sampleCollection);

		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());
		assertNotNull(sampleReceiver.next());

		assertFalse(sampleReceiver.hasNext());
	}

	@Test
	public void hasNext_orderedSampleBatchDepletedResetCalled_returnsTrue() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(sampleCollection);

		for (int i = 0; i < 3; ++i) {
			sampleReceiver.next();
		}

		sampleReceiver.reset();

		assertTrue(sampleReceiver.hasNext());
	}

	@Test
	public void hasNext_setNewOrderedSampleBatch_returnsTrue() {
		ETPlaybackSampleReceiver sampleReceiver = new ETPlaybackSampleReceiver();
		ETChronologicCollection<ETSample> sampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(sampleCollection);

		ETChronologicCollection<ETSample> newSampleCollection = createTestSampleBatch();
		sampleReceiver.setSamples(newSampleCollection);

		assertTrue(sampleReceiver.hasNext());
	}

	private ETChronologicCollection<ETSample> createTestSampleBatch() {
		ETChronologicCollection<ETSample> samples = new ETChronologicCollection<>();
		ETSample sample1 = new ETSample(new ETEyeData(0, 0, 0, 0, 0, 0), new ETEyeData(0, 0, 0, 0, 0, 0), 0);
		ETSample sample2 = new ETSample(new ETEyeData(1, 1, 1, 1, 1, 1), new ETEyeData(1, 1, 1, 1, 1, 1), 1);
		ETSample sample3 = new ETSample(new ETEyeData(2, 2, 2, 2, 2, 2), new ETEyeData(2, 2, 2, 2, 2, 2), 2);
		samples.add(sample1);
		samples.add(sample2);
		samples.add(sample3);
		return samples;
	}
}
