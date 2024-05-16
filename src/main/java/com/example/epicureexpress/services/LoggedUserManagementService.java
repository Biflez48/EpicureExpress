package com.example.epicureexpress.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LoggedUserManagementService {
    private String username;
    private int idRole;
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return username;
    }
    public void setIdRole(int idRole){
        this.idRole = idRole;
    }
    public int getIdRole(){
        return idRole;
    }
}
