package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.Country;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPARepositoryCountry extends JpaRepository<Country, Long> {

    Slice<Country> findAllBy(Pageable pageable);
    Slice<Country> findByIso2(String iso2, Pageable pageable);
    Country findByIso2(String iso2);


}
