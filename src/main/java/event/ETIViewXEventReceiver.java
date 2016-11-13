package event;

import iviewxapi.IViewXAPILibrary;
import exception.ETErrorHandler;
import eye.ETEye;
import iviewxapi.EventStruct;
import static iviewxapi.IViewXAPILibrary.RET_NO_VALID_DATA;

/** Receives eyetracking events from the RED-m eyetracker.
 *  <p>
 *  Please use the {@link iviewx.IViewX} class as a central access point.
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
	 *  This method can return <strong>null</strong>, which indicates one of the following scenarios:
	 *  <ul>
	 *    <li>There is no new data available because the eyetracker has not computed new data yet</li>
	 *    <li>There is no new data available because a currently occurring fixation has not yet ended</li>
	 *    <li>The user is not fixating anything (eyes are moving too rapidly)</li>
	 *    <li>The users gaze is not in tracking range</li>
	 *    <li>The user is not properly tracked by the eyetracker</li>
	 *  </ul>
	 *  <p>
	 *  If your eyetracking environment is properly set up, a <strong>null</strong> most likely means that
	 *  your application is polling faster than the eyetrackers refresh rate. Simply keep polling
	 *  until a new event is registered.
	 * 
	 *  @return Eyetracking event
	 *  
	 *  @throws exception.ETException If an error occured while retrieving a new event
	 *  @throws exception.ETConnectionException If no connection could be established to the eyetracker
	 */
	@Override
	public ETEvent getEvent() {
		int status = iView.iV_GetEvent(eventStruct);
		
		if(status == RET_NO_VALID_DATA)
			return null;
		else
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
