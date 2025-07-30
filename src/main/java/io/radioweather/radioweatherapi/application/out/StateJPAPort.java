package io.radioweather.radioweatherapi.application.out;

import io.radioweather.radioweatherapi.domain.States;
import java.util.List;

public interface StateJPAPort {
    List<States> findByCountry(String countryIso2,  int page, int size);
    long getTotalStatesByIso2(String countryIso2);
}
