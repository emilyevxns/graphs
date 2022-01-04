
/** Indicates that the node is not in the graph
 * <p>NoSuchNodeException is not expected to be thrown during normal use
 * of a graph. As such, it is declared as a RuntimeException, which
 * is not checked at compile time.
 */
public class NoSuchNodeException extends RuntimeException { 
    private static final long serialVersionUID = 0L; // Serialization requirement

    /** Generate instance
     */
    public NoSuchNodeException() {
        super("Provided node is not currently in the graph");
    }
}
