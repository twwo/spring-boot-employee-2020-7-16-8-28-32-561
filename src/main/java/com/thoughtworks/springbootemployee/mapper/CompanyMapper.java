package com.thoughtworks.springbootemployee.mapper;

import com.thoughtworks.springbootemployee.dto.CompanyRequest;
import com.thoughtworks.springbootemployee.model.Company;

public class CompanyMapper {
    public Company toCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setId(companyRequest.getId());
        company.setCompanyName(companyRequest.getCompanyName());
        company.setEmployeesNumber(companyRequest.getEmployeesNumber());
        company.setEmployees(companyRequest.getEmployees());
        return company;
    }
}
