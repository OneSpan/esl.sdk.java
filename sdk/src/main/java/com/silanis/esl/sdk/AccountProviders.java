package com.silanis.esl.sdk;


import java.util.ArrayList;
import java.util.List;

public class AccountProviders {

    private List<Provider> documents = new ArrayList<Provider>();
    private List<Provider> users = new ArrayList<Provider>();

    public void setDocuments(List<Provider> providers) {
        documents = providers;
    }

    public List<Provider> getDocuments() {
        return documents;
    }

    public void addDocument(Provider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        documents.add(provider);
    }

    public void setUsers(List<Provider> providers) {
        users = providers;
    }

    public List<Provider> getUsers() {
        return users;
    }

    public void addUser(Provider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Argument cannot be null");
        }
        users.add(provider);
    }


}