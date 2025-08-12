package io.radioweather.radioweatherapi.application.out;


import io.radioweather.radioweatherapi.domain.Favorites;
import io.radioweather.radioweatherapi.domain.Users;

import java.util.List;

public interface UserJPAPort {

    public Users login(String email, String password);
    public Users register(Users user);
    public Users findByEmail(String email);
    public List<Favorites> getAllFavorites(String emailUser);


}
