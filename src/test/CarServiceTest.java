import config.CarConfig;
import config.JdbcConfig;
import model.Car;
import model.autoparts.Engine;
import model.autoparts.SummerTyres;
import model.autoparts.Tyres;
import model.autoparts.Wheel;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.internal.matchers.Null;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.CarService;
import service.EngineService;
import java.sql.SQLException;
import static org.mockito.Mockito.*;
import org.junit.runner.Result;


public class CarServiceTest {
    @Test
    public void addCarTest() throws Exception {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(CarConfig.class, JdbcConfig.class);
        EngineService engineServiceMock = mock(EngineService.class);
        CarService carService = (CarService) applicationContext.getBean("CarService");
        Tyres tyres = new SummerTyres(20,"Belshina");
        Wheel wheel = new Wheel(tyres);
        Engine engine = new Engine(265, "AXCD-10XD");
        Car car = new Car(wheel,engine);
        when(engineServiceMock.addEngineAndGetID(engine)).thenThrow(new RuntimeException());
        carService.addCar(car);
        org.springframework.util.Assert.isNull(car.getWheel());
    }
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CarServiceTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}

