package com.example.epicureexpress.services;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class LoggedUserManagementService {
    private int id;
    private String username;
    private int idRole;
    private String roleName;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
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

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}
