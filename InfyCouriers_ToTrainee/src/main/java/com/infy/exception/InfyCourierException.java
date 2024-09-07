package com.infy.exception;

import java.io.Serial;

public class InfyCourierException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InfyCourierException(String message) {
        super(message);
    }
}
