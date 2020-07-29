package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeService service = new EmployeeService(employeeRepository);

    @Test
    void should_return_all_employees_when_get_all_given_none() {
        //given
        given(employeeRepository.getAll()).willReturn(new ArrayList<>(Arrays.asList(
                new Employee(4, "alibaba1", 20, "male", 6000),
                new Employee(1, "Quentin", 18, "male", 10000)
        )));

        //when
        List<Employee> employees = service.getAll();

        //then
        assertNotNull(employees);
        assertEquals(2, employees.size());
    }

    @Test
    void should_return_employee_when_get_by_id_given_id() {
        //given
        Integer id = 1;
        given(employeeRepository.getEmployeeById(id)).willReturn(new Employee(1, "Quentin", 18, "male", 10000));

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
        given(employeeRepository.addEmployee(employee)).willReturn(employee);

        //when
        Employee addedEmployee = service.addEmployee(employee);

        //then
        assertNotNull(addedEmployee);
        assertEquals(employee, addedEmployee);
    }

    @Test
    void should_return_updated_employee_when_update_given_employee() {
        //given
        Employee employee = new Employee(1, "HHHHHHH", 30, "female", 200);
        given(employeeRepository.getEmployeeById(employee.getId())).willReturn(new Employee(1, "Quentin", 18, "male", 10000));

        //when
        Employee updatedEmployee = service.updateEmployee(employee);

        //then
        assertNotNull(updatedEmployee);
        assertEquals(employee.getId(), updatedEmployee.getId());
        assertEquals(employee.getName(), updatedEmployee.getName());
        assertEquals(employee.getAge(), updatedEmployee.getAge());
        assertEquals(employee.getGender(), updatedEmployee.getGender());
        assertEquals(employee.getSalary(), updatedEmployee.getSalary());
    }

    @Test
    void should_return_deleted_employee_when_delete_given_id() {
        //given
        Integer id = 1;
        given(employeeRepository.getEmployeeById(id)).willReturn(new Employee(1, "Quentin", 18, "male", 10000));

        //when
        Employee employee = service.deleteEmployee(id);

        //then
        assertNotNull(employee);
        assertEquals(id, employee.getId());
    }

    @Test
    void should_return_employees_when_query_by_page_given_page_and_pageSize() {
        //given
        int page = 1;
        int pageSize = 5;
        given(employeeRepository.findEmployeesByPage(page, pageSize)).willReturn(Arrays.asList(
                new Employee(1, "Quentin", 18, "male", 10000),
                new Employee(2, "Quentin", 18, "male", 10000),
                new Employee(3, "Quentin", 18, "male", 10000),
                new Employee(4, "Quentin", 18, "male", 10000),
                new Employee(5, "Quentin", 18, "male", 10000)
        ));

        //when
        List<Employee> employees = service.getEmployeesByPage(page, pageSize);

        //then
        assertNotNull(employees);

        assertEquals(5, employees.size());
    }

    @Test
    void should_return_employees_when_query_by_gender_given_gender_is_male() {
        //given
        String gender = "male";
        given(employeeRepository.getEmployeesByGender(gender)).willReturn(Arrays.asList(
                new Employee(1, "Quentin", 18, "male", 10000),
                new Employee(2, "Quentin", 18, "male", 10000),
                new Employee(3, "Quentin", 18, "male", 10000),
                new Employee(4, "Quentin", 18, "male", 10000),
                new Employee(5, "Quentin", 18, "male", 10000)
        ));
        //when
        List<Employee> employees = service.getEmployeesByGender(gender);

        //then
        assertNotNull(employees);
        assertEquals(5, employees.size());
    }
}
