package com.learning.spring.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleDataNotFound(Exception e) {
        return new ResponseEntity<>("USER NOT FOUND!!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return new ResponseEntity<>("Something went wrong!!", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    //Customize exception handler for Validations related errors.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        //Create a map which will store the name of field and the message about the field.
        Map<String, String> validationErrors = new HashMap<>();

        // Get all validation errors from the exception
        List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();

        // Iterate through each validation error and extract field name and message
        for(ObjectError objectError: allErrors) {
            String fieldName = null;
            String fieldMessage = objectError.getDefaultMessage();
            if(objectError instanceof FieldError) {
                fieldName = ((FieldError) objectError).getField();
            }
            validationErrors.put(fieldName, fieldMessage);
        }
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }
}
