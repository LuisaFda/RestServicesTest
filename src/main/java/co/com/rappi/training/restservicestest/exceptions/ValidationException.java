package co.com.rappi.training.restservicestest.exceptions;

public class ValidationException extends AssertionError {

  public static final String ERROR_RESPONSE_CODE = "The response code is not what is expected";
  public static final String ERROR_VALIDATION = "The result is not as expected";

  public ValidationException(String message, Throwable cause) {
    super(message, cause);
  }

}
