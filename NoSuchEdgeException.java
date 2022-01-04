
/** Indicates that the edge is not in the graph
 * <p>NoSuchEdgeException is not expected to be thrown during normal use
 * of a graph. As such, it is declared as a RuntimeException, which
 * is not checked at compile time.
 */
public class NoSuchEdgeException extends RuntimeException { 
    private static final long serialVersionUID = 0L; // Serialization requirement

    /** Generate instance
     */
    public NoSuchEdgeException() {
        super("Provided edge is not currently in the graph");
    }
}
