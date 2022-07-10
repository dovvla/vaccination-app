package com.timrobot.vaccapp.exceptions;

public class ResourceAlreadyExistsException extends RuntimeException {
    private String message;

    public ResourceAlreadyExistsException(String message) {
        this.message = message;
    }

    public ResourceAlreadyExistsException() {
        super();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
