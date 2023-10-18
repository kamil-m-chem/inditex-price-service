package com.inditex.interview.inditexpriceservice.exceptions;

public class PriceEntryNotFoundException extends RuntimeException {

  public PriceEntryNotFoundException() {
    super();
  }

  public PriceEntryNotFoundException(String message) {
    super(message);
  }
}
