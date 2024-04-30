package com.example.epicureexpress.models;

import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class LoginProcessor {
    private final LoggedUserManagementService loggedUserManagementService;
    private String username;
    private String password;
    public LoginProcessor(
            LoggedUserManagementService loggedUserManagementService
    ){
        this.loggedUserManagementService = loggedUserManagementService;
    }

    public boolean login(){
        String username = this.username;
        String password = this.password;

        boolean loginResult = false;
        if ("Alexey".equals(username) && "1".equals(password)){
            loggedUserManagementService.setUsername(username);
            loginResult = true;
        }
        return loginResult;
    }

    public boolean logout(){
        boolean logoutResult = false;
        loggedUserManagementService.setUsername(null);
        logoutResult = true;
        return logoutResult;
    }

    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
}
