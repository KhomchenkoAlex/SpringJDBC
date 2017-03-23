package dao;

import model.autoparts.Engine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import util.EngineMapper;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EngineDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertEngine;

    @Autowired
    public EngineDao(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertEngine = new SimpleJdbcInsert(dataSource)
                .withTableName("engine")
                .usingGeneratedKeyColumns("id_eng");
    }

    public int addEngineAndGetID(Engine engine) {
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("eng_type", engine.getEngineType());
        parameters.put("eng_cap", engine.getEngineCapacity());
        Number id_eng = insertEngine.executeAndReturnKey(parameters);
        return id_eng.intValue();
    }

    public Engine getEngineByID(int id_eng) {
        String SQL = "select * from Engine where id_eng = ?";
        Engine engine = jdbcTemplate.queryForObject(SQL, new Object[]{id_eng}, new EngineMapper());
        return engine;
    }
}
