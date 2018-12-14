package org.om.springboot.demo.demofilterpagingsorting.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class PageAndSort implements Serializable {
    private static final long serialVersionUID = 7142358879822679800L;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalNumberOfRecords;
    private Integer totalNumberOfPages;
    private Boolean hasNextPage;
    private Boolean hasPreviousPage;
    private String sortingCriteria;

}
