package io.radioweather.radioweatherapi.domain;

import io.radioweather.radioweatherapi.infrastructure.postgresql.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UsersTests {

    @Test
    public void userEmailNotNull(){
        Users user = new Users("example@gmail.com","123","Example", "ExampleFirstName");
        Assertions.assertNotNull(user.getEmail());
    }

    @Test
    public void userEmailNull(){
        Assertions.assertThrows(IllegalArgumentException.class, ()  -> {
            new Users(null,"123","Example", "ExampleFirstName");
        });
    }

    @Test
    public void userEmailEmpty(){
        Assertions.assertThrows(IllegalArgumentException.class, ()  -> {
            new Users("","123","Example", "ExampleFirstName");
        });
    }

    @Test
    public void passwordNull(){
        Assertions.assertThrows(IllegalArgumentException.class, ()  -> {
            new Users("example@gmail.com",null,"example", "exampleFirstName");
        });
    }

    @Test
    public void passwordEmpty(){
        Assertions.assertThrows(IllegalArgumentException.class, ()  -> {
            new Users("example@gmail.com","","example", "exampleFirstName");
        });
    }




}
