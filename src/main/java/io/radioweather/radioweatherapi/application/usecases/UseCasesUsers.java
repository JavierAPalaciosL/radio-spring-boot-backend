package io.radioweather.radioweatherapi.application.usecases;

import io.radioweather.radioweatherapi.application.in.GetUserByEmail;
import io.radioweather.radioweatherapi.application.in.LoginUser;
import io.radioweather.radioweatherapi.application.in.RegisterUser;
import io.radioweather.radioweatherapi.application.out.UserJPAPort;
import io.radioweather.radioweatherapi.domain.Users;
import org.springframework.stereotype.Service;

@Service
public class UseCasesUsers implements LoginUser, RegisterUser, GetUserByEmail {

    private final UserJPAPort userJPAPort;

    public UseCasesUsers(UserJPAPort userJPAPort) {
        this.userJPAPort = userJPAPort;
    }

    @Override
    public Users login(String email, String password) {
        Users userFound = this.userJPAPort.login(email, password);

        if(userFound == null) {
            return null;
        }

        return userFound;
    }

    @Override
    public Users findUserByEmail(String email) {
        return this.userJPAPort.findByEmail(email);
    }

    @Override
    public Users register(Users user) {
        return this.userJPAPort.register(user);
    }

    @Override
    public Users getUserByEmail(String email) {
        return this.findUserByEmail(email);
    }
}
