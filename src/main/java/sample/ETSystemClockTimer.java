package sample;

public class ETSystemClockTimer implements ETTimer{

	@Override
	public long getTime() {
		return System.currentTimeMillis();
	}
	
}
