package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.Address;
import com.silanis.esl.sdk.Company;

import java.util.Map;


public class CompanyBuilder {
    private Address address;
    private String name;
    private String id;
    private Map<String, Object> data;

    private CompanyBuilder() {}

    public static CompanyBuilder newCompany() {
        return new CompanyBuilder();
    }

    public CompanyBuilder withAddress(Address value) {
        address = value;
        return this;
    }

    public CompanyBuilder withId(String value ) {
        id = value;
        return this;
    }

    public CompanyBuilder withName(String value) {
        name = value;
        return this;
    }

    public CompanyBuilder withData(Map<String, Object> value) {
        data = value;
        return this;
    }

    public Company build() {
        Company company = new Company();
        company.setData(data);
        company.setName(name);
        company.setId(id);
        company.setAddress(address);
        return company;
    }
}
