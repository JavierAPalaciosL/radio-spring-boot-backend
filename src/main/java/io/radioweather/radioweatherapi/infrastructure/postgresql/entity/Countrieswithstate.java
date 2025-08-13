package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Mapping for DB view
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = "countrieswithstates")
public class Countrieswithstate {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "name_countries", length = Integer.MAX_VALUE)
    private String nameCountries;

    @Column(name = "latitude", length = 30)
    private String latitude;

    @Column(name = "longitude", length = 30)
    private String longitude;

    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "iso2", length = Integer.MAX_VALUE)
    private String iso2;

    @Column(name = "iso3", length = Integer.MAX_VALUE)
    private String iso3;

    @Column(name = "emoji", length = Integer.MAX_VALUE)
    private String emoji;

    @Column(name = "emojiu", length = Integer.MAX_VALUE)
    private String emojiu;

}
