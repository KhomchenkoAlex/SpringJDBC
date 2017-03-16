package service;


import model.autoparts.SummerTyres;
import model.autoparts.Wheel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class WheelService {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertWheel;
    private TyresService tyresService;

    @Autowired
    public WheelService(DataSource dataSource) {
        this.dataSource = dataSource;
        this.tyresService = new TyresService(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.insertWheel = new SimpleJdbcInsert(dataSource)
                .withTableName("wheel")
                .usingGeneratedKeyColumns("id_wheel");
    }

    public int addWheelsAndGetID(Wheel wheel) {
        SummerTyres tyres = (SummerTyres) wheel.getTyres();
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("id_tyres", tyresService.addTyresAndGetID(tyres));
        Number id_wheel = insertWheel.executeAndReturnKey(parameters);
        return id_wheel.intValue();
    }

    public Wheel getWheelsByID(int id_wheel) {
        String SQL = "select id_tyres from Wheel where id_wheel = ?";
        int id_tyres = jdbcTemplate.queryForObject(SQL, new Object[]{id_wheel}, Integer.class);
        return new Wheel(tyresService.getTyresByID(id_tyres));
    }
}
