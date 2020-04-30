package com.silanis.esl.sdk;


import java.util.ArrayList;
import java.util.List;

public class AccountProviders {

    private List<Provider> documents = new ArrayList<Provider>();
    private List<Provider> users = new ArrayList<Provider>();

    public void setDocuments(List<Provider> value) { documents = value; }

    public List<Provider> getDocuments() { return documents; }

    public void addDocument(Provider value) {
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        documents.add(value);
    }

    public void setUsers(List<Provider> value) { users = value; }

    public List<Provider> getUsers() { return users; }

    public void addUser(Provider value) {
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        users.add(value);
    }
    
    
}