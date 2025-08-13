package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "iso3", length = 500)
    private String iso3;

    @Column(name = "iso2", length = 500)
    private String iso2;

    @Column(name = "numeric_code", length = 500)
    private String numericCode;

    @Column(name = "phone_code", length = 500)
    private String phoneCode;

    @Column(name = "capital", length = 500)
    private String capital;

    @Column(name = "currency", length = 500)
    private String currency;

    @Column(name = "currency_name", length = 500)
    private String currencyName;

    @Column(name = "currency_symbol", length = 500)
    private String currencySymbol;

    @Column(name = "tld", length = 10)
    private String tld;

    @Column(name = "native", length = 500)
    private String nativeField;

    @Column(name = "region", length = 500)
    private String region;

    @Column(name = "subregion", length = 500)
    private String subregion;

    @Column(name = "timezones", length = 5000)
    private String timezones;

    @Column(name = "latitude", length = 30)
    private String latitude;

    @Column(name = "longitude", length = 30)
    private String longitude;

    @Column(name = "emoji", length = 30)
    private String emoji;

    @Column(name = "emojiu", length = 30)
    private String emojiu;

    @OneToMany(mappedBy = "country")
    private Set<State> states = new LinkedHashSet<>();

}
