package com.progetto.ProgettoEsame.model;

public class CityModel extends GeolocationModel {

    private String name;
    private long id;
    private String country;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public CityModel(String name, long id, String country, double latitude, double longitude) {
        super(latitude, longitude);
        this.name = name;
        this.id = id;
        this.country = country;
    }
}
