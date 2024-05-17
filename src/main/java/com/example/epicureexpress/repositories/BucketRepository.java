package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Bucket;
import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BucketRepository {

    private final JdbcTemplate jdbc;
    private final LoggedUserManagementService loggedUserManagementService;

    public BucketRepository(
            JdbcTemplate jdbc,
            LoggedUserManagementService loggedUserManagementService
    ){
        this.jdbc = jdbc;
        this.loggedUserManagementService = loggedUserManagementService;
    }

    public List<Bucket> findNomenclatures(){

        int idUs = loggedUserManagementService.getId();

        String sql = "SELECT n.*, b.cntprod FROM nomenclatures n JOIN bucket b ON n.idnom = b.idnom WHERE b.idus = "+idUs;

        RowMapper<Bucket> nomenclatureRowMapper = (r, i) -> {
            Bucket rowObject = new Bucket();
            rowObject.setId(r.getInt("idnom"));
            rowObject.setServletId("/product/imgServlet?id="+r.getInt("idnom"));
            rowObject.setNameProd(r.getString("namenom"));
            rowObject.setPriceProd(r.getBigDecimal("priceprod"));
            rowObject.setImageProd(r.getBytes("imgnom"));
            rowObject.setCountProduct(r.getInt("cntprod"));
            return rowObject;
        };

        return jdbc.query(sql, nomenclatureRowMapper);
    }

}
