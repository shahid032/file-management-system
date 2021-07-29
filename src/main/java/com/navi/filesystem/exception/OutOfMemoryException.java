package com.navi.filesystem.exception;

public class OutOfMemoryException extends RuntimeException {

  /**
   * 
   */
  private static final long serialVersionUID = 6613570852736287674L;
  public static final String EXCEPTION_NAME = "OutOfMemoryException";

  public OutOfMemoryException(String message) {
    super(message);
  }

  public OutOfMemoryException() {
    super("Out of memory Exception");
  }

  public OutOfMemoryException(String message, Throwable e) {
    super(message, e);
  }

  public OutOfMemoryException(Throwable e) {
    super(e);
  }

}
