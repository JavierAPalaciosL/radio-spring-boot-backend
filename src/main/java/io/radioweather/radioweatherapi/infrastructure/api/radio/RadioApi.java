package io.radioweather.radioweatherapi.infrastructure.api.radio;

import io.radioweather.radioweatherapi.application.out.RadioAPIPort;
import io.radioweather.radioweatherapi.domain.Country;
import io.radioweather.radioweatherapi.domain.Radio;
import io.radioweather.radioweatherapi.infrastructure.api.radio.dto.RadioDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RadioApi implements RadioAPIPort {

    private final String host = "https://de1.api.radio-browser.info/";
    /*https://de1.api.radio-browser.info/json/stations/search?geo_lat=18.85195&geo_long=-97.09957&geo_distance=8250&limit=8500&hidebroken=true&order=clickcount&reverse=true*/

    public RestTemplate restTemplate;

    public RadioApi() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public List<Radio> getStationByCodeCountryAndNameCity(String codeCountry, String nameCity, int limit) {

        String getStationsUrl = this.host+"json/stations/search?limit="+limit+"&name="+nameCity+"&countrycode="+codeCountry+"&hidebroken=true&order=clickcount&reverse=true";

        RadioDTO[] radioDTOS = restTemplate.getForObject(getStationsUrl, RadioDTO[].class);

        return Arrays.stream(radioDTOS).map((radioDTO) ->
                        new Radio(
                                radioDTO.getStationUuid(),
                                radioDTO.getName(),
                                radioDTO.getUrl(),
                                radioDTO.getUrlResolved(),
                                radioDTO.getHomepage(),
                                radioDTO.getFavicon(),
                                radioDTO.getTags(),
                                radioDTO.getVotes()
                        ))
                .collect(Collectors.toList());
    }
}
