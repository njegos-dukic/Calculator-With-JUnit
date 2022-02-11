package impl.exceptions;

/**
 * @author njego
 * @version 1.0
 * 
 * Exception that will be thrown when trying to divide by zero.
 *
 */
public class DivisionByZeroException extends Exception {

    public DivisionByZeroException() {
        super("Invalid argument, cannot divide with 0.");
    }
}
