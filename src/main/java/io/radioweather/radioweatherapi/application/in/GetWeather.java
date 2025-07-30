package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Weather;

public interface GetWeather {

    public Weather getWeather(double latitude, double longitude);

}
