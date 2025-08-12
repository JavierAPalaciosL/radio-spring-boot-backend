package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Users;

public interface GetUserByEmail {
    public Users getUserByEmail(String email);
}
