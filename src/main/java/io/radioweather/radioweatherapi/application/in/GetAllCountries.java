package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Country;

import java.util.List;

public interface GetAllCountries {
    public List<Country> getAllCountries(int page, int sizePage);
    long countAllCountries();
}
