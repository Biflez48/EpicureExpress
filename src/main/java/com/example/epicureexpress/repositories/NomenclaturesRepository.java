package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.models.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Repository
public class NomenclaturesRepository {
    private final JdbcTemplate jdbc;

    public NomenclaturesRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    public void addNomenclature(Nomenclature nomenclature){

        byte[] imageData = nomenclature.getImage();

        String sql = "INSERT INTO nomenclatures (idtype,namenom,priceprod,imgnom,countpur) VALUES (?,?,?,?,?)";

        jdbc.update(
                sql,
                nomenclature.getIdType(),
                nomenclature.getName(),
                nomenclature.getPrice(),
                imageData,
                nomenclature.getCountPurchase()
        );
    }

    public List<Nomenclature> findNomenclature(){

        String sql = "SELECT * FROM nomenclatures";

        RowMapper<Nomenclature> nomenclatureRowMapper = (r, i) -> {
            Nomenclature rowObject = new Nomenclature();
            rowObject.setId(r.getInt("idnom"));
            rowObject.setServletId("/product/imgServlet?id="+r.getInt("idnom"));
            rowObject.setIdType(r.getInt("idtype"));
            rowObject.setName(r.getString("namenom"));
            rowObject.setPrice(r.getBigDecimal("priceprod"));
            rowObject.setImage(r.getBytes("imgnom"));
            rowObject.setCountPurchase(r.getInt("countpur"));
            return rowObject;
        };

        return jdbc.query(sql, nomenclatureRowMapper);
    }
}
