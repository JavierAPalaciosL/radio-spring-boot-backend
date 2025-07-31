package io.radioweather.radioweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {

    private String name;
    private double latitude;
    private double longitude;
    private String wikiData;

}
