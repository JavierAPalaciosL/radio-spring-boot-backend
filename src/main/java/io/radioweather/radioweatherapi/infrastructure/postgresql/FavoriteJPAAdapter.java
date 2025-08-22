package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.application.out.FavoritesJPAPort;
import io.radioweather.radioweatherapi.domain.Favorites;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.City;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Favorite;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.FavoriteId;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.User;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryCities;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryFavorites;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryUsers;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class FavoriteJPAAdapter implements FavoritesJPAPort {

    private final JPARepositoryFavorites jpaRepositoryFavorites;
    private final JPARepositoryUsers  jpaRepositoryUsers;
    private final JPARepositoryCities  jpaRepositoryCities;

    public FavoriteJPAAdapter(JPARepositoryFavorites jpaRepositoryFavorites, JPARepositoryUsers  jpaRepositoryUsers, JPARepositoryCities jpaRepositoryCities) {
        this.jpaRepositoryFavorites = jpaRepositoryFavorites;
        this.jpaRepositoryUsers = jpaRepositoryUsers;
        this.jpaRepositoryCities = jpaRepositoryCities;
    }

    @Override
    public Favorites saveFavorite(Favorites favorites) {

        Optional<User> userFound = this.jpaRepositoryUsers.findById(favorites.getEmailUser());
        Optional<City> cityFound = this.jpaRepositoryCities.findById(favorites.getIdCity());

        FavoriteId favoriteId = new FavoriteId();
        favoriteId.setCityfavorite(cityFound.get().getId());
        favoriteId.setEmailuser(userFound.get().getEmail());

        Favorite favoriteSave = this.jpaRepositoryFavorites.save(
                Favorite.builder()
                .emailuser(userFound.get().getEmail())
                .cityfavorite(cityFound.get().getId())
                .dateadd(LocalDate.now())
                .build());

        return new Favorites(favoriteSave.getEmailuser(), favoriteSave.getCityfavorite(), Date.valueOf(favoriteSave.getDateadd()));
    }

    @Override
    public List<Favorites> getFavorites(String emailUser) {
        return List.of();
    }
}
