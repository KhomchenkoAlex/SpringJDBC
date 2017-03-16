package service;

import model.autoparts.SummerTyres;
import model.autoparts.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import util.TyreMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
public class TyresService {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertTyres;

    @Autowired
    public TyresService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertTyres = new SimpleJdbcInsert(dataSource)
                .withTableName("tyres")
                .usingGeneratedKeyColumns("id_tyres");
    }

    public int addTyresAndGetID(SummerTyres tyres){
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("tyres_size", tyres.getSize());
        parameters.put("tyres_name", tyres.getName());
        Number id_tyres = insertTyres.executeAndReturnKey(parameters);
        return id_tyres.intValue();
    }

    public Tyres getTyresByID(int id_tyres) {
        String SQL = "select * from Tyres where id_tyres = ?";
        Tyres tyres = jdbcTemplate.queryForObject(SQL, new Object[]{id_tyres}, new TyreMapper());
        return tyres;
    }
}
