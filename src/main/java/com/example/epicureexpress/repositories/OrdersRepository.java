package com.example.epicureexpress.repositories;

import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

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

    public int AddOrder(){

        int idUs = loggedUserManagementService.getId();

        String sql = "INSERT INTO orders (idst, idus, dateord) VALUES (?, ?, ?) RETURNING idord";

        return jdbc.queryForObject(
                sql,
                new Object[]{
                        1,
                        idUs,
                        new java.sql.Date(System.currentTimeMillis())
                },
                Integer.class
        );
    }
}
