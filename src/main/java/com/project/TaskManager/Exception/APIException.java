package com.project.TaskManager.Exception;

public class APIException extends RuntimeException {
    public static final Long serialVersionUID = 1L;
    public APIException() {

    }
    public APIException(String message) {
        super(message);
    }
}
