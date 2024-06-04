package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Bucket;
import com.example.epicureexpress.models.Order;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class OrdersRepository {
    private final JdbcTemplate jdbc;
    private final LoggedUserManagementService loggedUserManagementService;

    public OrdersRepository(
            JdbcTemplate jdbc,
            LoggedUserManagementService loggedUserManagementService
    ){
        this.jdbc = jdbc;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    public int AddOrder(List<Bucket> products){
        BigDecimal sumprice = new BigDecimal("0.0");
        for (Bucket product:products) {
            sumprice = sumprice.add(product.getPriceProd().multiply(new BigDecimal(product.getCountProduct())));
        }
        int idUs = loggedUserManagementService.getId();
        String sql = "INSERT INTO orders (idst, idus, dateord,sumprice) VALUES (?, ?, ?, ?) RETURNING idord";
        return jdbc.queryForObject(
                sql,
                new Object[]{
                        1,
                        idUs,
                        new java.sql.Date(System.currentTimeMillis()),
                        sumprice
                },
                Integer.class
        );
    }

    public List<Order> getOrdersForCourier(){
        String sql = "SELECT o.*, s.namest FROM orders o JOIN statuses s ON o.idst = s.idst WHERE NOT o.idst = 3 ORDER BY o.dateord desc";
        RowMapper<Order> orderRowMapper = (r, i) -> {
            Order rowObject = new Order();
            rowObject.setId(r.getInt("idord"));
            rowObject.setIdStatus(r.getInt("idst"));
            rowObject.setStatus(r.getString("namest"));
            rowObject.setDateOrder(r.getDate("dateord"));
            rowObject.setSumPrice(r.getBigDecimal("sumprice"));
            return rowObject;
        };
        return jdbc.query(sql, orderRowMapper);
    }

    public List<Order> getOrdersByUserId(int idUser){
        int idUs = idUser;
        String sql = "SELECT o.*, s.namest FROM orders o JOIN statuses s ON o.idst = s.idst WHERE idus = "+idUs+" ORDER BY o.dateord desc";
        RowMapper<Order> orderRowMapper = (r, i) -> {
            Order rowObject = new Order();
            rowObject.setId(r.getInt("idord"));
            rowObject.setIdStatus(r.getInt("idst"));
            rowObject.setStatus(r.getString("namest"));
            rowObject.setDateOrder(r.getDate("dateord"));
            rowObject.setSumPrice(r.getBigDecimal("sumprice"));
            return rowObject;
        };
        return jdbc.query(sql, orderRowMapper);
    }

    public void changeOrderStatus(int idOrder){
        String sql = "UPDATE orders SET idst = idst + 1 WHERE idord = ?";
        jdbc.update(sql, idOrder);
    }
}
