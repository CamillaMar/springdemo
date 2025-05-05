package org.generation.italy.springdemo.models.exceptions;

public class DataException extends RuntimeException {
    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
