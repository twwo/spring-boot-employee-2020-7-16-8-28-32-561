package com.thoughtworks.springbootemployee.intergration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

    @Test
    void should_return_employees_when_hit_get_employee_endpoint_given_nothing() throws Exception {
        //given
        Company company = new Company(1, "OOCL", 10000, Collections.emptyList());
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(1, "ShaoLi", 22, "male", 500);
        employee.setCompanyId(1);
        employeeRepository.save(employee);

        //when
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").isNumber())
                .andExpect(jsonPath("$[0].name").value("ShaoLi"))
                .andExpect(jsonPath("$[0].age").value(22))
                .andExpect(jsonPath("$[0].gender").value("male"))
                .andExpect(jsonPath("$[0].salary").value(500))
                .andExpect(jsonPath("$[0].companyId").value(1));
    }

    @Test
    void should_return_employees_when_hit_get_employee_by_page_endpoint_given_page_and_page_size() throws Exception {
        //given
        Company company = new Company(1, "OOCL", 10000, Collections.emptyList());
        Company savedCompany = companyRepository.save(company);
        Employee employee = new Employee(1, "ShaoLi", 22, "male", 500);
        employee.setCompanyId(1);
        employeeRepository.save(employee);
        int page = 1;
        int pageSize = 1;

        //when
        mockMvc.perform(get("/employees?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].id").isNumber())
                .andExpect(jsonPath("$.content[0].name").value("ShaoLi"))
                .andExpect(jsonPath("$.content[0].age").value(22))
                .andExpect(jsonPath("$.content[0].gender").value("male"))
                .andExpect(jsonPath("$.content[0].salary").value(500))
                .andExpect(jsonPath("$.content[0].companyId").value(1));
    }

}
