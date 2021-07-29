package com.navi.filesystem.exception;

public class UserDefinedException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 8644495801158555491L;
  public static final String EXCEPTION_NAME = "UserDefinedException";

  public UserDefinedException(String message) {
    super(message);
  }

  public UserDefinedException() {
    super("Something went wrong");
  }

  public UserDefinedException(String message, Throwable e) {
    super(message, e);
  }

  public UserDefinedException(Throwable e) {
    super(e);
  }

}
