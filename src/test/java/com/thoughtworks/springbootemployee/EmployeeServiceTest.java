package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {

    private EmployeeRepository employeeRepository;
    private EmployeeService service;

    @BeforeEach
    void setUp() {
        employeeRepository = mock(EmployeeRepository.class);
        service = new EmployeeService(employeeRepository);
    }

    @Test
    void should_return_all_employees_when_get_all_given_none() {
        //given
        given(employeeRepository.getAll()).willReturn(new ArrayList<>());

        //when
        List<Employee> employees = service.getAll();

        //then
        assertNotNull(employees);
    }

    @Test
    void should_return_employee_when_get_by_id_given_id() {
        //given
        int id = 1;
        given(employeeRepository.getEmployeeById(id)).willReturn(new Employee(1, "ShaoLi", 22, "male", 6000));

        //when
        Employee employee = service.getEmployeeById(id);

        //then
        assertNotNull(employee);
        assertEquals(id, employee.getId());
    }
}
