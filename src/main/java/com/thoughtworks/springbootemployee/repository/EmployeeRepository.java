package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    public List<Employee> getAll() {
        return null;
    }

    public Employee getEmployeeById(Integer id) {
        return null;
    }

    public Employee addEmployee(Employee employee) {
        return employee;
    }

    public void deleteEmployee(Employee employee) {

    }

    public List<Employee> findEmployeesByPage(Integer page, Integer pageSize) {
        return null;
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return null;
    }

    public Employee updateEmployee(Employee updatedEmployee) {
        return null;
    }
}
