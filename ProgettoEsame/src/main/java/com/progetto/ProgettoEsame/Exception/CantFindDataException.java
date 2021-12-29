package com.progetto.ProgettoEsame.Exception;

public class CantFindDataException extends Exception {


    /**
     * Variabile che contiene il messaggio da restituire.
     */
    private String message;

    /** Costruttore della classe.
     * @param message        Messaggio che verrà restituito.
     */
    public CantFindDataException (String message){
        super();
        this.message = message;
    }

    /**
     * metodo che restituisce il messaggio dell'eccezione.
     * @return exceptionMessage
     */
    public String getExceptionMessage() {
        return message;
    }
}
