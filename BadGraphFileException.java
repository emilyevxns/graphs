
/** Exception to be thrown by the GraphReader if there is something
 *  wrong with the file being read
 */
public class BadGraphFileException extends Exception {
    /** Constructs a new instance
     *@param filename file that is broken
     *@param msg additional information
     */
    public BadGraphFileException(String filename, String msg) {
        super("In file "+filename+" "+msg);
    }
}
