package com.example.epicureexpress.repositories;

import com.example.epicureexpress.models.Bucket;
import com.example.epicureexpress.models.Nomenclature;
import com.example.epicureexpress.services.LoggedUserManagementService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NomenclaturesRepository {
    private final JdbcTemplate jdbc;
    private final LoggedUserManagementService loggedUserManagementService;

    public NomenclaturesRepository(
            JdbcTemplate jdbc,
            LoggedUserManagementService loggedUserManagementService
    ){
        this.jdbc = jdbc;
        this.loggedUserManagementService = loggedUserManagementService;
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

    public byte[] getImgById(int idNom){

        String sql = "SELECT * FROM nomenclatures WHERE idnom = "+idNom;

        RowMapper<Nomenclature> nomenclatureRowMapper = (r, i) -> {
            Nomenclature rowObject = new Nomenclature();
            rowObject.setId(r.getInt("idnom"));
            rowObject.setImage(r.getBytes("imgnom"));
            return rowObject;
        };

        return jdbc.query(sql, nomenclatureRowMapper).get(0).getImage();
    }

    public List<Nomenclature> findNomenclature(String selectedtype, String selectedcategory){

        String sql = "SELECT * FROM nomenclatures";

        if(selectedtype != null && !selectedtype.equals("null")){
            sql = "SELECT * FROM nomenclatures WHERE idtype IN (SELECT idtype FROM typesprod WHERE codetype = '"+selectedtype+"')";
        }
        if(selectedcategory != null && !selectedcategory.equals("null")){
            sql = "SELECT DISTINCT * FROM nomenclatures WHERE idnom IN (SELECT DISTINCT idnom FROM listcateg WHERE idcateg IN (SELECT idcateg FROM categories WHERE codecateg = '"+selectedcategory+"'))";
        }
        if(selectedtype != null && !selectedtype.equals("null") && selectedcategory != null && !selectedcategory.equals("null")){
            sql = "SELECT DISTINCT * FROM nomenclatures WHERE idtype IN (SELECT idtype FROM typesprod WHERE codetype = '\"+selectedtype+\"') AND idnom IN (SELECT DISTINCT idnom FROM listcateg WHERE idcateg IN (SELECT idcateg FROM categories WHERE codecateg = '\"+selectedcategory+\"'))";
        }

        RowMapper<Nomenclature> nomenclatureRowMapper = (r, i) -> {
            Nomenclature rowObject = new Nomenclature();
            rowObject.setId(r.getInt("idnom"));
            rowObject.setServletId("/product/imgServlet?id="+r.getInt("idnom"));
            rowObject.setIdType(r.getInt("idtype"));
            rowObject.setName(r.getString("namenom"));
            rowObject.setPrice(r.getBigDecimal("priceprod"));
            rowObject.setImage(r.getBytes("imgnom"));
            rowObject.setCountPurchase(r.getInt("countpur"));

            int idUs = loggedUserManagementService.getId();
            if(idUs == 0) {
                rowObject.setInBucket(false);
            }else{
                int idNom = r.getInt("idnom");
                String sqlBucket = "SELECT idbuc FROM bucket WHERE idnom = " + idNom+" AND idus = "+idUs;

                RowMapper<Bucket> bucketRowMapper = (c, j) -> {
                    Bucket rowBuck = new Bucket();
                    rowBuck.setId(c.getInt("idbuc"));
                    return rowBuck;
                };

                List<Bucket> buckets = jdbc.query(sqlBucket, bucketRowMapper);

                rowObject.setInBucket(buckets.size() > 0);
            }
            return rowObject;
        };

        return jdbc.query(sql, nomenclatureRowMapper);
    }
}
