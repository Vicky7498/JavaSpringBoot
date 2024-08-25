package com.infy.exception;

import java.io.Serial;

public class InfyPlayerException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InfyPlayerException(String message) {
        super(message);
    }
}
