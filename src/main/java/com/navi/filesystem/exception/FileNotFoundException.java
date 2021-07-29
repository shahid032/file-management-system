package com.navi.filesystem.exception;

public class FileNotFoundException extends RuntimeException{

  
  /**
   * 
   */
  private static final long serialVersionUID = 8446778570737958489L;
  public static final String EXCEPTION_NAME = "FileNotFoundException";

  public FileNotFoundException(String message) {
    super(message);
  }

  public FileNotFoundException() {
    super("File not found Exception");
  }

  public FileNotFoundException(String message, Throwable e) {
    super(message, e);
  }

  public FileNotFoundException(Throwable e) {
    super(e);
  }
  
}
