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
	 *  <p>
	 *  To check if there are still events available, call {@link #hasNext() hasNext}.
	 * 
	 *  @return Eyetracking event
	 *  
	 *  @throws exception.ETException If an error occured while retrieving a new event
	 *  @throws exception.ETConnectionException If no connection could be established to the eyetracker
	 */
	@Override
	public ETEvent next() {
		int status = iView.iV_GetEvent(eventStruct);
		
		if(status == RET_NO_VALID_DATA)
			return null;
		else
			ETErrorHandler.handle(status);
		
		return structToEvent(eventStruct);
	}
	
	/** Returns <em>true</em> if the event receiver has more samples.
	 *  <p>
	 *  Since the eyetracker can produce events endlessly, this method will always return true.
	 *  A return of <em>true</em> does not however indicate that new data is available.
	 *  It only indicates that the eyetracker is trying to retrieve a new event at the moment.
	 *  <p>
	 *  This behavior might change in a later release of the eyetracking library, for example by 
	 *  supporting pausing and resuming the eyetracking process.
	 * 
	 *  @return <em>true</em> if the receiver has more events
	 */
	@Override
	public boolean hasNext() {
		return true;
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
