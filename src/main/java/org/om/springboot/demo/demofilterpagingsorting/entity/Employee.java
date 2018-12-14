package org.om.springboot.demo.demofilterpagingsorting.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeNo;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "dob")
    private LocalDate dateOfBirth;

    @Column(name = "gender")
    private String gender;

    @Column(name = "salary")
    private Long salary;

    @Column(name = "joiningDate")
    private LocalDate joiningDate;

}
