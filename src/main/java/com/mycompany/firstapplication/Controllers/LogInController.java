package com.mycompany.firstapplication.Controllers;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class LogInController {
    private String username;
    private String password;

    @Inject
    private HttpServletRequest request;

    public String logIn() {
        return "main";
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
