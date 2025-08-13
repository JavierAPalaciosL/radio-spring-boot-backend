package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email", nullable = false, length = 30)
    private String email;

    @Column(name = "name", length = 30)
    private String name;

    @Column(name = "firstname", length = 30)
    private String firstname;

    @Column(name = "password", length = 30)
    private String password;

    @OneToMany(mappedBy = "emailuser")
    private Set<Favorite> favorites = new LinkedHashSet<>();

}
