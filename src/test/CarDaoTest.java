import config.CarConfig;
import config.JdbcConfig;
import model.Car;
import model.autoparts.Engine;
import model.autoparts.SummerTyres;
import model.autoparts.Tyres;
import model.autoparts.Wheel;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.notification.Failure;

import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import dao.CarDao;
import dao.EngineDao;

import static org.mockito.Mockito.*;

import org.junit.runner.Result;
import util.Init;

public class CarDaoTest {
    @Test
    public void addCarTest() throws Exception {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(CarConfig.class, JdbcConfig.class);
        EngineDao engineDaoMock = mock(EngineDao.class);
        Init init = (Init) applicationContext.getBean("Init");
        CarDao carDao = (CarDao) applicationContext.getBean("CarDao");
        Tyres tyres = new SummerTyres(20, "Belshina");
        Wheel wheel = new Wheel(tyres);
        Engine engine = new Engine(265, "AXCD-10XD");
        Car car = new Car(wheel, engine);

        when(engineDaoMock.addEngineAndGetID(engine)).thenThrow(new RuntimeException());
        carDao.setEngineDao(engineDaoMock);
        int countOfRowBefore = init.countRowInTableWheel();
        try {
            carDao.addCar(car);
        } catch (RuntimeException e) {
            int countOfRowAfter = init.countRowInTableWheel();
            assertEquals(countOfRowBefore, countOfRowAfter);
        }
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(CarDaoTest.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
    }
}

