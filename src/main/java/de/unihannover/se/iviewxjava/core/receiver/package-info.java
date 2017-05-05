/** Contains classes for defining and implementing receivers.
 *  <p>
 *  To define your own receiver, you have to create a class that
 *  extends {@link de.unihannover.se.iviewxjava.core.receiver.ETReceiver}. Receivers can be used
 *  throughout the eyetracking core library, making it easy to reuse
 *  the library for different sources (eyetrackers, databases, etc). To
 *  define custom types see the {@link de.unihannover.se.iviewxjava.core.chronologic} package.
 *  <p>
 *  If you need a receiver that simply plays back data from a
 *  {@link de.unihannover.se.iviewxjava.core.chronologic.ETChronologicCollection}, then take a
 *  look at {@link de.unihannover.se.iviewxjava.core.receiver.ETPlaybackReceiver}.
 *
 *  @author Luca Fuelbier
 */
package de.unihannover.se.iviewxjava.core.receiver;