package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IAccountProviders {
    public IAccountProviders setDocuments( List<Provider> value);
    public List<Provider> getDocuments();
    public IAccountProviders setUsers( List<Provider> value);
    public List<Provider> getUsers();
    }