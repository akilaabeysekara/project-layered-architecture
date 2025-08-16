package com.assignment.project.bo.exception;

public class InUseException extends RuntimeException {
    public InUseException(String message) {
        super(message);
    }
}
