package com.progetto.ProgettoEsame.Exception;

public class CityException extends Exception{

    private String exceptionMessage;

    public CityException (String message){
        super();
        this.exceptionMessage = message;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
