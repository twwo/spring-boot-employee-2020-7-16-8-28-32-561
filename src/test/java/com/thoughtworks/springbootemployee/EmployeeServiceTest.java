package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
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

    @Test
    void should_return_all_employees_when_get_all_given_none() {
        //given
        EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
        EmployeeService service = new EmployeeService(employeeRepository);
        given(employeeRepository.getAll()).willReturn(new ArrayList<>());

        //when
        List<Employee> employees = service.getAll();

        //then
        assertNotNull(employees);
    }
}
