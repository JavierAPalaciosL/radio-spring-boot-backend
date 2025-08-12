package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Favorite;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.FavoriteId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryFavorites extends JpaRepository<Favorite, FavoriteId> {
}
