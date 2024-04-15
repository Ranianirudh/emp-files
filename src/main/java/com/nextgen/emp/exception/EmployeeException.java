package com.nextgen.emp.exception;

public class EmployeeException extends RuntimeException{
        public EmployeeException(String message, Throwable cause){
            super(message,cause);
        }
        public EmployeeException(String message){
            super(message);
        }
}
