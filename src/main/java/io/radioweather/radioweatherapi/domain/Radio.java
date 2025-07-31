package io.radioweather.radioweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Radio {

    private String stationID;
    private String name;
    private String url;
    private String urlResolved;
    private String homePage;
    private String icon;
    private String tags;
    private int votes;
    private double geoLat;
    private double geoLong;
    private double geoDistance;

}
