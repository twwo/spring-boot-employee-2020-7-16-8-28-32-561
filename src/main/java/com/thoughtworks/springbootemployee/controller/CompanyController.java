package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final CompanyService service = new CompanyService(new CompanyRepository());

    @GetMapping
    public List<Company> getAll() {
        return service.getAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getAllByPage(Integer page, Integer pageSize) {
        return service.getCompaniesByPage(page, pageSize);
    }

    @GetMapping("/{companyID}")
    public Company getCompanyByCompanyID(@PathVariable Integer companyID) {
        return service.getCompanyById(companyID);
    }

    @GetMapping("/{companyID}/employees")
    public List<Employee> getAllEmployeesByCompanyID(@PathVariable Integer companyID) {
        return service.getEmployeesByCompanyId(companyID);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        return service.addCompany(company);
    }

    @PutMapping("/{companyID}")
    public Company updateCompanyById(@RequestBody Company company, @PathVariable Integer companyID) {
        return service.updateCompany(companyID, company);
    }

    @DeleteMapping("/{companyID}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Company deleteCompanyById(@PathVariable Integer companyID) {
        return service.deleteCompanyById(companyID);
    }

}
