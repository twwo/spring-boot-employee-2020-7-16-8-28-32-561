package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.model.Employee;

public class EmployeeMapper {
    public Employee toEmployee(EmployeeRequest employeeRequest) {
        Employee employee = new Employee();
        employee.setId(employeeRequest.getId());
        employee.setName(employeeRequest.getName());
        employee.setAge(employeeRequest.getAge());
        employee.setGender(employeeRequest.getGender());
        employee.setSalary(employeeRequest.getSalary());
        employee.setCompanyId(employeeRequest.getCompanyId());
        return employee;
    }

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setId(employee.getId());
        employeeResponse.setName(employee.getName());
        employeeResponse.setAge(employee.getAge());
        employeeResponse.setGender(employee.getGender());
        employeeResponse.setSalary(employee.getSalary());
        employeeResponse.setCompanyId(employee.getCompanyId());
        return employeeResponse;
    }
}
