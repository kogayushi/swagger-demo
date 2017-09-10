package com.example.swagger.domain.model.identity;

import com.example.swagger.exception.ApplicationException;

public class FailedAuthenticationException extends ApplicationException {
    private static final long serialVersionUID = 4125007030953051640L;

    public FailedAuthenticationException() {
        super();
    }

    public FailedAuthenticationException(String message) {
        super(message);
    }

    public FailedAuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailedAuthenticationException(Throwable cause) {
        super(cause);
    }
}
