package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.GetFavorites;
import io.radioweather.radioweatherapi.application.in.SaveFavorite;
import io.radioweather.radioweatherapi.application.out.FavoritesJPAPort;
import io.radioweather.radioweatherapi.domain.Favorites;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UseCaseFavorites implements GetFavorites, SaveFavorite {

    private FavoritesJPAPort favoritesJPAPort;

    public UseCaseFavorites(FavoritesJPAPort favoritesJPAPort) {
        this.favoritesJPAPort = favoritesJPAPort;
    }

    @Override
    public Favorites saveFavorite(Favorites favorites) {
        return this.favoritesJPAPort.saveFavorite(favorites);
    }

    @Override
    public List<Favorites> getFavorites(Favorites favorites) {
        return this.favoritesJPAPort.getFavorites(favorites.getEmailUser());
    }

}
