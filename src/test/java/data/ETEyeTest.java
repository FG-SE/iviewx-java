package data;

import org.junit.Test;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import eye.ETEye;

import static org.junit.Assert.*;

public class ETEyeTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void fromByte_correctByteSupplied_returnsCorrespondingEnumValue() {
		byte l = 108;
		byte r = 114;
		
		assertEquals(ETEye.LEFT, ETEye.fromByte(l));
		assertEquals(ETEye.RIGHT, ETEye.fromByte(r));
	}
	
	@Test
	public void fromByte_incorrectByteSupplied_throwsException() {
		byte a = 97;
		
		thrown.expect(IllegalArgumentException.class);
		ETEye.fromByte(a);
	}

}
