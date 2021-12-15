package com.progetto.ProgettoEsame.Exception;

public class TimeSlotException extends Exception{

    private String exceptionMessage;

    public TimeSlotException (String message){
        super();
        this.exceptionMessage = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
