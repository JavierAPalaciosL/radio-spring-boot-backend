package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "favorites")
public class Favorite {
    @EmbeddedId
    private FavoriteId id;

    @MapsId("emailuser")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "emailuser", nullable = false)
    private User emailuser;

    @MapsId("cityfavorite")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cityfavorite", nullable = false)
    private City cityfavorite;

    @Column(name = "dateadd")
    private LocalDate dateadd;

}
