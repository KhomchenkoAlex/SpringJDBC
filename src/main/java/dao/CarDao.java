package dao;

import model.Car;
import model.autoparts.Engine;
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
public class CarDao {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert insertCar;
    private EngineDao engineDao;
    private WheelDao wheelDao;

    public void setEngineDao(EngineDao engineDao){
        this.engineDao = engineDao;
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.wheelDao = new WheelDao(dataSource);
        this.engineDao = new EngineDao(dataSource);
        this.insertCar = new SimpleJdbcInsert(dataSource).
                withTableName("Car").usingGeneratedKeyColumns("id_car");
    }
    @Transactional
    public void addCar(Car car){
         Engine engine = car.getEngine();
         Wheel wheel = car.getWheel();
         Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("id_eng", engineDao.addEngineAndGetID(engine));
        parameters.put("id_wheel", wheelDao.addWheelsAndGetID(wheel));
         insertCar.execute(parameters);
     }
    @Transactional
     public Car getCarByID(int id_car){
         String SQL = "select id_wheel from Car where id_car = ?";
         int id_wheel = jdbcTemplate.queryForObject(SQL, new Object[]{id_car}, Integer.class);
         SQL = "select id_eng from Car where id_eng = ?";
         int id_eng = jdbcTemplate.queryForObject(SQL, new Object[]{id_car}, Integer.class);
        return new Car(wheelDao.getWheelsByID(id_wheel), engineDao.getEngineByID(id_eng));
     }
}
