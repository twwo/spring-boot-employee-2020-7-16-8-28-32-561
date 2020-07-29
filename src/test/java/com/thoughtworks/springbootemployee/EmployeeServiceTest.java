package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EmployeeServiceTest {

    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final EmployeeService service = new EmployeeService(employeeRepository);

    @Test
    void should_return_all_employees_when_get_all_given_none() {
        //given

        //when
        List<Employee> employees = service.getAll();

        //then
        assertNotNull(employees);
    }

    @Test
    void should_return_employee_when_get_by_id_given_id() {
        //given
        int id = 1;

        //when
        Employee employee = service.getEmployeeById(id);

        //then
        assertNotNull(employee);
        assertEquals(id, employee.getId());
    }
}
