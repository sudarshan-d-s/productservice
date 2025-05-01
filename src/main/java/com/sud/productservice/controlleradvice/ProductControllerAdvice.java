package com.sud.productservice.controlleradvice;

import com.sud.productservice.dtos.ErrorDto;
import com.sud.productservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException(NullPointerException ex, WebRequest request) {
        ErrorDto errorDto = new ErrorDto("Unexpected null value encountered", "Error");
        return new ResponseEntity<>(errorDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException ex) {
        ErrorDto errorDto = new ErrorDto(ex.getMessage(), "FAILURE");
        return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
    }

}
