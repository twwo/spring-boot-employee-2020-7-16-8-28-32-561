package com.thoughtworks.springbootemployee.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeRequest {
    private Integer id;
    private String name;
    private Integer age;
    private String gender;
    private Integer salary;
    private Integer companyId;
}
