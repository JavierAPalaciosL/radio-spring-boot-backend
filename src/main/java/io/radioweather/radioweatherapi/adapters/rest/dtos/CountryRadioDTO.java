package io.radioweather.radioweatherapi.adapters.rest.dtos;

import io.radioweather.radioweatherapi.domain.Country;
import io.radioweather.radioweatherapi.domain.Radio;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryRadioDTO {

    private Country country;
    private List<Radio> radios;

}
