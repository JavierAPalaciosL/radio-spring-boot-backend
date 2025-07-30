package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.States;
import java.util.List;

public interface GetAllStates {
    List<States> findByCountry(String countryIso2);
}
