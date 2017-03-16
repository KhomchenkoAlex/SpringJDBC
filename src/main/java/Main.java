import config.JdbcConfig;
import service.CarService;
import service.InitService;
import model.Car;
import config.CarConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(CarConfig.class, JdbcConfig.class);
        Car car = applicationContext.getBean(Car.class);
        CarService event = applicationContext.getBean(CarService.class);
        InitService initService = applicationContext.getBean(InitService.class);
        initService.initDataBase();
        event.addCar(car);
        System.out.println(event.getCarByID(1).toString());

    }
}


