package io.radioweather.radioweatherapi.application.out;

import io.radioweather.radioweatherapi.domain.Radio;

import java.util.List;

public interface RadioAPIPort {

    public List<Radio> getStationByCodeCountryAndNameCity(String codeCountry, String nameCity, int limit);


}
