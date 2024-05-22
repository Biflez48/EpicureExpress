package com.example.epicureexpress.models;

import java.math.BigDecimal;

public class Bucket {
    private int id;
    private int idBucket;
    private int countProduct;

    private byte[] imageProd;
    private String nameProd;
    private BigDecimal priceProd;
    private String servletId;

    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setIdBucket(int idBucket) {
        this.idBucket = idBucket;
    }
    public int getIdBucket() {
        return idBucket;
    }
    public void setCountProduct(int countProduct){
        this.countProduct = countProduct;
    }
    public int getCountProduct(){
        return countProduct;
    }
    public void setImageProd(byte[] imageProd){
        this.imageProd = imageProd;
    }
    public byte[] getImageProd(){
        return imageProd;
    }
    public void setNameProd(String nameProd){
        this.nameProd = nameProd;
    }
    public String getNameProd(){
        return nameProd;
    }
    public void setPriceProd(BigDecimal price){
        this.priceProd = price;
    }
    public BigDecimal getPriceProd(){
        return priceProd;
    }
    public void setServletId(String servletId){
        this.servletId = servletId;
    }
    public String getServletId(){
        return servletId;
    }
}
