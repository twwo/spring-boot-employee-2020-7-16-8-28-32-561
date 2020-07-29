package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeServiceTest {

    @Test
    void should_return_all_employees_when_get_all_given_none() {
        //given
        EmployeeService service = new EmployeeService();

        //when
        List<Employee> employees = service.getAll();

        //then
        assertNotNull(employees);
    }
}
