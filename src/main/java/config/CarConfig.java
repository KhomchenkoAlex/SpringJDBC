package config;

import model.Car;
import model.autoparts.Engine;
import model.autoparts.SummerTyres;
import model.autoparts.Tyres;
import model.autoparts.Wheel;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@ComponentScan(basePackages = {"model"})
@Configuration
@PropertySource("classpath:car.properties")
public class CarConfig {

    @Value("${car.tyreSize}")
    private int size;
    @Value("${car.tyreName}")
    private String name;
    @Value("${car.engineCapacity}")
    private int engineCapacity;
    @Value("${car.engineType}")
    private String engineType;


    @Bean(autowire = Autowire.BY_NAME)
    public SummerTyres summerTyres() {
        return new SummerTyres(size, name);
    }

    @Bean
    public Wheel wheel(Tyres summerTyres) {
        return new Wheel(summerTyres);
    }

    @Bean(autowire = Autowire.BY_NAME)
    public Engine engine() {
        return new Engine(engineCapacity, engineType);
    }

    @Bean(autowire = Autowire.BY_NAME)
    public Car car() {
        return new Car();
    }

}
