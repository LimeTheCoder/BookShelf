package com.limethecoder.data.dto;


import com.limethecoder.controller.validation.PasswordMatches;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@PasswordMatches
public class UserDto {
    @NotNull
    @NotEmpty
    @Size(min = 4, max=50)
    private String login;

    @NotNull
    @NotEmpty
    @Size(max=50)
    private String name;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String surname;

    @NotNull
    @NotEmpty
    @Size(max = 50)
    private String city;

    @NotNull
    @NotEmpty
    @Size(min = 6, max = 50)
    private String password;

    private String matchingPassword;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}