package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyMapperTest {
    @Test
    void should_return_company_when_toCompany_given_companyRequest() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        CompanyRequest companyRequest = new CompanyRequest(1, "OOCL", 1, Collections.emptyList());

        //when
        Company company = companyMapper.toCompany(companyRequest);

        //then
        assertEquals(companyRequest.getId(), company.getId());
        assertEquals(companyRequest.getCompanyName(), company.getCompanyName());
        assertEquals(companyRequest.getEmployeesNumber(), company.getEmployeesNumber());
        assertEquals(companyRequest.getEmployees(), company.getEmployees());
    }

    @Test
    void should_return_companyResponse_when_toCompanyResponse_given_company() {
        //given
        CompanyMapper companyMapper = new CompanyMapper();
        Company company = new Company(1, "OOCL", 1, Collections.emptyList());

        //when
        CompanyResponse companyResponse = companyMapper.toCompanyResponse(company);

        //then
        assertEquals(company.getId(), companyResponse.getId());
        assertEquals(company.getCompanyName(), companyResponse.getCompanyName());
        assertEquals(company.getEmployeesNumber(), companyResponse.getEmployeesNumber());
        assertEquals(company.getEmployees(), companyResponse.getEmployees());
    }
}
