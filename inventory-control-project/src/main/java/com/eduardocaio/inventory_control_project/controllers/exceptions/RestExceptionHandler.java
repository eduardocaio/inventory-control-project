package com.eduardocaio.inventory_control_project.controllers.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eduardocaio.inventory_control_project.exceptions.DataAlreadyRegisteredException;
import com.eduardocaio.inventory_control_project.exceptions.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<StandardError> userNotFoundHandler(UserNotFoundException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setError("User not found");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DataAlreadyRegisteredException.class)
    private ResponseEntity<StandardError> dataAlreadyRegisteredHandler(DataAlreadyRegisteredException exception, HttpServletRequest request){
        StandardError error = new StandardError();
        error.setError("One or more data already registered.");
        error.setMessage(exception.getMessage());
        error.setPath(request.getRequestURI());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimestamp(Instant.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }


}
