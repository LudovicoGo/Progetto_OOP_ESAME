package com.progetto.ProgettoEsame.model;

public class CityModel extends GeolocationModel {

    private String cityName;
    private long cityId;
    private String country;

    public CityModel() {
        super();
    }

    public String getCityName() {
        return cityName;
    }
    public void setCityName(String name) {
        this.cityName = name;
    }

    public long getCityId() {
        return cityId;
    }
    public void setCityId(long id) {
        this.cityId = id;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public CityModel(String name, long cityId, String country, double latitude, double longitude) {
        super(latitude, longitude);
        this.cityName = name;
        this.cityId = cityId;
        this.country = country;
    }
}
