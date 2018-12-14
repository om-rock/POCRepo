package org.om.springboot.demo.demofilterpagingsorting.repository;

import org.om.springboot.demo.demofilterpagingsorting.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CarRepository extends JpaRepository<Car, Long> {
}