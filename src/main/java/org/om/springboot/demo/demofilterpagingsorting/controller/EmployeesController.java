package org.om.springboot.demo.demofilterpagingsorting.controller;

import org.om.springboot.demo.demofilterpagingsorting.Dao.EmployeesDAO;
import org.om.springboot.demo.demofilterpagingsorting.entity.Employee;
import org.om.springboot.demo.demofilterpagingsorting.entity.EmployeeFilterSpecification;
import org.om.springboot.demo.demofilterpagingsorting.model.EmployeeWrapper;
import org.om.springboot.demo.demofilterpagingsorting.page.PageRequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeesController {
    @Autowired
    private EmployeesDAO employeesDAO;
    @Autowired
    private EmployeeFilterSpecification employeeFilterSpecification;

    @GetMapping("/employees")
    public EmployeeWrapper getEmployees() {
        Specification<Employee> specs = Specification
                //Exposed attributes in API swagger spec doesn't need to be same as Database table column names.
                .where(employeeFilterSpecification.getStringTypeSpecification("firstName", "eq:Vijay"))
                .and(employeeFilterSpecification.getStringTypeSpecification("lastName", ""))
                .and(employeeFilterSpecification.getLongTypeSpecification("salary", ""))
                .and(employeeFilterSpecification.getDateTypeSpecification("dob", ""))
                .and(employeeFilterSpecification.getDateTypeSpecification("joiningDate", ""));


        //This represents the Page config with sorting
        PageRequest pageRequest = PageRequestBuilder.getPageRequest(10, 1, "-salary");

        //Call the DAO with specifications and pagerequest
        EmployeeWrapper employees = employeesDAO.getEmployees(specs, pageRequest);

        //Return the sorting criteria back so that the consumer can pass the same sorting or of different sorting based on the usecases.
        employees.getPaging().setSortingCriteria("-salary");

        return employees;
    }

}
