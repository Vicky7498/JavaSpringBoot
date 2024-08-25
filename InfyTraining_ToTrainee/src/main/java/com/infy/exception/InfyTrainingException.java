package com.infy.exception;

import java.io.Serial;

public class InfyTrainingException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    public InfyTrainingException(String message) {
        super(message);

    }
}