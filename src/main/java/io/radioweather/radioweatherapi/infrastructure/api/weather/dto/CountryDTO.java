package io.radioweather.radioweatherapi.infrastructure.api.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountryDTO {

    private String name;
    private HashMap<String, String> local_names;
    private String lat;
    private String lon;
    private String country;
    private String state;


}
