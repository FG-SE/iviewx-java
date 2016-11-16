package service;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import static org.junit.Assert.*;

import sample.ETSample;
import exception.ETBadFormatException;
import generic.ETChronologicCollection;

import java.io.File;
import java.util.List;
import java.util.ArrayList;

public class ETSortedSampleLoaderTest {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void fromTextFile_correctFormatting_loadsAllSamples() throws Exception {
		File sampleFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testsamples_goodformat.txt").toURI());
		ETChronologicCollection<ETSample> sampleCollection = ETSortedSampleLoader.fromTextFile(sampleFile);
		List<ETSample> sampleList = new ArrayList<>(sampleCollection);

		assertEquals(2, sampleCollection.size());
		
		ETSample sample1 = sampleList.get(0);
		ETSample sample2 = sampleList.get(1);
		final double DELTA = 1e-9;
		
		assertEquals(1.1, sample1.getLeftEye().getDiameter(), 		DELTA);
		assertEquals(1.1, sample1.getLeftEye().getEyePositionX(), 	DELTA);
		assertEquals(1.1, sample1.getLeftEye().getEyePositionY(), 	DELTA);
		assertEquals(1.1, sample1.getLeftEye().getEyePositionZ(), 	DELTA);
		assertEquals(1.1, sample1.getLeftEye().getGazeX(), 			DELTA);
		assertEquals(1.1, sample1.getLeftEye().getGazeY(), 			DELTA);
		
		assertEquals(2.2, sample1.getRightEye().getDiameter(), 		DELTA);
		assertEquals(2.2, sample1.getRightEye().getEyePositionX(), 	DELTA);
		assertEquals(2.2, sample1.getRightEye().getEyePositionY(), 	DELTA);
		assertEquals(2.2, sample1.getRightEye().getEyePositionZ(), 	DELTA);
		assertEquals(2.2, sample1.getRightEye().getGazeX(), 		DELTA);
		assertEquals(2.2, sample1.getRightEye().getGazeY(), 		DELTA);
		
		assertEquals(10, sample1.getTimestamp());
		
		assertEquals(3, sample2.getLeftEye().getDiameter(), 		DELTA);
		assertEquals(3, sample2.getLeftEye().getEyePositionX(), 	DELTA);
		assertEquals(3, sample2.getLeftEye().getEyePositionY(), 	DELTA);
		assertEquals(3, sample2.getLeftEye().getEyePositionZ(), 	DELTA);
		assertEquals(3, sample2.getLeftEye().getGazeX(), 			DELTA);
		assertEquals(3, sample2.getLeftEye().getGazeY(), 			DELTA);
		
		assertEquals(4, sample2.getRightEye().getDiameter(), 		DELTA);
		assertEquals(4, sample2.getRightEye().getEyePositionX(), 	DELTA);
		assertEquals(4, sample2.getRightEye().getEyePositionY(), 	DELTA);
		assertEquals(4, sample2.getRightEye().getEyePositionZ(), 	DELTA);
		assertEquals(4, sample2.getRightEye().getGazeX(), 			DELTA);
		assertEquals(4, sample2.getRightEye().getGazeY(), 			DELTA);
		
		assertEquals(20, sample2.getTimestamp());
	}
	
	@Test
	public void fromTextFile_notEnoughFields_throwsException() throws Exception {
		File sampleFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testsamples_not_enough_fields.txt").toURI());
		
		exception.expect(ETBadFormatException.class);
		
		ETSortedSampleLoader.fromTextFile(sampleFile);
	}
	
	@Test
	public void fromTextFile_unparsableFields_throwsException() throws Exception {
		File sampleFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testsamples_unparsable_fields.txt").toURI());
		
		exception.expect(ETBadFormatException.class);
		
		ETSortedSampleLoader.fromTextFile(sampleFile);
	}
	
	@Test
	public void fromTextFile_comments_commentsAreIgnored() throws Exception {
		File sampleFile = new File(ClassLoader.getSystemClassLoader().getResource("./service/testsamples_comments.txt").toURI());
		ETChronologicCollection<ETSample> samples = ETSortedSampleLoader.fromTextFile(sampleFile);
		
		assertEquals(2, samples.size());
	}
	
}
