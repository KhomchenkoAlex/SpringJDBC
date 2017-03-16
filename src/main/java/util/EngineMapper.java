package util;

import model.autoparts.Engine;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EngineMapper implements RowMapper<Engine> {
    public Engine mapRow(ResultSet rs, int rowNum) throws SQLException {
        Engine engine = new Engine(rs.getInt("eng_cap"), rs.getString("eng_type"));
        return engine;
    }
}

