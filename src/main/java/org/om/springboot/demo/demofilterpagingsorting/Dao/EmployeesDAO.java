package org.om.springboot.demo.demofilterpagingsorting.Dao;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.Validate;
import org.om.springboot.demo.demofilterpagingsorting.entity.Employee;
import org.om.springboot.demo.demofilterpagingsorting.model.EmployeeDTO;
import org.om.springboot.demo.demofilterpagingsorting.model.EmployeeWrapper;
import org.om.springboot.demo.demofilterpagingsorting.model.PageAndSort;
import org.om.springboot.demo.demofilterpagingsorting.repository.EmployeesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class EmployeesDAO {

    private final EmployeesRepository employeesRepository;

    /**
     * Prefer Constructor Injection over field injection
     *
     * @param employeesRespository
     */
    public EmployeesDAO(EmployeesRepository employeesRespository) {
        super();
        this.employeesRepository = employeesRespository;
    }

    /**
     * Get the list of Employee entities based on the specification and page request and convert that to {@link EmployeeWrapper}
     *
     * @param spec        - Specification for the Entity 'Employee'
     * @param pageRequest - PageRequest
     * @return
     */
    public EmployeeWrapper getEmployees(Specification<Employee> spec, PageRequest pageRequest) {

        Validate.notNull(spec, "Specification can't be null");
        Validate.notNull(pageRequest, "pageRequest can't be null");

        EmployeeWrapper employeesResponse = new EmployeeWrapper();


        //Get Page info from EmployeesRepository
        Page<Employee> employeesPage = employeesRepository.findAll(spec, pageRequest);

        PageAndSort pagingResponse = new PageAndSort();

        //Set the flag to indicate next page exists
        pagingResponse.setHasNextPage(employeesPage.hasNext());

        //Set the flag to indicate previous page exists
        pagingResponse.setHasPreviousPage(employeesPage.hasPrevious());

        //Set the total number of records for the given Filter Specification
        pagingResponse.setTotalNumberOfRecords(employeesPage.getTotalElements());

        //Set the total number of pages for the given filter specification and pagerequests
        pagingResponse.setTotalNumberOfPages(employeesPage.getTotalPages());

        //Page numbers are indexed from 0 but to the consume we follow start index as 1
        pagingResponse.setPageNumber(pageRequest.getPageNumber() + 1);

        //Number of records per page
        pagingResponse.setPageSize(pageRequest.getPageSize());

        employeesResponse.setPaging(pagingResponse);

        //Get the Employee List from the Page
        List<Employee> employees = employeesPage.getContent();

        //Map the data to ApiModelEmployeeResource using lambda function
        employeesResponse.setEmployees(employees.stream().map(this::getEmployee).collect(Collectors.toList()));

        return employeesResponse;

    }

    /**
     * Mapper from {@link Employee} to {@link EmployeeDTO}
     *
     * @param employee
     * @return
     */
    private EmployeeDTO getEmployee(Employee employee) {
        EmployeeDTO res = new EmployeeDTO();

        res.setDob(employee.getDateOfBirth());
        res.setFirstName(employee.getFirstName());
        res.setGender(employee.getGender());
        res.setEmployeeNo(employee.getEmployeeNo());
        res.setJoiningDate(employee.getJoiningDate());
        res.setLastName(employee.getLastName());
        res.setSalary(employee.getSalary());
        return res;

    }
}
