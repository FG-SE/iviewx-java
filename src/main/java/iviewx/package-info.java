/** Contains the IViewX SDK specific data and interface classes.
 * 
 *  You can connect and configure the eyetracker through the services
 *  provided by {@link iviewx.IViewX}. This class also provides receivers
 *  for the two main datatypes, {@link iviewx.data.ETEvent} and 
 *  {@link iviewx.data.ETSample}. Note that these services should only
 *  be accessed through the {@link iviewx.IViewX} class, which handles
 *  the singleton behavior of the services internally.
 *  
 *  The subpackage {@link iviewx.serialization} provides serialization
 *  functinality for events and samples. This makes it easy to store and
 *  load data to/from disk or a database, or send data over the network.
 *  
 *  @author Luca Fuelbier
 */
package iviewx;