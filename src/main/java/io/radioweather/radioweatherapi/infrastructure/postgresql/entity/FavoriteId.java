package io.radioweather.radioweatherapi.infrastructure.postgresql.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class FavoriteId implements Serializable {

    private String emailuser;
    private Integer cityfavorite;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavoriteId entity = (FavoriteId) o;
        return Objects.equals(this.emailuser, entity.emailuser) &&
                Objects.equals(this.cityfavorite, entity.cityfavorite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailuser, cityfavorite);
    }

}
