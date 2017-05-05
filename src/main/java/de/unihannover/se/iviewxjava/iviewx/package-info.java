/** Contains the SMI IViewX SDK specific data and configuration classes.
 *  <p>
 *  You can connect and configure the eyetracker through the services
 *  provided by {@link de.unihannover.se.iviewxjava.iviewx.IViewX}. This class also provides receivers
 *  for the two main datatypes, {@link de.unihannover.se.iviewxjava.iviewx.data.ETEvent} and
 *  {@link de.unihannover.se.iviewxjava.iviewx.data.ETSample}. Note that these services should only
 *  be accessed through the {@link de.unihannover.se.iviewxjava.iviewx.IViewX} class, which handles
 *  the singleton behavior of the services internally.
 *  <p>
 *  The {@link de.unihannover.se.iviewxjava.iviewx.serialization} package provides serialization
 *  functionality for events and samples. This makes it easy to store and
 *  load data to/from disk or a database, or send data over the network.
 *  
 *  @author Luca Fuelbier
 */
package de.unihannover.se.iviewxjava.iviewx;