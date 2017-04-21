/** Contains classes for defining and managing chronologic objects.
 *   <p>
 *   Chronologic objects are objects, that can be chronologically compared.
 *   For eyetracking applications this would be a timestamp that
 *   indicates when the piece of data was recorded.
 *   <p>
 *   To define a chronologic object, you will have to create a class
 *   that implements the {@link core.chronologic.ChronologicComparable}
 *   interface. This class can then be used throughout the eyetracking
 *   library.
 *   <p>
 *   If you need to store chronologic objects in a collection that guarantees
 *   the chronologic order of the stored objects, take a look at
 *   {@link core.chronologic.ETChronologicCollection}.
 *
 *   @author Luca Fuelbier
 */
package core.chronologic;