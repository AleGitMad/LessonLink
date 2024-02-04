package com.example.lessonlink.exceptions;

import java.io.Serial;

public class FailedUpdateException extends Exception{
    @Serial
    private static final long serialVersionUID = 3L;
    public FailedUpdateException(String message){
        super(message);
    }
}
