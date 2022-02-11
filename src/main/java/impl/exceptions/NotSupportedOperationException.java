package impl.exceptions;

/**
 * @author njego
 * @version 1.0
 * 
 * Exception that will be thrown if specified operation is not supported.
 *
 */
public class NotSupportedOperationException extends Exception {
    
	public NotSupportedOperationException() {
        super("Invalid operation.");
    }
}
