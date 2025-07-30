package io.radioweather.radioweatherapi.application.in;

import io.radioweather.radioweatherapi.domain.Users;

public interface LoginUser {
    public Users login(String email, String password);
}
