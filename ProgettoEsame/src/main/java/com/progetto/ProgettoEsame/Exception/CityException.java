package com.progetto.ProgettoEsame.Exception;

/**
 * Classe che rappresenta il messaggio di eccezione che viene restituito
 * @author Ludovico Gorgoglione
 * @author Christian Curzi
 */

public class CityException extends Exception{

    /**
     * Variabile che rappresenta il messaggio che verrà restituito.
     */
    private String exceptionMessage;

    /** Costruttore dell'oggetto.
     * @param message        Messaggio che verrà restituito.
     */

    public CityException (String message){
        super();
        this.exceptionMessage = message;
    }

    /**
     * Metodo che restituisce il messaggio di eccezione.
     * @return exceptionMessage
     */

    public String getExceptionMessage() {
        return exceptionMessage;
    }

}