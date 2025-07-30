package io.radioweather.radioweatherapi.infrastructure.postgresql;

import io.radioweather.radioweatherapi.application.out.UserJPAPort;
import io.radioweather.radioweatherapi.domain.Users;
import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.User;
import io.radioweather.radioweatherapi.infrastructure.postgresql.repository.JPARepositoryUsers;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
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
            throw new UsernameNotFoundException("Invalid email or password");
        }

        return new Users(user.get().getEmail(), user.get().getPassword(), user.get().getName(), user.get().getFirstname());
    }

    @Override
    public Users register(Users user) {

        User userSave = this.jpaRepositoryUsers.save(User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .firstname(user.getFirstName())
                .build());

        return new Users(userSave.getEmail(), userSave.getPassword(), userSave.getName(), userSave.getFirstname());
    }

}
