package com.thoughtworks.springbootemployee.intergration;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
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
public class CompanyIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    private final List<Company> testCompanies = Arrays.asList(
            new Company(null, "OOCL", 10000, Collections.singletonList(
                    new Employee(null, "OOCL1", 20, "male", 500, 1))),
            new Company(null, "KFC", 10000, Collections.singletonList(
                    new Employee(null, "KFC1", 22, "male", 500, 2))),
            new Company(null, "Alibaba", 10000, Collections.singletonList(
                    new Employee(null, "Alibaba1", 22, "male", 500, 3)))
    );

    @AfterEach
    private void afterAll() {
        companyRepository.deleteAll();
    }

    @Test
    void should_return_companies__when_hit_get_all_companies_given_nothing() throws Exception {
        //given
        Company addedCompany = companyRepository.save(testCompanies.get(0));

        //when
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").value(addedCompany.getId()));
    }

    @Test
    void should_return_companies_when_hit_get_company_by_page_endpoint_given_page_and_page_size() throws Exception {
        //given
        companyRepository.saveAll(testCompanies);
        int page = 2;
        int pageSize = 1;

        //when
        mockMvc.perform(get("/companies?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(pageSize)))
                .andExpect(jsonPath("$.number").value(page - 1));
    }

    @Test
    void should_return_companies_when_hit_get_company_by_id_given_id() throws Exception {
        //given
        Company addedCompany = companyRepository.save(testCompanies.get(0));

        //when
        mockMvc.perform(get("/companies/" + addedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(addedCompany.getId()));
    }

    @Test
    void should_return_employees_when_hit_get_company_employees_by_id_given_company_id() throws Exception {
        //given
        Company addedCompany = companyRepository.save(testCompanies.get(0));

        //when
        mockMvc.perform(get("/companies/" + addedCompany.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employees[0]").value(addedCompany.getEmployees().get(0)));
    }

    @Test
    void should_return_company_when_hit_add_company_endpoint_given_company() throws Exception {
        //given
        String comanyInfo = "{\n" +
                "    \"companyName\": \"bilibili\",\n" +
                "    \"employeesNumber\": 1\n" +
                "}";
        //then
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(comanyInfo))
                .andExpect(status().isCreated());

        Company addedCompany = companyRepository.findAll().get(0);
        assertNotNull(addedCompany);
        assertEquals("bilibili", addedCompany.getCompanyName());
        assertEquals(1, addedCompany.getEmployeesNumber());
    }

    @Test
    void should_return_company_when_hit_update_company_endpoint_given_new_company() throws Exception {
        //given
        Company addedCompany = companyRepository.save(testCompanies.get(0));
        String comanyInfo = "{\n" +
                "    \"id\": " + addedCompany.getId() + ",\n" +
                "    \"companyName\": \"TW\",\n" +
                "    \"employeesNumber\": 3\n" +
                "}";
        //then
        mockMvc.perform(put("/companies/1").contentType(MediaType.APPLICATION_JSON).content(comanyInfo))
                .andExpect(status().isOk());

        Company updatedCompany = companyRepository.findById(addedCompany.getId()).orElse(null);
        assertNotNull(updatedCompany);
        assertEquals(addedCompany.getId(), updatedCompany.getId());
        assertEquals("TW", updatedCompany.getCompanyName());
        assertEquals(3, updatedCompany.getEmployeesNumber());
    }

    @Test
    void should_delete_company_when_hit_delete_company_endpoint_given_id() throws Exception {
        //given
        Company addedCompany = companyRepository.save(testCompanies.get(0));

        //then
        mockMvc.perform(delete("/companies/" + addedCompany.getId()).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Company deletedCompany = companyRepository.findById(addedCompany.getId()).orElse(null);
        assertNull(deletedCompany);
    }
}
