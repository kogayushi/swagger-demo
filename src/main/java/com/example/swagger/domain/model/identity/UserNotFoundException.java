package com.example.swagger.domain.model.identity;

import com.example.swagger.exception.ApplicationException;

public class UserNotFoundException extends ApplicationException {
    private static final long serialVersionUID = 4125007030953051640L;

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
