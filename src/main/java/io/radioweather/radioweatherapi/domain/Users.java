package io.radioweather.radioweatherapi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Users {

    private String email;
    private String password;
    private String name;
    private String firstName;


    public Users(String email, String password, String name, String firstName) {
        if(email == null){
            throw new IllegalArgumentException("Email cannot be null");
        }else if(email.isEmpty()){
            throw new IllegalArgumentException("Email cannot be empty");
        }else if(password == null){
            throw new IllegalArgumentException("Password cannot be null");
        }

        this.email = email;
        this.password = password;
        this.name = name;
        this.firstName = firstName;


    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}
