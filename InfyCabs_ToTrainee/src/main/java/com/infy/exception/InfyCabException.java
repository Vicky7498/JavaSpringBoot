package com.infy.exception;

import java.io.Serial;

public class InfyCabException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InfyCabException(String message) {
        super(message);
    }
}
