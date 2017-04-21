/** Contains classes for defining and implementing receivers.
 *   <p>
 *   To define your own receiver, you have to create a class that
 *   extends {@link core.receiver.ETReceiver}. Receivers can be used
 *   throughout the eyetracking core library, making it easy to reuse
 *   the library for different sources (eyetrackers, databases, etc). To
 *   define custom types see the {@link core.chronologic} package.
 *   <p>
 *   If you need a receiver that simply plays back data from a
 *   {@link core.chronologic.ETChronologicCollection}, then take a
 *   look at {@link core.receiver.ETPlaybackReceiver}.
 *
 *   @author Luca Fuelbier
 */
package core.receiver;