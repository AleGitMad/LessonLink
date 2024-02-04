package com.example.lessonlink.exceptions;

import java.io.Serial;

public class FailedInsertException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;
    public FailedInsertException (String message){
        super(message);
    }
}
