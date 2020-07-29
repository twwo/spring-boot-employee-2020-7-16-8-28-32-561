package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.getAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.getCompanyById(id);
    }

    public List<Company> getCompaniesByPage(int page, int pageSize) {
        return companyRepository.getCompaniesByPage(page, pageSize);
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) {
        Company company = companyRepository.getCompanyById(companyId);
        if (company != null) {
            return company.getEmployees();
        }
        return null;
    }

    public Company addCompany(Company company) {
        return null;
    }
}
