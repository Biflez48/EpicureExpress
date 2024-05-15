package com.example.epicureexpress.models;

public class User {
    private int id;
    private String login;
    private String password;
    private int idRol;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getLogin(){
        return login;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setIdRol(int idRol){
        this.idRol = idRol;
    }
    public int getIdRol(){
        return idRol;
    }
}
