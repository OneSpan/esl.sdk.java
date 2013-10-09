package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ISession {
    public ISession setAccount( Account value);
    public Account getAccount();
    public ISession setFeatures( Features value);
    public Features getFeatures();
    public ISession setInPerson( Boolean value);
    public Boolean getInPerson();
    public ISession setPackages( List<String> value);
    public List<String> getPackages();
    public ISession setUser( User value);
    public User getUser();
    }