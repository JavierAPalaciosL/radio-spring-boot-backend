package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Favorite;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JPARepositoryFavorites extends JpaRepository<Favorite, FavoriteId> {

     List<Favorite> findByEmailuser(String emailuser);

}
