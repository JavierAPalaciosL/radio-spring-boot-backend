package io.radioweather.radioweatherapi.adapters.rest.dtos;

import lombok.Data;

@Data
public class UserInfoDTO {
    private String sub;
    private String name;
    private String given_name;
    private String family_name;
    private String picture;
    private String email;
    private boolean email_verified;
}
