package com.progetto.ProgettoEsame.model;

public class CoordinateGeo {

    private long latitudine;
    private long longitudine;

    public long getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(long latitudine) {
        this.latitudine = latitudine;
    }

    public long getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(long longitudine) {
        this.longitudine = longitudine;
    }

    public void latLongString (){
        System.out.println("Latitudine: " + latitudine + "; Longitudine: " + longitudine);
    }

}
