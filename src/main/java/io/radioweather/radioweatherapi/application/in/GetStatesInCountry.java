package io.radioweather.radioweatherapi.application.in;


import io.radioweather.radioweatherapi.domain.States;

import java.util.List;

public interface GetStatesInCountry {
    public List<States> states(String countryIso2, int page, int size);
    public long totalStatesByIso2(String countryIso2);
}
