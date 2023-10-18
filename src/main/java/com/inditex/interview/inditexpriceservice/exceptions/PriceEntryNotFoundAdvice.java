package com.inditex.interview.inditexpriceservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class PriceEntryNotFoundAdvice {

  @ExceptionHandler(PriceEntryNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  ResponseEntity<ErrorResponse> employeeNotFoundHandler(PriceEntryNotFoundException ex) {
    ErrorResponse errorResponse = new ErrorResponse("Price entry not found", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
    ErrorResponse errorResponse = new ErrorResponse("Internal Server Error", ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
