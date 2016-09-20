package data;

public enum ETEye {
	LEFT,
	RIGHT;
	
	public static ETEye fromByte(byte b) throws IllegalArgumentException {
		
		char eyeCharacter = (char)(b & 0xFF);
		
		switch(eyeCharacter)
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
