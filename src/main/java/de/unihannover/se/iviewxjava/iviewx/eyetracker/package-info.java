/** Contains the SMI IViewX SDK specific configuration and receiver
 *  classes.
 *  <p>
 *  The manager classes handle connecting and configuring the eyetracking
 *  server. This package also includes the receiver implementations for
 *  use with the eyetracking core library.
 *  <p>
 *  Note that none of these classes should be created by the
 *  user. The {@link de.unihannover.se.iviewxjava.iviewx.IViewX} class handles the singleton
 *  creation of these classes and provides static methods to
 *  access them for use in your program.
 *  <p>
 *  Also note that none of these classes are intended to be used
 *  concurrently. If you need concurrent access to the eyetracking
 *  receiver, you should use {@link de.unihannover.se.iviewxjava.core.reactive.ETStreamService}
 *  in conjunction with one of the receivers provided by the
 *  {@link de.unihannover.se.iviewxjava.iviewx.IViewX} class.
 *
 *  @author Luca Fuelbier
 */
package de.unihannover.se.iviewxjava.iviewx.eyetracker;