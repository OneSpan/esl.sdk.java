package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Account {

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


    public void setCompany(Company company) {
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public java.util.Date getCreated() {
        return created;
    }

    public void setCreated(java.util.Date created) {
        this.created = created;
    }

    public java.util.Date getUpdated() {
        return updated;
    }

    public void setUpdated(java.util.Date updated) {
        this.updated = updated;
    }

    public void setCustomFields(List<CustomField> customFields) {
        this.customFields = customFields;
    }

    public List<CustomField> getCustomFields() {
        return customFields;
    }

    public void addCustomField(CustomField customField) {
        if (customField == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        customFields.add(customField);
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void addLicense(License license) {
        if (license == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        licenses.add(license);
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public void setProviders(AccountProviders providers) {
        this.providers = providers;
    }

    public AccountProviders getProviders() {
        return providers;
    }

}