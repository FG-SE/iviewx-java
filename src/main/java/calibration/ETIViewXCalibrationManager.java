package calibration;

import exception.ETErrorHandler;
import iviewxapi.IViewXAPILibrary;

public class ETIViewXCalibrationManager implements ETCalibrationManager {
	
	private IViewXAPILibrary iView;
	
	public ETIViewXCalibrationManager(IViewXAPILibrary lib) {
		iView = lib;
	}
	
	@Override
	public void calibrate() {
		int status = iView.iV_Calibrate();
		ETErrorHandler.handle(status);
	}
	
}
