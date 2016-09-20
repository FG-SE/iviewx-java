package event;

import iviewxapi.IViewXAPILibrary;
import exception.ETErrorHandler;
import iviewxapi.EventStruct;
import data.ETEye;

public class ETIViewXEventReceiver implements ETEventReceiver {
	
	private IViewXAPILibrary iView;
	
	private EventStruct eventStruct;
	
	public ETIViewXEventReceiver(IViewXAPILibrary lib) {
		iView = lib;
		eventStruct = new EventStruct();
	}

	@Override
	public ETEvent getEvent() {
		int status = iView.iV_GetEvent(eventStruct);
		ETErrorHandler.handle(status);
		return structToEvent(eventStruct);
	}
	
	private ETEvent structToEvent(EventStruct struct) {
		return new ETEvent(struct.startTime, struct.endTime,
						   ETEye.fromByte(struct.eye),
						   struct.positionX, struct.positionY);
	}
	
}
