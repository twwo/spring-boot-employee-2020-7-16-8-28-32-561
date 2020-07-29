package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    List<Company> companies = new ArrayList<>(Arrays.asList(
            new Company(1, "alibaba", 200, new ArrayList<>(Arrays.asList(
                    new Employee(4, "alibaba1", 20, "male", 6000),
                    new Employee(11, "tengxun2", 19, "female", 7000),
                    new Employee(6, "alibaba3", 19, "male", 8000),
                    new Employee(13, "huawei", 60, "male", 4000),
                    new Employee(1, "Quentin", 18, "male", 10000),
                    new Employee(5, "goodboy", 70, "remale", 5000)
            ))),
            new Company(2, "tx", 100, new ArrayList<>(Arrays.asList(
                    new Employee(4, "tx", 20, "male", 6000),
                    new Employee(5, "gd", 70, "remale", 5000)
            )))
    ));

    @GetMapping
    public List<Company> getAll(@RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null)
            return companies.stream().skip((page - 1) * pageSize).limit(pageSize).collect(Collectors.toList());
        return companies;
    }

    @GetMapping("/{companyID}")
    public Company getCompanyByCompanyID(@PathVariable Integer companyID) {
        return companies.stream().filter(company -> company.getId() == companyID).findFirst().get();
    }

    @GetMapping("/{companyIndex}/employees")
    public List<Employee> getAllEmployeesByCompanyID(@PathVariable Integer companyIndex) {
        return companies.get(companyIndex).getEmployees();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company addCompany(@RequestBody Company company) {
        companies.add(company);
        return company;
    }

    @PutMapping("/{companyIndex}")
    public Company updateCompanyById(@RequestBody Company company, @PathVariable Integer companyIndex) {
        companies.get(companyIndex).setCompanyName(company.getCompanyName());
        companies.get(companyIndex).setEmployees(company.getEmployees());
        companies.get(companyIndex).setEmployeesNumber(company.getEmployeesNumber());
        return companies.get(companyIndex);
    }

    @DeleteMapping("/{companyIndex}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Company deleteCompanyById(@PathVariable Integer companyIndex) {
        return companies.remove(companyIndex.intValue());
    }

}
