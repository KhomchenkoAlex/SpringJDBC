import config.JdbcConfig;
import dao.CarDao;
import util.Init;
import model.Car;
import config.CarConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(CarConfig.class, JdbcConfig.class);
        Car car = applicationContext.getBean(Car.class);
        CarDao carDao = applicationContext.getBean(CarDao.class);
        Init init = applicationContext.getBean(Init.class);
        init.initDataBase();
        carDao.addCar(car);
        System.out.println(carDao.getCarByID(2).toString());

    }
}


