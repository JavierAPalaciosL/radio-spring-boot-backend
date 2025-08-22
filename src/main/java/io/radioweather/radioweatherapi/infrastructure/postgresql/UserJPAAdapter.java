package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.application.out.UserJPAPort;
import io.radioweather.radioweatherapi.domain.Favorites;
import io.radioweather.radioweatherapi.domain.Users;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.User;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryUsers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class UserJPAAdapter implements UserJPAPort {

    private final JPARepositoryUsers jpaRepositoryUsers;

    public UserJPAAdapter(JPARepositoryUsers jpaRepositoryUsers) {
        this.jpaRepositoryUsers = jpaRepositoryUsers;
    }

    @Override
    public Users login(String email, String password) {
        Optional<User> user = this.jpaRepositoryUsers.findByEmailAndPassword(email, password);

        if(!user.isPresent()) {
            return null;
        }

        return new Users(user.get().getEmail().stripTrailing(), "", user.get().getName().stripTrailing(), user.get().getFirstname().stripTrailing());
    }

    @Override
    public Users register(Users user) {

        Optional<User> userFound = this.jpaRepositoryUsers.findByEmail(user.getEmail());

        if(userFound.isPresent()) {
            return null;
        }

        User userSave = this.jpaRepositoryUsers.save(User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .firstname(user.getFirstName())
                .build());

        return new Users(userSave.getEmail().stripTrailing(), "", userSave.getName().stripTrailing(), userSave.getFirstname().stripTrailing());
    }

    @Override
    public Users findByEmail(String email) {
        Optional<User> userFound = this.jpaRepositoryUsers.findById(email);

        if(!userFound.isPresent()) {
            return null;
        }

        User user = userFound.get();

        return new Users(user.getEmail().stripTrailing(), "", user.getName().stripTrailing(), user.getFirstname().stripTrailing());
    }

    @Override
    public List<Favorites> getAllFavorites(String emailUser) {

        Optional<User> userFound = this.jpaRepositoryUsers.findById(emailUser);

        if(!userFound.isPresent()){
            return null;
        }

        User user = userFound.get();

        return user.getFavorites().stream().map(favorite -> {return new Favorites(favorite.getEmailuser(), favorite.getCityfavorite(), Date.valueOf(favorite.getDateadd()));}).toList();
    }

}
