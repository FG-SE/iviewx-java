/** Contains the data types provided by the SMI IViewX SDK.
 *  <p>
 *  The following table gives a short overview of the available
 *  types:
 *
 *  <table summary="IView X SDK data types">
 *    <tr>
 *      <td>{@link de.unihannover.se.iviewxjava.iviewx.data.ETEvent}</td>
 *      <td>
 *        If a user fixates on a specific area for an extended time period
 *        (more than a couple of milliseconds), it is registered as an event.
 *        The ETEvent data type provides information about the timeframe and
 *        gaze coordinates. Events can be used for applications that only care
 *        about actual fixations and not eye movement between them (saccades).
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>{@link de.unihannover.se.iviewxjava.iviewx.data.ETSample}</td>
 *      <td>
 *        A sample is the eyetracking information at a specific point in time.
 *        The ETSample data type provides information about the timestamp of
 *        at which the sample was computed, as well as information about both
 *        eyes at that time. The information about the eyes are wrapped in the
 *        {@link de.unihannover.se.iviewxjava.iviewx.data.ETEyeData} type.
 *      </td>
 *    </tr>
 *    <tr>
*       <td>{@link de.unihannover.se.iviewxjava.iviewx.data.ETEyeData}</td>
 *      <td>
 *        Provides information about a single eye. This information includes
 *        pupil diameter, XYZ coordinates of the eyes in space, aswell as
 *        the gaze coordinates on the screen.
 *      </td>
 *    </tr>
 *  </table>
 *
 *  @author Luca Fuelbier
 */
package de.unihannover.se.iviewxjava.iviewx.data;