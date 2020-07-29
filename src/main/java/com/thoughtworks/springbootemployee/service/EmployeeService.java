package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.getEmployeeById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = employeeRepository.getEmployeeById(employee.getId());
        if (updatedEmployee != null) {
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setAge(employee.getAge());
            updatedEmployee.setGender(employee.getGender());
            updatedEmployee.setSalary(employee.getSalary());
            updatedEmployee = employeeRepository.updateEmployee(updatedEmployee);
        }
        return updatedEmployee;
    }

    public Employee deleteEmployee(Integer id) {
        Employee deletedEmployee = employeeRepository.getEmployeeById(id);
        if (deletedEmployee != null) {
            employeeRepository.deleteEmployee(deletedEmployee);
        }
        return deletedEmployee;
    }

    public List<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeRepository.findEmployeesByPage(page, pageSize);
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.getEmployeesByGender(gender);
    }
}
