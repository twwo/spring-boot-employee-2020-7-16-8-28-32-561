package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class CompanyServiceTest {

    private final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private final CompanyService companyService = new CompanyService(companyRepository);

    @Test
    void should_return_companies_when_getAll_given_none() {
        //given
        given(companyRepository.getAll()).willReturn(Arrays.asList(
                new Company(1, "alibaba", 200, new ArrayList<>(Arrays.asList(
                        new Employee(4, "alibaba1", 20, "male", 6000),
                        new Employee(11, "tengxun2", 19, "female", 7000),
                        new Employee(6, "alibaba3", 19, "male", 8000),
                        new Employee(13, "huawei", 60, "male", 4000),
                        new Employee(1, "Quentin", 18, "male", 10000),
                        new Employee(5, "goodboy", 70, "female", 5000)
                ))),
                new Company(2, "tx", 100, new ArrayList<>(Arrays.asList(
                        new Employee(4, "tx", 20, "male", 6000),
                        new Employee(5, "gd", 70, "remale", 5000)
                )))
        ));

        //when
        List<Company> companies = companyService.getAll();
        //then
        assertNotNull(companies);
        assertEquals(2, companies.size());
    }
}
