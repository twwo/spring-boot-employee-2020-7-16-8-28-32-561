package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmployeeRepository {

    private final List<Employee> employeesData = new ArrayList<>(Arrays.asList(
            new Employee(4, "alibaba1", 20, "male", 6000),
            new Employee(11, "tengxun2", 19, "female", 7000),
            new Employee(6, "alibaba3", 19, "male", 8000),
            new Employee(13, "huawei", 60, "male", 4000),
            new Employee(1, "Quentin", 18, "male", 10000),
            new Employee(5, "goodboy", 70, "remale", 5000)
    ));

    public List<Employee> getAll() {
        return employeesData;
    }

    public Employee getEmployeeById(Integer id) {
        return employeesData.stream().filter(employee -> id.equals(employee.getId())).findFirst().get();
    }
}
