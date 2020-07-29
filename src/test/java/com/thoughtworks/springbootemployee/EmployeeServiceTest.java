package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        Integer id = 1;

        //when
        Employee employee = service.getEmployeeById(id);

        //then
        assertNotNull(employee);
        assertEquals(id, employee.getId());
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        //given
        Employee employee = new Employee(80, "ggggggg", 20, "male", 100);

        //when
        Employee addedEmployee = service.addEmployee(employee);

        //then
        assertNotNull(addedEmployee);
        assertEquals(employee, addedEmployee);
    }
}
