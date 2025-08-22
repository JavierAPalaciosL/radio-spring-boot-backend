package io.radioweather.radioweatherapi.application.out;

import io.radioweather.radioweatherapi.domain.Favorites;

import java.util.List;

public interface FavoritesJPAPort {
    public Favorites saveFavorite(Favorites favorites);
    public List<Favorites> getFavorites(String emailUser);
}
