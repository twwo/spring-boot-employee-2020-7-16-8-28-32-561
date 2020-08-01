package com.thoughtworks.springbootemployee.intergration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private final Company testCompany = new Company(
            null, "OOCL", 10000, Collections.emptyList());

    private final List<Employee> testEmployeesData = Arrays.asList(
            new Employee(null, "Shao1", 22, "male", 500, 1),
            new Employee(null, "Shao2", 22, "male", 500, 1),
            new Employee(null, "Shao3", 22, "male", 500, 1),
            new Employee(null, "Shao4", 22, "male", 500, 1),
            new Employee(null, "Shao5", 22, "male", 500, 1),
            new Employee(null, "Shao6", 22, "male", 500, 1)
    );

    @BeforeEach
    private void init() {
        companyRepository.save(testCompany);
    }

    @AfterEach
    private void afterEach() {
        employeeRepository.deleteAll();
        companyRepository.deleteAll();
    }

    @Test
    void should_return_employees_when_hit_get_employee_endpoint_given_nothing() throws Exception {
        //given
        Employee employee = testEmployeesData.get(0);
        employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value(employee.getName()))
                .andExpect(jsonPath("$[0].age").value(employee.getAge()))
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()))
                .andExpect(jsonPath("$[0].salary").value(employee.getSalary()))
                .andExpect(jsonPath("$[0].companyId").value(employee.getCompanyId()));
    }

    @Test
    void should_return_employees_when_hit_get_employee_by_page_endpoint_given_page_and_page_size() throws Exception {
        //given
        employeeRepository.saveAll(testEmployeesData);
        int page = 2;
        int pageSize = 3;

        //when
        mockMvc.perform(get("/employees?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(pageSize)))
                .andExpect(jsonPath("$.number").value(page - 1));
    }

    @Test
    void should_return_employee_when_hit_get_employee_by_gender_endpoint_given_gender() throws Exception {
        //given
        Employee employee = testEmployeesData.get(0);
        employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees?gender=" + employee.getGender()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].gender").value(employee.getGender()));
    }

    @Test
    void should_return_employee_when_hit_get_employee_by_id_given_id() throws Exception {
        //given
        Employee addedEmployee = employeeRepository.save(testEmployeesData.get(0));

        //when
        mockMvc.perform(get("/employees?id=" + addedEmployee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(addedEmployee.getId()));
    }

    @Test
    void should_create_employee_when_hit_add_employee_given_employee() throws Exception {
        //given
        String employeeInfo = "{\n" +
                "                \"id\": 1,\n" +
                "                \"name\": \"ShaoLi\",\n" +
                "                \"age\": 22,\n" +
                "                \"gender\": \"male\",\n" +
                "                \"salary\": 5000,\n" +
                "                \"companyId\": 1\n" +
                "            }";
        //when
        mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON).content(employeeInfo))
                .andExpect(status().isCreated());

        Employee addedEmployee = employeeRepository.findAll().get(0);
        assertNotNull(addedEmployee);
        assertEquals("ShaoLi", addedEmployee.getName());
        assertEquals(22, addedEmployee.getAge());
        assertEquals("male", addedEmployee.getGender());
        assertEquals(5000, addedEmployee.getSalary());
        assertEquals(1, addedEmployee.getCompanyId());
    }

    @Test
    void should_update_employee_when_hit_update_employee_given_new_employee() throws Exception {
        //given
        Employee addedEmployee = employeeRepository.save(new Employee(null, "aaa", 0, "female", 500, 1));
        String newEmployee = "{\n" +
                "                \"id\": " + addedEmployee.getId() + ",\n" +
                "                \"name\": \"ShaoLi\",\n" +
                "                \"age\": 22,\n" +
                "                \"gender\": \"male\",\n" +
                "                \"salary\": 5000,\n" +
                "                \"companyId\": 1\n" +
                "            }";
        //when
        mockMvc.perform(put("/employees/" + addedEmployee.getId()).contentType(MediaType.APPLICATION_JSON).content(newEmployee))
                .andExpect(status().isOk());

        Employee updatedEmployee = employeeRepository.findById(addedEmployee.getId()).orElse(null);
        assertNotNull(updatedEmployee);
        assertEquals(addedEmployee.getId(), updatedEmployee.getId());
        assertEquals("ShaoLi", updatedEmployee.getName());
        assertEquals(22, updatedEmployee.getAge());
        assertEquals("male", updatedEmployee.getGender());
        assertEquals(5000, updatedEmployee.getSalary());
        assertEquals(1, updatedEmployee.getCompanyId());
    }

    @Test
    void should_delete_employee_when_hit_delete_employee_endpoint_given_id() throws Exception {
        //given
        Employee addedEmployee = employeeRepository.save(new Employee(null, "aaa", 0, "female", 500, 1));

        //when
        mockMvc.perform(delete("/employees/" + addedEmployee.getId()))
                .andExpect(status().isOk());

        Employee deleteEmployee = employeeRepository.findById(addedEmployee.getId()).orElse(null);
        assertNull(deleteEmployee);
    }
}
