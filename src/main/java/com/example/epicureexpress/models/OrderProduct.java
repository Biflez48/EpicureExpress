package com.example.epicureexpress.models;

public class OrderProduct {
    private int id;
    private int idOrder;
    private int idNomenclature;
    private int countProduct;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }
    public int getIdOrder() {
        return idOrder;
    }
    public void setIdNomenclature(int idNomenclature) {
        this.idNomenclature = idNomenclature;
    }
    public int getIdNomenclature() {
        return idNomenclature;
    }
    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }
    public int getCountProduct() {
        return countProduct;
    }
}
