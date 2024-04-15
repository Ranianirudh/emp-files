package com.nextgen.emp.exception;

import com.nextgen.emp.model.Employee;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonHandler {
    @ExceptionHandler(EmployeeException.class)
    public ResponseEntity<?> handlerException(EmployeeException exception){
        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(exception.getMessage());
    }
}
