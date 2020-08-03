package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequest;
import com.thoughtworks.springbootemployee.dto.EmployeeResponse;
import com.thoughtworks.springbootemployee.exception.IllegalOperationException;
import com.thoughtworks.springbootemployee.exception.NotSuchDataException;
import com.thoughtworks.springbootemployee.mapper.EmployeeMapper;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

    private final EmployeeRepository employeeRepository = mock(EmployeeRepository.class);
    private final EmployeeMapper employeeMapper = new EmployeeMapper();
    private final EmployeeService employeeService = new EmployeeService(employeeRepository, employeeMapper);

    @Test
    void should_return_all_employees_when_get_all_given_none() {
        //given
        given(employeeRepository.findAll()).willReturn(new ArrayList<>(Arrays.asList(
                new Employee(4, "alibaba1", 20, "male", 6000, 1),
                new Employee(1, "Quentin", 18, "male", 10000, 1)
        )));

        //when
        List<Employee> employees = employeeService.getAll();

        //then
        assertNotNull(employees);
        assertEquals(2, employees.size());
    }

    @Test
    void should_return_employee_when_get_by_id_given_id() throws NotSuchDataException {
        //given
        Integer id = 1;
        given(employeeRepository.findById(id))
                .willReturn(Optional.of(new Employee(1, "Quentin", 18, "male", 10000, 1)));

        //when
        EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);

        //then
        assertNotNull(employeeResponse);
        assertEquals(id, employeeResponse.getId());
    }

    @Test
    void should_return_employee_when_add_employee_given_employee() {
        //given
        EmployeeRequest employeeRequest = new EmployeeRequest(null, "ggggggg", 20, "male", 100, 1);

        Employee employee = employeeMapper.toEmployee(employeeRequest);
        given(employeeRepository.save(any(Employee.class))).willReturn(employee);

        //when
        Employee addedEmployee = employeeService.addEmployee(employeeRequest);

        //then
        assertNotNull(addedEmployee);
        assertEquals(employee, addedEmployee);
    }

    @Test
    void should_return_updated_employee_when_update_given_employee_and_employeeId_is_1() throws NotSuchDataException, IllegalOperationException {
        //given
        int employeeId = 1;
        Employee employee = new Employee(1, "HHHHHHH", 30, "female", 200, 1);
        given(employeeRepository.findById(employeeId)).willReturn(Optional.of(employee));
        given(employeeRepository.save(employee)).willReturn(employee);

        //when
        Employee updatedEmployee = employeeService.updateEmployee(employeeId, employee);

        //then
        assertNotNull(updatedEmployee);
        assertEquals(employee.getId(), updatedEmployee.getId());
        assertEquals(employee.getName(), updatedEmployee.getName());
        assertEquals(employee.getAge(), updatedEmployee.getAge());
        assertEquals(employee.getGender(), updatedEmployee.getGender());
        assertEquals(employee.getSalary(), updatedEmployee.getSalary());
    }

    @Test
    void should_return_deleted_employee_when_delete_given_id() throws NotSuchDataException {
        //given
        Integer id = 1;
        given(employeeRepository.findById(id)).willReturn(
                Optional.of(new Employee(1, "Quentin", 18, "male", 10000, 1)));

        //when
        Employee employee = employeeService.deleteEmployee(id);

        //then
        assertNotNull(employee);
        assertEquals(id, employee.getId());
    }

    @Test
    void should_return_employees_when_query_by_page_given_page_and_pageSize() {
        //given
        int page = 1;
        int pageSize = 5;
        given(employeeRepository.findAll(PageRequest.of(page - 1, pageSize))).willReturn(Page.empty());

        //when
        Page<Employee> employees = employeeService.getEmployeesByPage(page, pageSize);

        //then
        assertNotNull(employees);
    }

    @Test
    void should_return_employees_when_query_by_gender_given_gender_is_male() {
        //given
        String gender = "male";
        given(employeeRepository.findByGender(gender)).willReturn(Arrays.asList(
                new Employee(1, "Quentin", 18, "male", 10000, 1),
                new Employee(2, "Quentin", 18, "male", 10000, 1),
                new Employee(3, "Quentin", 18, "male", 10000, 1),
                new Employee(4, "Quentin", 18, "male", 10000, 1),
                new Employee(5, "Quentin", 18, "male", 10000, 1)
        ));
        //when
        List<Employee> employees = employeeService.getEmployeesByGender(gender);

        //then
        assertNotNull(employees);
        assertEquals(5, employees.size());
    }

    @Test
    void should_throw_exception_when_delete_given_id_not_exists() {
        //given

        //when
        assertThrows(NotSuchDataException.class,
                () -> employeeService.deleteEmployee(1));
    }

    @Test
    void should_throw_exception_when_update_given_id_not_exists() {
        //given
        Employee employee = new Employee();
        employee.setId(1);
        //when
        assertThrows(NotSuchDataException.class,
                () -> employeeService.updateEmployee(1, employee));
    }

    @Test
    void should_throw_exception_when_update_given_id_not_equals_updated_employee_id() {
        //given

        //when
        assertThrows(IllegalOperationException.class,
                () -> employeeService.updateEmployee(1, new Employee(2, "ShaoLi", 22, "male", 90, 1)));
    }
}
