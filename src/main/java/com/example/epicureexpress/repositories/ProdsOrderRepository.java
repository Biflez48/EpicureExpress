package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Bucket;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProdsOrderRepository {

    private final JdbcTemplate jdbc;

    public ProdsOrderRepository(
            JdbcTemplate jdbc
    ){
        this.jdbc = jdbc;
    }

    public void addProducts(int idOrder, List<Bucket> products){
        for (Bucket product:products) {
            String sql = "INSERT INTO prodsord (idnom,cntprod,idord) VALUES (?,?,?)";
            jdbc.update(sql, product.getId(), product.getCountProduct(), idOrder);
        }
    }
}
