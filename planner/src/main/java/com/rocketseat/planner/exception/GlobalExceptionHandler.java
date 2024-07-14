package com.rocketseat.planner.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.DateTimeException;
import java.time.format.DateTimeParseException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = InvalidDateException.class)
    public ResponseEntity<ExceptionResponse> handleValidDateException(InvalidDateException exception, WebRequest webRequest){
        ExceptionResponse exceptionResponse = new ExceptionResponse(exception.getMessage(), webRequest.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = DateTimeException.class)
    public ResponseEntity<ExceptionResponse> handleDateTimeParseException(DateTimeParseException exception,WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse("Invalid date error.Please insert a date type or a valid date.", request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
    }

}
