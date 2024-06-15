package com.example.epicureexpress.models;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    private int id;
    private int idStatus;
    private String status;
    private int idUser;
    private Date dateOrder;
    private BigDecimal sumPrice;
    private String address;

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
    public int getIdStatus() {
        return idStatus;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }
    public Date getDateOrder() {
        return dateOrder;
    }
    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }
    public BigDecimal getSumPrice() {
        return sumPrice;
    }
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress() {
        return address;
    }
}
