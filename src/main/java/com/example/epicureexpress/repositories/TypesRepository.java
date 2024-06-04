package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.TypeProd;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TypesRepository {
    private final JdbcTemplate jdbc;

    public TypesRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<TypeProd> findAllTypes(){
        String sql = "SELECT * FROM typesprod ORDER BY nametype";
        RowMapper<TypeProd> typeProdRowMapper = (r, i) -> {
            TypeProd rowObject = new TypeProd();
            rowObject.setId(r.getInt("idtype"));
            rowObject.setName(r.getString("nametype"));
            rowObject.setCode(r.getString("codetype"));
            return rowObject;
        };
        return jdbc.query(sql, typeProdRowMapper);
    }
}
