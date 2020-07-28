package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    List<Employee> employeesData = new ArrayList<>(Arrays.asList(
            new Employee(4, "alibaba1", 20, "male", 6000),
            new Employee(11, "tengxun2", 19, "female", 7000),
            new Employee(6, "alibaba3", 19, "male", 8000),
            new Employee(13, "huawei", 60, "male", 4000),
            new Employee(1, "Quentin", 18, "male", 10000),
            new Employee(5, "goodboy", 70, "remale", 5000)
    ));

    @GetMapping
    public List<Employee> getAll(@RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null)
            employeesData.subList((page - 1) * pageSize, (page - 1) * pageSize + pageSize);
        return employeesData;
    }

    @GetMapping("/{id}")
    public Employee getOneByID(@PathVariable Integer id) {
        for (Employee employee : employeesData) {
            if (employee.getId() == id)
                return employee;
        }
        return null;
    }

}
