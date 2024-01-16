package com.example.lessonlink.exceptions;

import java.io.Serial;

//TODO: capire come funziona
public class FailedResearchException extends Exception{
    @Serial
    private static final long serialVersionUID = 2L;
    public FailedResearchException (String message){
        super(message);
    }

}