package org.om.springboot.demo.demofilterpagingsorting.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class EmployeeWrapper implements Serializable {
    private static final long serialVersionUID = -5255762094833703923L;
    private List<EmployeeDTO> employees;
    private PageAndSort paging;

    
}