package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "favorites")
@IdClass(FavoriteId.class)
public class Favorite {

    @Id
    @Column(name = "emailuser", nullable = false)
    private String emailuser;

    @Id
    @Column(name = "cityfavorite", nullable = false)
    private int cityfavorite;

    @Column(name = "dateadd")
    private LocalDate dateadd;

}
