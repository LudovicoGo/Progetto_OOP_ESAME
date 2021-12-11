package com.progetto.ProgettoEsame.model;

public class Citta {

    private String nome;
    private long id;
    private CoordinateGeo coordinate;
    private String stato;

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public CoordinateGeo getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(CoordinateGeo coordinate) {
        this.coordinate = coordinate;
    }

    public String getStato() {
        return stato;
    }
    public void setStato(String stato) {
        this.stato = stato;
    }

}
