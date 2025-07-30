package io.radioweather.radioweatherapi.infrastructure.postgresql.repository.views;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Countrieswithstate;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryViewCountry extends JpaRepository<Countrieswithstate, Long> {
    Slice<Countrieswithstate> findAllBy(Pageable pageable);
}
