package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.dto.CompanyResponse;
import com.thoughtworks.springbootemployee.model.Company;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public Company toCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setId(companyRequest.getId());
        company.setCompanyName(companyRequest.getCompanyName());
        company.setEmployeesNumber(companyRequest.getEmployeesNumber());
        company.setEmployees(companyRequest.getEmployees());
        return company;
    }

    public CompanyResponse toCompanyResponse(Company company) {
        CompanyResponse companyResponse = new CompanyResponse();
        companyResponse.setId(company.getId());
        companyResponse.setCompanyName(company.getCompanyName());
        companyResponse.setEmployeesNumber(company.getEmployeesNumber());
        companyResponse.setEmployees(company.getEmployees());
        return companyResponse;
    }
}
