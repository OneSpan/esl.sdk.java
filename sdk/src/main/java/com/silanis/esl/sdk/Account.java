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


    public void setCompany(Company value){
       company = value;
    }

    public Company getCompany(){
        return company;
    }

    public java.util.Date getCreated(){ return created; }

    public void setCreated(java.util.Date value){ created = value; }

    public java.util.Date getUpdated() { return updated; }

    public void setUpdated(java.util.Date value) { updated = value; }

    public void setCustomFields(List<CustomField> value){ customFields = value; }

    public List<CustomField> getCustomFields(){ return customFields; }

    public void addCustomField(CustomField value){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        customFields.add(value);
    }

    public Map<String, Object> getData() { return data; }

    public void setData(Map<String, Object> value) { data = value; }

    public void setId(String value) { id = value; }

    public String getId(){ return id; }

    public void setName(String value) { name = value; }

    public String getName() { return name; }

    public void setLicenses(List<License> value) { licenses = value; }

    public List<License> getLicenses() { return licenses; }

    public void addLicense(License value) {
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        licenses.add(value);
    }

    public void setLogoUrl(String value) { logoUrl = value; }

    public String getLogoUrl() { return logoUrl; }
    
    public void setOwner(String value) { owner = value; }

    public String getOwner() { return owner; }

    public void setProviders(AccountProviders value) { providers = value; }

    public AccountProviders getProviders() { return providers; }

}