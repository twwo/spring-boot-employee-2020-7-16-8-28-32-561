package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.exception.ExceptionMessage;
import com.thoughtworks.springbootemployee.exception.GlobalException;
import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) throws GlobalException {
        Company company = companyRepository.findById(id).orElse(null);
        if (company == null) {
            throw new GlobalException(ExceptionMessage.NOT_SUCH_DATA.getMessage());
        }
        return company;
    }

    public Page<Company> getCompaniesByPage(int page, int pageSize) {
        return companyRepository.findAll(PageRequest.of(page - 1, pageSize));
    }

    public List<Employee> getEmployeesByCompanyId(int companyId) throws GlobalException {
        Company company = getCompanyById(companyId);
        if (company != null) {
            return company.getEmployees();
        }
        return null;
    }

    public Company addCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompany(Integer companyId, Company company) throws GlobalException {
        if (!companyId.equals(company.getId())) {
            throw new GlobalException(ExceptionMessage.ILLEGAL_OPERATION.getMessage());
        }
        Company fetchedCompany = getCompanyById(companyId);
        fetchedCompany.setCompanyName(company.getCompanyName());
        fetchedCompany.setEmployees(company.getEmployees());
        fetchedCompany.setEmployeesNumber(company.getEmployeesNumber());
        fetchedCompany = companyRepository.save(company);
        return fetchedCompany;
    }

    public Company deleteCompanyById(Integer companyId) throws GlobalException {
        Company fetchedCompany = getCompanyById(companyId);
        companyRepository.delete(fetchedCompany);
        return fetchedCompany;
    }
}
