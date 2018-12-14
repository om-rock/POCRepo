package org.om.springboot.demo.demofilterpagingsorting;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.om.springboot.demo.demofilterpagingsorting.entity.Car;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.om.springboot.demo.demofilterpagingsorting.repository.CarRepository;
import org.om.springboot.demo.demofilterpagingsorting.repository.EmployeesRepository;

@SpringBootApplication
public class DemoFilterPagingSortingApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoFilterPagingSortingApplication.class, args);
    }
/*	@Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> {
            Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
                      "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").forEach(name -> {
                Car car = new Car();
                car.setName(name);
                repository.save(car);
            });
            repository.findAll().forEach(System.out::println);
        };
    }*/
    	@Bean
    ApplicationRunner init(CarRepository repository) {
        return args -> {
          List<Car> cars= Stream.of("Ferrari", "Jaguar", "Porsche", "Lamborghini", "Bugatti",
                      "AMC Gremlin", "Triumph Stag", "Ford Pinto", "Yugo GV").map(s->{Car car=new Car();
                      car.setName(s); return car;}).collect(Collectors.toList());
          //cars.forEach(System.out::println);
          repository.saveAll(cars);
          repository.findAll().forEach(System.out::println);
        };
    }
    
    /*	@Bean
    ApplicationRunner init(EmployeesRepository repository) {
        return args -> {
            repository.findAll().forEach(System.out::println);
        };
    }*/
}
