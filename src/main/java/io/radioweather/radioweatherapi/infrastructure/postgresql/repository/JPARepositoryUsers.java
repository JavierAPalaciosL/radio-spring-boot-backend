package io.radioweather.radioweatherapi.infrastructure.postgresql.repository;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JPARepositoryUsers extends JpaRepository<User, String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

}
