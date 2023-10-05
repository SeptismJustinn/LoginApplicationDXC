package com.septismjustinn.dxc.loginapp.validators;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class RegisterRequest {
    @NotNull
    @Length(min = 1)
    private String name;
    @NotNull
    @Length(min = 1)
    private String username;
    @NotNull
    @Length(min = 8)
    private String password;

    public RegisterRequest(@NotNull String name, @NotNull String username, @NotNull String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
