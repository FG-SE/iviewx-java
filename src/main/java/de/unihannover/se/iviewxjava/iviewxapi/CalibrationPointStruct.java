package de.unihannover.se.iviewxjava.iviewxapi;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * @struct CalibrationPointStruct
<br>
 * <br>
 * @brief This struct provides information about the position of calibration points. To
<br>
 * update information in @ref CalibrationPointStruct during a calibration or validation
<br>
 * use function @ref iV_GetCurrentCalibrationPoint. Before or after the calibration use
<br>
 * @ref iV_GetCalibrationPoint.<br>
 * <i>native declaration : C:\Program Files\SMI\iView X SDK\include\iViewXAPI.h:403</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class CalibrationPointStruct extends Structure {
	public int number;
	public int positionX;
	public int positionY;
	public CalibrationPointStruct() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("number", "positionX", "positionY");
	}
	public CalibrationPointStruct(int number, int positionX, int positionY) {
		super();
		this.number = number;
		this.positionX = positionX;
		this.positionY = positionY;
	}
	public CalibrationPointStruct(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends CalibrationPointStruct implements Structure.ByReference {
		
	};
	public static class ByValue extends CalibrationPointStruct implements Structure.ByValue {
		
	};
}
