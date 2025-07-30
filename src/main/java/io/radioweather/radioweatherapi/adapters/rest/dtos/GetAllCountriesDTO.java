package io.radioweather.radioweatherapi.adapters.rest.dtos;

import io.radioweather.radioweatherapi.domain.Country;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCountriesDTO {

    private List<Country> country;
    private long totalCountries;

}
