package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    List<Company> companies = new ArrayList<>(Arrays.asList(
            new Company("alibaba", 200, new ArrayList<>(Arrays.asList(
                    new Employee(4, "alibaba1", 20, "male", 6000),
                    new Employee(11, "tengxun2", 19, "female", 7000),
                    new Employee(6, "alibaba3", 19, "male", 8000),
                    new Employee(13, "huawei", 60, "male", 4000),
                    new Employee(1, "Quentin", 18, "male", 10000),
                    new Employee(5, "goodboy", 70, "remale", 5000)
            ))),
            new Company("tx", 100, new ArrayList<>(Arrays.asList(
                    new Employee(4, "tx", 20, "male", 6000),
                    new Employee(5, "gd", 70, "remale", 5000)
            )))
    ));

    @GetMapping
    public List<Company> getAll(@RequestParam(value = "page", required = false) Integer page
            , @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        if (page != null && pageSize != null)
            return companies.subList((page - 1) * pageSize, pageSize > companies.size() ? companies.size() : (page - 1) * pageSize + pageSize);
        return companies;
    }

    @GetMapping("/{companyIndex}")
    public Company getCompanyByCompanyID(@PathVariable Integer companyIndex) {
        return companies.get(companyIndex);
    }

    @GetMapping("/{companyIndex}/employees")
    public List<Employee> getAllEmployeesByCompanyID(@PathVariable Integer companyIndex) {
        return companies.get(companyIndex).getEmployees();
    }

    @PostMapping
    public Boolean addCompany(@RequestBody Company company) {
        return companies.add(company);
    }

}
