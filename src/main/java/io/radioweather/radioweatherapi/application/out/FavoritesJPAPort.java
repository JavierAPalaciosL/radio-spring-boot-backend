package io.radioweather.radioweatherapi.application.out;

import io.radioweather.radioweatherapi.domain.Favorites;

public interface FavoritesJPAPort {
    public Favorites saveFavorite(Favorites favorites);

}
