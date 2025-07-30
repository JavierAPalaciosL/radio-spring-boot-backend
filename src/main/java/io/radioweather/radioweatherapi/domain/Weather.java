package io.radioweather.radioweatherapi.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;

@Data
@RequiredArgsConstructor
public class Weather {

    private HashMap<String, String> coord;
    private List<HashMap<String, String>> weather;
    private String base;
    private HashMap<String, String> main;
    private String visibility;
    private HashMap<String, String> wind;
    private HashMap<String, String> sys;
}
