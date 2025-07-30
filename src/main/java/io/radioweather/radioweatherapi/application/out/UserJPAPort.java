package io.radioweather.radioweatherapi.application.out;


import io.radioweather.radioweatherapi.domain.Users;

public interface UserJPAPort {

    public Users login(String email, String password);
    public Users register(Users user);

}
