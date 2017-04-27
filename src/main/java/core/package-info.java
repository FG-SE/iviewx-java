/** Contains the generic core classes of the eyetracking library.
 *  <p>
 *  The core package provides a couple of work classes that are more
 *  or less suited, depending on the kind of application you are building.
 *  The following table gives a short overview of the available options:
 *  
 *  <table summary="Main work classes">
 *    <tr>
 *      <td>{@link core.receiver.ETReceiver}</td>
 *      <td>
 *        Main work class powering all the others utilities in the eyetracking library.
 *        You can build simple main-loop based applications with this, like video games,
 *        that continually poll for new data to update their state.
 *        Note that this is an abstract class and has to be subclassed in order to be
 *        of any use. The {@link iviewx.IViewX} class provides receiver implementations
 *        for the IViewX SDK that will get you started quickly.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>{@link core.receiver.ETPlaybackReceiver}</td>
 *      <td>
 *        Receiver implementation that simply plays back a provided collection of data.
 *        This can be useful for unit testing a system that is built around a simple
 *        {@link core.receiver.ETReceiver}.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>{@link core.recorder.ETRecorder}</td>
 *      <td>
 *        Records data from a receiver and stores the data in a collection for posthoc
 *        analysis. The returned collection supports Java 8 streams, making it easy to
 *        build complex analysis pipelines.
 *        The recording is run asynchronously and can be started/stopped through a simple
 *        interface. The recording rate can be limited by providing a poll rate.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>{@link core.reactive.ETStreamService}</td>
 *      <td>
 *        Provides a reactive stream based on a receiver, powered by the RxJava 2 library.
 *        This is the most advanced work class in the eyetracking library. The reactive
 *        stream provides a powerful tool for building applications, utilizing RxJava's
 *        transformations, schedulers and backpressure mechanisms.
 *        The stream service is run asynchronously and can be started/stopped through a simple
 *        interface. The publish rate can be limited by providing a poll rate and further
 *        controlled by using a suited backpressure strategy.
 *      </td>
 *    </tr>
 *  </table>
 * 
 *  @author Luca Fuelbier
 */
package core;