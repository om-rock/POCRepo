package org.om.springboot.demo.demofilterpagingsorting.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private LocalDate dob;
    private LocalDate joiningDate;
    private Long salary;
    private Integer employeeNo;
}
