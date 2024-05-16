package com.example.epicureexpress.models;

public class Bucket {
    private int id;
    private int idUser;
    private int idNomenclature;
    private int countProduct;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setIdUser(int idUser){
        this.idUser = idUser;
    }
    public int getIdUser(){
        return idUser;
    }
    public void setIdNomenclature(int idNomenclature){
        this.idNomenclature = idNomenclature;
    }
    public int getIdNomenclature(){
        return idNomenclature;
    }
    public void setCountProduct(int countProduct){
        this.countProduct = countProduct;
    }
    public int getCountProduct(){
        return countProduct;
    }
}
