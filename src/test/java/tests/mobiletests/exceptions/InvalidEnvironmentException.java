package tests.mobiletests.exceptions;

public class InvalidEnvironmentException extends RuntimeException {

  private static final long serialVersionUID = 2684271192091416338L;

  public InvalidEnvironmentException(String message) {
    super(message);
  }
}
