package io.radioweather.radioweatherapi.adapters.rest.dtos;

import io.radioweather.radioweatherapi.domain.City;
import io.radioweather.radioweatherapi.domain.Radio;
import io.radioweather.radioweatherapi.domain.Weather;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCityDTO {

    private City city;
    private Weather weather;
    private List<Radio> radioStations;

}
