package com.example.swagger.exception;

public class ApplicationException extends RuntimeException {

    private static final long serialVersionUID = -5268720984937870300L;

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }
}
