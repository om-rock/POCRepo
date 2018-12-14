package org.om.springboot.demo.demofilterpagingsorting.repository;

import org.om.springboot.demo.demofilterpagingsorting.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring JPA Repository with {@link JpaSpecificationExecutor}
 */
@Repository
public interface EmployeesRepository extends CrudRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

}
