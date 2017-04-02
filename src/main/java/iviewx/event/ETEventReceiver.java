package iviewx.event;

import iviewxapi.IViewXAPILibrary;
import core.receiver.ETReceiver;
import core.receiver.response.ETResponse;
import core.receiver.response.ETResponseType;
import core.receiver.stabilization.ETStabilizationStrategy;
import exception.ETErrorHandler;
import iviewx.eye.ETEye;
import iviewxapi.EventStruct;

/** Receives eyetracking events from the RED-m eyetracker.
 *  <p>
 *  Please use the {@link iviewx.IViewX} class as a central access point.
 * 
 *  @author Luca Fuelbier
 */
public class ETEventReceiver extends ETReceiver<ETEvent> {
	
	private IViewXAPILibrary iView;
	
	private EventStruct eventStruct;
	
	/** Constructs a new IView X EventReceiver that uses the provided IView X SDK binding.
	 * 
	 *  @param lib IView X SDK binding for eyetracker communication
	 */
	public ETEventReceiver(IViewXAPILibrary lib) {
		super();
		iView = lib;
		eventStruct = new EventStruct();
	}
	
	/** Constructs a new IView X SampleReceiver that uses the provided IView X SDK binding 
	 *  and the provided stabilization strategy.
	 * 
	 * @param lib IView X SDK binding for eyetracker communication
	 * @param strategy Sample stabilization strategy
	 */
	public ETEventReceiver(IViewXAPILibrary lib, ETStabilizationStrategy<ETEvent> strategy) {
		super(strategy);
		iView = lib;
		eventStruct = new EventStruct();
	}
	
	/** Retrieves a single eyetracking event from the RED-m eyetracker,
	 *  wrapped in a {@link core.receiver.response.ETResponse}.
	 *  <p>
	 *  If no new data is available, this method will return <strong>null</strong>
	 *  as the responses data.
	 *  {@link core.receiver.response.ETResponseType#NO_NEW_DATA_AVAILABLE NO_NEW_DATA_AVAILABLE}
	 *  indicates one of the following scenarios:
	 *  <ul>
	 *    <li>There is no new data available because the eyetracker has not computed new data yet</li>
	 *    <li>There is no new data available because a currently occurring fixation has not yet ended</li>
	 *    <li>The user is not fixating anything (eyes are moving too rapidly)</li>
	 *    <li>The users gaze is not in tracking range</li>
	 *    <li>The user is not properly tracked by the eyetracker</li>
	 *  </ul>
	 *  <p>
	 *  If your eyetracking environment is properly set up, the most likely reason is that
	 *  your application is polling faster than the eyetrackers refresh rate.
	 *  Simply keep polling until a new event is registered, or introduce wait times
	 *  between consecutive polls.
	 * 
	 *  @return Eyetracking event
	 *  
	 *  @throws exception.ETException If an error occured while retrieving a new event
	 *  @throws exception.ETConnectionException If no connection could be established to the eyetracker
	 */
	@Override
	protected ETResponse<ETEvent> getNextFromSource() {
		int status = iView.iV_GetEvent(eventStruct);
		
		if(status == IViewXAPILibrary.RET_NO_VALID_DATA)
			return new ETResponse<ETEvent>(ETResponseType.NO_NEW_DATA_AVAILABLE, null);
		else
			ETErrorHandler.handle(status);
		
		ETEvent receivedEvent = structToEvent(eventStruct);
		return new ETResponse<ETEvent>(ETResponseType.NEW_DATA, receivedEvent);
	}
	
	/** Converts the information of an EventStruct to an ETEvent.
	 * 
	 *  @param struct EventStruct containing event information
	 *  
	 *  @return Eyetracking event
	 */
	private ETEvent structToEvent(EventStruct struct) {
		return new ETEvent(struct.startTime, struct.endTime,
						   ETEye.fromByte(struct.eye),
						   struct.positionX, struct.positionY);
	}
	
}
