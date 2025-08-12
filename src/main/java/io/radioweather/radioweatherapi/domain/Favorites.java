package io.radioweather.radioweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Favorites {

    private String emailUser;
    private int idCity;
    private Date dateAdd;

}
