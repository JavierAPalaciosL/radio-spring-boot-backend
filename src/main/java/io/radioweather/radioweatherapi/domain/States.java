package io.radioweather.radioweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class States {

    private Integer id;
    private String name;
    private Country country;
    private String stateCode;
    private double latitude;
    private double longitude;
    private String urlImageMap;

}
