package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.*;

import java.util.*;

public class AccountBuilder {

    private Company company;
    private java.util.Date created;
    private java.util.Date updated;
    private List<CustomField> customFields = new ArrayList<CustomField>();
    private List<License> licenses = new ArrayList<License>();
    private String logoUrl = "";
    private String owner = "";
    protected String name;
    private String id;
    private Map<String, Object> data;
    private AccountProviders providers = null;

    private AccountBuilder() {
        customFields = new ArrayList<CustomField>();
        licenses = new ArrayList<License>();
        data = new HashMap<String, Object>();
    }

    public static AccountBuilder newAccount() {
        return new AccountBuilder();
    }

    public AccountBuilder withName( String value ) {
        name = value;
        return this;
    }

    public AccountBuilder withOwner( String value ) {
        owner = value;
        return this;
    }

    public AccountBuilder withLogoUrl( String value ) {
        logoUrl = value;
        return this;
    }

    public AccountBuilder withId( String value ) {
        id = value;
        return this;
    }

    public AccountBuilder withCompany(Company value) {
        company = value;
        return this;
    }

    public AccountBuilder withCreated( Date value ) {
        created = value;
        return this;
    }

    public AccountBuilder withUpdated( Date value ) {
        updated = value;
        return this;
    }

    public AccountBuilder withCustomField( CustomField value ) {
        customFields.add(value);
        return this;
    }


    public AccountBuilder withLicense(License value) {
        licenses.add(value);
        return this;
    }

    public AccountBuilder withAccountProviders(AccountProviders value) {
        providers = value;
        return this;
    }

    public AccountBuilder withAccountProviders(List<Provider> documents, List<Provider> users)
    {
        providers = new AccountProviders();
        for (Provider provider : documents) {
            providers.addDocument(provider);
        }
        for (Provider provider : users) {
            providers.addUser(provider);
        }

        return this;
    }

    public AccountBuilder withData(Map<String, Object> value)
    {
        data = value;
        return this;
    }

    public Account build() {
        Account account = new Account();
        account.setCompany(company);
        account.setCreated(created);
        account.setUpdated(updated);
        for( CustomField field : customFields ) {
            account.addCustomField(field);
        }
        account.setData(data);
        account.setId(id);
        for (License license : licenses)
        {
            account.addLicense(license);
        }
        account.setLogoUrl(logoUrl);
        account.setName(name);
        account.setOwner(owner);
        account.setProviders(providers);
        return account;
    }

}
