package event;

import iviewxapi.IViewXAPILibrary;
import exception.ETErrorHandler;
import iviewxapi.EventStruct;
import data.ETEye;

/** Receives eyetracking events from the RED-m eyetracker.
 *  <p>
 *  Please use the IViewX class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETIViewXEventReceiver implements ETEventReceiver {
	
	private IViewXAPILibrary iView;
	
	private EventStruct eventStruct;
	
	/** Constructs a new IView X EventReceiver that uses the provided IView X SDK binding.
	 * 
	 *  @param lib IView X SDK binding for eyetracker communication
	 */
	public ETIViewXEventReceiver(IViewXAPILibrary lib) {
		iView = lib;
		eventStruct = new EventStruct();
	}

	/** Retrieves a single eyetracking event from the RED-m eyetracker.
	 *  <p>
	 *  Fast successive calls may return the same event if no new fixation 
	 *  event has been registered in the meantime.
	 * 
	 *  @return Eyetracking event
	 */
	@Override
	public ETEvent getEvent() {
		int status = iView.iV_GetEvent(eventStruct);
		ETErrorHandler.handle(status);
		return structToEvent(eventStruct);
	}
	
	/** Converts the information of an EventStruct to an ETEvent.
	 * 
	 *  @param struct EventStruct containing event information
	 *  @return Eyetracking event
	 */
	private ETEvent structToEvent(EventStruct struct) {
		return new ETEvent(struct.startTime, struct.endTime,
						   ETEye.fromByte(struct.eye),
						   struct.positionX, struct.positionY);
	}
	
}
