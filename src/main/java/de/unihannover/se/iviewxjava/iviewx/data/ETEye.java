package de.unihannover.se.iviewxjava.iviewx.data;

/** Eye of one side of the face.
 *  <ul>
 *    <li>{@link #LEFT}</li>
 *    <li>{@link #RIGHT}</li>
 *  </ul>
 * 
 * @author Luca Fuelbier
 *
 */
public enum ETEye {
	
	/** Left eye */
	LEFT,
	
	/** Right eye */
	RIGHT;
	
	/** Converts a ASCII encoded character to the corresponding enum value.
	 *  <p>
	 *  Only the characters 'l' and 'r' are allowed.
	 *  Other characters are invalid and will throw an exception.
	 * 
	 * @param b ASCII codepoint representing a single character
	 * @return {@link ETEye} enum value that corresponds to the provided character
	 * @throws IllegalArgumentException If a invalid character was provided
	 */
	public static ETEye fromByte(byte b) throws IllegalArgumentException {
		
		// Converts a byte (signed) to a char (unsigned)
		char eyeCharacter = (char)(b & 0xFF);
		
		return ETEye.fromChar(eyeCharacter);
	}
	
	/** Converts a character to the corresponding enum value.
	 *  <p>
	 *  Only the characters 'l' and 'r' are allowed.
	 *  Other characters are invalid and will throw an exception.
	 * 
	 * @param c Character
	 * @return {@link ETEye} enum value that corresponds to the provided character
	 * @throws IllegalArgumentException If a invalid character was provided
	 */
	public static ETEye fromChar(char c) throws IllegalArgumentException {
		switch(c)
		{
			case 'l':
				return LEFT;
			case 'r':
				return RIGHT;
			default:
				throw new IllegalArgumentException("Error: Could not convert byte to ETEye enum value. Invalid byte value.");
		}
	}
}
