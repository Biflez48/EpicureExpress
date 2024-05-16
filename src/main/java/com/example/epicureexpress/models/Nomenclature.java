package com.example.epicureexpress.models;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class Nomenclature {
    private int id;
    private byte[] image;
    private String name;
    private BigDecimal price;
    private int idType;
    private int countPurchase;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setImage(byte[] image){
        this.image = image;
    }
    public byte[] getImage(){
        return image;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setPrice(BigDecimal price){
        this.price = price;
    }
    public BigDecimal getPrice(){
        return price;
    }
    public void setIdType(int idType){
        this.idType = idType;
    }
    public int getIdType(){
        return idType;
    }
    public void setCountPurchase(int countPurchase){
        this.countPurchase = countPurchase;
    }
    public int getCountPurchase(){
        return countPurchase;
    }
}
