package com.silanis.esl.api.model;
//
import java.util.List;

public interface IAccountProviders {
    public IAccountProviders setDocuments( List<Provider> value);
    public List<Provider> getDocuments();
    public IAccountProviders setUsers( List<Provider> value);
    public List<Provider> getUsers();
    }