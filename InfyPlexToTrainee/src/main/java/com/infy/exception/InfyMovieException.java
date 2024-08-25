package com.infy.exception;

import java.io.Serial;

public class InfyMovieException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InfyMovieException(String message) {
        super(message);
    }
}
