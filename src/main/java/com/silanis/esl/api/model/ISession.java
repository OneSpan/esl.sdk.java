package com.silanis.esl.api.model;
//
import java.util.List;

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