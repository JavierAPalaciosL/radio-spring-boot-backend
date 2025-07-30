package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FavoriteId implements Serializable {
    private static final long serialVersionUID = 7858359486516612161L;
    @Column(name = "emailuser", nullable = false, length = 30)
    private String emailuser;

    @Column(name = "cityfavorite", nullable = false)
    private Integer cityfavorite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FavoriteId entity = (FavoriteId) o;
        return Objects.equals(this.emailuser, entity.emailuser) &&
                Objects.equals(this.cityfavorite, entity.cityfavorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailuser, cityfavorite);
    }

}
