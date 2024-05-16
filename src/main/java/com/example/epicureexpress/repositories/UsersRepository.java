package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Category;
import com.example.epicureexpress.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsersRepository {
    private final JdbcTemplate jdbc;

    public UsersRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<User> findUser(String login, String password){
        String sql = "SELECT * FROM users WHERE loginus = '"+login+"' AND passwordus = '"+password+"'";

        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("idus"));
            rowObject.setLogin(r.getString("loginus"));
            rowObject.setPassword(r.getString("passwordus"));
            rowObject.setIdRol(r.getInt("idrol"));
            return rowObject;
        };

        return jdbc.query(sql, userRowMapper);
    }

    public void addUser(String login, String password){
        String sql = "INSERT INTO users (loginus, passwordus, idrol) VALUES ('"+login+"','"+password+"',2)";
/*
        User newUser = new User();
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setIdRol(2);
        */
        jdbc.update(sql/*,newUser*/);
    }
}