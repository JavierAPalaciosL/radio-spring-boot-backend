package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Radio;

import java.util.List;

public interface GetStations {
    public List<Radio> getStationByCodeCountryAndNameCity(String codeCountry, String nameCity, int limit);

}
