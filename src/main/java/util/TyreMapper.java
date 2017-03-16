package util;

import model.autoparts.SummerTyres;
import model.autoparts.Tyres;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TyreMapper implements RowMapper<Tyres> {
    public Tyres mapRow(ResultSet rs, int rowNum) throws SQLException {
        Tyres tyre = new SummerTyres(rs.getInt("tyres_size"),rs.getString("tyres_name"));
        return tyre;
    }
}
