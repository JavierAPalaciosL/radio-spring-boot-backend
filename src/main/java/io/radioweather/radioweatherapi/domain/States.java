package io.radioweather.radioweatherapi.domain;

import lombok.Builder;

@Builder
public class States {

    private Integer id;
    private String name;
    private Country country;
    private String stateCode;
    private double latitude;
    private double longitude;
    private String urlImageMap;

    public States() {
    }

    public States(Integer id, String name, Country country, String stateCode, double latitude, double longitude, String urlImageMap) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.stateCode = stateCode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.urlImageMap = urlImageMap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getUrlImageMap() {
        return urlImageMap;
    }

    public void setUrlImageMap(String urlImageMap) {
        this.urlImageMap = urlImageMap;
    }

}
