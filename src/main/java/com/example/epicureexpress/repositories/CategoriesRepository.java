package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Category;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoriesRepository {
    private final JdbcTemplate jdbc;

    public CategoriesRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public List<Category> findAllCategories(){
        String sql = "SELECT * FROM categories ORDER BY namecateg";
        RowMapper<Category> categoryRowMapper = (r, i) -> {
            Category rowObject = new Category();
            rowObject.setId(r.getInt("idcateg"));
            rowObject.setName(r.getString("namecateg"));
            rowObject.setCode("/products?selectedcategory="+r.getString("codecateg"));
            return rowObject;
        };
        return jdbc.query(sql, categoryRowMapper);
    }
}
