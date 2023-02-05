package com.creators.notebook.backend.exception;

public class NoItemException extends Exception{
  public NoItemException() {
  }

  public NoItemException(String message) {
    super(message);
  }

  public NoItemException(String message, Throwable cause) {
    super(message, cause);
  }

  public NoItemException(Throwable cause) {
    super(cause);
  }

  public NoItemException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
