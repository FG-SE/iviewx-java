package event;

import java.util.Iterator;

/** Receives eyetracking events.
 *  <p>
 *  This interface extends the {@link java.util.Iterator} interface.
 *  Its implementations can therefore be used like any other iterator.
 * 
 *  @author Luca Fuelbier
 */
public interface ETEventReceiver extends Iterator<ETEvent> {
	
}
