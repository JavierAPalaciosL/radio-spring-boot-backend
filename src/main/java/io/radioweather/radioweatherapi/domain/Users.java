package io.radioweather.radioweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Users {

    private String email;
    private String password;
    private String name;
    private String firstName;

}
