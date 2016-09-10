package validation;

import exception.ETErrorHandler;
import iviewxapi.IViewXAPILibrary;

public class ETIViewXValidationManager implements ETValidationManager {

	private IViewXAPILibrary iView;
	
	public ETIViewXValidationManager(IViewXAPILibrary lib) {
		iView = lib;
	}
	
	@Override
	public void validate() {
		int status = iView.iV_Validate();
		ETErrorHandler.handle(status);
	}
	
}
