package com.example.epicureexpress.models;

public class Category {
    private int id;
    private String name;
    private String code;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
}
