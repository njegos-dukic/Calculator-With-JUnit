package impl.exceptions;

/**
 * @author njego
 * @version 1.0
 *
 * Exception that will be thrown if provided argument is not in range.
 */
public class NumberNotInAreaException extends Exception { 
	
    public NumberNotInAreaException() {
        super("Number not in range.");
    }
}
