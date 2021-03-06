package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.ExceptionMessage;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NotSuchDataException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    public EmployeeService(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public EmployeeResponse getEmployeeById(Integer id) throws GlobalException {
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee == null) {
            throw new GlobalException(ExceptionMessage.NOT_SUCH_DATA.getMessage());
        }
        return employeeMapper.toEmployeeResponse(employee);
    }

    public Employee addEmployee(EmployeeRequest employeeRequest) {
        return employeeRepository.save(employeeMapper.toEmployee(employeeRequest));
    }

    public Employee updateEmployee(Integer employeeId, Employee employee) throws GlobalException {
        if (!employeeId.equals(employee.getId())) {
            throw new GlobalException(ExceptionMessage.ILLEGAL_OPERATION.getMessage());
        }
        Employee updatedEmployee = employeeRepository.findById(employeeId).orElse(null);
        if (updatedEmployee != null) {
            //todo
            updatedEmployee.setName(employee.getName());
            updatedEmployee.setAge(employee.getAge());
            updatedEmployee.setGender(employee.getGender());
            updatedEmployee.setSalary(employee.getSalary());
            updatedEmployee = employeeRepository.save(updatedEmployee);
        } else {
            throw new GlobalException(ExceptionMessage.NOT_SUCH_DATA.getMessage());
        }
        return updatedEmployee;
    }

    public Employee deleteEmployee(Integer id) throws GlobalException {
        Employee deletedEmployee = employeeRepository.findById(id).orElse(null);
        if (deletedEmployee != null) {
            employeeRepository.delete(deletedEmployee);
        } else {
            throw new GlobalException(ExceptionMessage.NOT_SUCH_DATA.getMessage());
        }
        return deletedEmployee;
    }

    public Page<Employee> getEmployeesByPage(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public List<Employee> getEmployeesByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }
}
