package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.GetStations;
import io.radioweather.radioweatherapi.application.out.RadioAPIPort;
import io.radioweather.radioweatherapi.domain.Radio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseRadio implements GetStations {

    private RadioAPIPort radioAPIPort;

    public UseCaseRadio(RadioAPIPort radioAPIPort) {
        this.radioAPIPort = radioAPIPort;
    }

    @Override
    public List<Radio> getStationByCodeCountryAndNameCity(String codeCountry, String nameCity, int limit) {
        return radioAPIPort.getStationByCodeCountryAndNameCity(codeCountry, nameCity, limit);
    }

}
