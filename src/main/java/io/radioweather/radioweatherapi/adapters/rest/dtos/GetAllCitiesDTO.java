package io.radioweather.radioweatherapi.adapters.rest.dtos;

import io.radioweather.radioweatherapi.domain.City;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCitiesDTO {

    private List<City> cities;
    private long totalCountries;

}
