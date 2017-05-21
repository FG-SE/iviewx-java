package de.unihannover.se.iviewxjava.iviewxapi;
import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;
/**
 * @struct SystemInfoStruct
<br>
 * <br>
 * @brief This struct provides information about the iView eye tracking server version
<br>
 * and the API version in use. To update data in @ref SystemInfoStruct use the function
<br>
 * @ref iV_GetSystemInfo.<br>
 * <i>native declaration : C:\Program Files\SMI\iView X SDK\include\iViewXAPI.h:347</i><br>
 * This file was autogenerated by <a href="http://jnaerator.googlecode.com/">JNAerator</a>,<br>
 * a tool written by <a href="http://ochafik.com/">Olivier Chafik</a> that <a href="http://code.google.com/p/jnaerator/wiki/CreditsAndLicense">uses a few opensource projects.</a>.<br>
 * For help, please visit <a href="http://nativelibs4java.googlecode.com/">NativeLibs4Java</a> , <a href="http://rococoa.dev.java.net/">Rococoa</a>, or <a href="http://jna.dev.java.net/">JNA</a>.
 */
public class SystemInfoStruct extends Structure {
	public int samplerate;
	public int iV_MajorVersion;
	public int iV_MinorVersion;
	public int iV_Buildnumber;
	public int API_MajorVersion;
	public int API_MinorVersion;
	public int API_Buildnumber;
	/**
	 * @see ETDevice<br>
	 * C type : ETDevice
	 */
	public int iV_ETDevice;
	public SystemInfoStruct() {
		super();
	}
	protected List<? > getFieldOrder() {
		return Arrays.asList("samplerate", "iV_MajorVersion", "iV_MinorVersion", "iV_Buildnumber", "API_MajorVersion", "API_MinorVersion", "API_Buildnumber", "iV_ETDevice");
	}
	/**
	 * @param iV_ETDevice @see ETDevice<br>
	 * C type : ETDevice
	 */
	public SystemInfoStruct(int samplerate, int iV_MajorVersion, int iV_MinorVersion, int iV_Buildnumber, int API_MajorVersion, int API_MinorVersion, int API_Buildnumber, int iV_ETDevice) {
		super();
		this.samplerate = samplerate;
		this.iV_MajorVersion = iV_MajorVersion;
		this.iV_MinorVersion = iV_MinorVersion;
		this.iV_Buildnumber = iV_Buildnumber;
		this.API_MajorVersion = API_MajorVersion;
		this.API_MinorVersion = API_MinorVersion;
		this.API_Buildnumber = API_Buildnumber;
		this.iV_ETDevice = iV_ETDevice;
	}
	public SystemInfoStruct(Pointer peer) {
		super(peer);
	}
	public static class ByReference extends SystemInfoStruct implements Structure.ByReference {
		
	};
	public static class ByValue extends SystemInfoStruct implements Structure.ByValue {
		
	};
}