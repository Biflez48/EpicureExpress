package com.example.epicureexpress.models;

import com.example.epicureexpress.repositories.UsersRepository;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@Component
@RequestScope
public class LoginProcessor {
    private final LoggedUserManagementService loggedUserManagementService;
    private final UsersRepository usersRepository;
    private String username;
    private String password;
    public LoginProcessor(
            LoggedUserManagementService loggedUserManagementService,
            UsersRepository usersRepository
    ){
        this.loggedUserManagementService = loggedUserManagementService;
        this.usersRepository = usersRepository;
    }

    public boolean login(){
        String username = this.username;
        String password = this.password;

        boolean loginResult = false;
        List<User> gettingUsers = usersRepository.findUser(username,password);
        if (gettingUsers.size() != 0){
            loggedUserManagementService.setUsername(gettingUsers.get(0).getLogin());
            loggedUserManagementService.setIdRole(gettingUsers.get(0).getIdRol());
            loggedUserManagementService.setId(gettingUsers.get(0).getId());
            loginResult = true;
        }
        return loginResult;
    }

    public boolean logout(){
        boolean logoutResult = false;
        loggedUserManagementService.setId(0);
        loggedUserManagementService.setUsername(null);
        loggedUserManagementService.setIdRole(0);
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
