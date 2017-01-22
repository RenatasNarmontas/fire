package fire.exception;

/**
 * Created by rena17 on 1/22/2017.
 *
 * Custom exception class
 */
public class CustomerException extends Exception {

  /**
   * Constructs a new exception with the specified detail message.  The
   * cause is not initialized, and may subsequently be initialized by
   * a call to {@link #initCause}.
   *
   * @param message the detail message. The detail message is saved for later retrieval by the {@link
   * #getMessage()} method.
   */
  public CustomerException(String message) {
    super(message);
  }

}
