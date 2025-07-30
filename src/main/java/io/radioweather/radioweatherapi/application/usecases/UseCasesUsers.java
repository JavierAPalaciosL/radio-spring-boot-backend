package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.LoginUser;
import io.radioweather.radioweatherapi.application.in.RegisterUser;
import io.radioweather.radioweatherapi.application.out.UserJPAPort;
import io.radioweather.radioweatherapi.domain.Users;
import org.springframework.stereotype.Service;

@Service
public class UseCasesUsers implements LoginUser, RegisterUser {

    private final UserJPAPort userJPAPort;

    public UseCasesUsers(UserJPAPort userJPAPort) {
        this.userJPAPort = userJPAPort;
    }

    @Override
    public Users login(String email, String password) {
        return null;
    }

    @Override
    public Users register(Users user) {
        return null;
    }

}
