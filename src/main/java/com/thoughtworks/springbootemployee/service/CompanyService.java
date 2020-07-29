package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.model.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;

import java.util.List;

public class CompanyService {
    public CompanyService(CompanyRepository companyRepository) {
    }

    public List<Company> getAll() {
        return null;
    }
}
