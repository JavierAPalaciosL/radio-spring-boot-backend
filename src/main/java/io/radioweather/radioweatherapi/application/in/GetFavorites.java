package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Favorites;

import java.util.List;

public interface GetFavorites {
    List<Favorites> getFavorites(Favorites favorites);
}
