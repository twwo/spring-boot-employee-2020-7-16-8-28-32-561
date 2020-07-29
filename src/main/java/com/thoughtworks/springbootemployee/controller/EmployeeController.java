package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service = new EmployeeService(new EmployeeRepository());

    @GetMapping()
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllByPage(Integer page, Integer pageSize) {
        return service.getEmployeesByPage(page, pageSize);
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllByGender(String gender) {
        return service.getEmployeesByGender(gender);
    }

    @GetMapping("/{employeeID}")
    public Employee getEmployeeByID(@PathVariable Integer employeeID) {
        return service.getEmployeeById(employeeID);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }

    @PutMapping("/{employeeID}")
    public Employee modifyEmployee(@RequestBody Employee modifiedEmployee, @PathVariable Integer employeeID) {
        return service.updateEmployee(employeeID, modifiedEmployee);
    }

    @DeleteMapping("/{employeeID}")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public Employee deleteEmployee(@PathVariable Integer employeeID) {
        return service.deleteEmployee(employeeID);
    }

}
