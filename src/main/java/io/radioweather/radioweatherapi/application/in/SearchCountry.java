package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Country;
import java.util.List;

public interface SearchCountry {
    public List<Country> getCoordsCountry(String countryName, int page, int sizePage);
    public Country findByCoords(double latitude, double longitude);
}
