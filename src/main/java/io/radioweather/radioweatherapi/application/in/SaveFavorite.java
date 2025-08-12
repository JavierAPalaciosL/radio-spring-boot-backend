package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Favorites;

public interface SaveFavorite {
    Favorites saveFavorite(Favorites favorites);
}
