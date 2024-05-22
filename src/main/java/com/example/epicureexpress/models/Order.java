package com.example.epicureexpress.models;

import java.util.Date;

public class Order {
    private int id;
    private int idStatus;
    private int idUser;
    private Date dateOrder;

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
    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
    public int getIdUser() {
        return idUser;
    }
    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }
    public Date getDateOrder() {
        return dateOrder;
    }
}
