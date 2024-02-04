package com.example.lessonlink.exceptions;

import java.io.Serial;

public class FailedConnectorException extends Exception{
    @Serial
    private static final long serialVersionUID = 4L;
    public FailedConnectorException(String message){
        super(message);
    }
}
