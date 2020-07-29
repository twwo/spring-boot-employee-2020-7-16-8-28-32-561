package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            , @RequestParam(value = "pageSize", required = false) Integer pageSize
            , @RequestParam(value = "gender", required = false) String gender) {
        if (page != null && pageSize != null)
            return employeesData.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        if (gender != null)
            return employeesData.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
        return employeesData;
    }

    @GetMapping("/{employeeID}")
    public Employee getEmployeeByID(@PathVariable Integer employeeID) {
        return employeesData.stream().filter(employee -> employee.getId() == employeeID).findFirst().get();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        employeesData.add(employee);
        return employee;
    }

    @PutMapping("/{employeeID}")
    public Employee modifyEmployee(@RequestBody Employee modifiedEmployee, @PathVariable Integer employeeID) {
        for (Employee employee : employeesData) {
            if (employee.getId().equals(employeeID)) {
                employee.setName(modifiedEmployee.getName());
                employee.setAge(modifiedEmployee.getAge());
                employee.setGender(modifiedEmployee.getGender());
                employee.setSalary(modifiedEmployee.getSalary());
                return employee;
            }
        }
        return null;
    }

    @DeleteMapping("/{employeeID}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteEmployee(@PathVariable Integer employeeID) {
        employeesData.removeIf(employee -> employee.getId().equals(employeeID));
    }

}
