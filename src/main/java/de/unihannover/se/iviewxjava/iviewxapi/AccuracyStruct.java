package de.unihannover.se.iviewxjava.iviewxapi;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * @struct AccuracyStruct
<br>
 * <br>
 * @brief This struct provides information about the last validation.
<br>
 * Therefore a valid validation must be successfully completed before the AccuracyStruct can be
<br>
 * updated. To update information in @ref AccuracyStruct use function @ref iV_GetAccuracy.<br>
 * <i>native declaration : C:\Program Files\SMI\iView X SDK\include\iViewXAPI.h:669</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class AccuracyStruct extends Structure {
	public double deviationLX;
	public double deviationLY;
	public double deviationRX;
	public double deviationRY;
	public AccuracyStruct() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("deviationLX", "deviationLY", "deviationRX", "deviationRY");
	}
	public AccuracyStruct(double deviationLX, double deviationLY, double deviationRX, double deviationRY) {
		super();
		this.deviationLX = deviationLX;
		this.deviationLY = deviationLY;
		this.deviationRX = deviationRX;
		this.deviationRY = deviationRY;
	}
	public AccuracyStruct(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends AccuracyStruct implements Structure.ByReference {
		
	};
	public static class ByValue extends AccuracyStruct implements Structure.ByValue {
		
	};
}
