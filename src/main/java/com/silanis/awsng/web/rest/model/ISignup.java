package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ISignup extends ICredentials{
    public ISignup setBill( Bill value);
    public Bill getBill();
    public ISignup setEmail( String value);
    public String getEmail();
    public ISignup setEmailVerified( Boolean value);
    public Boolean getEmailVerified();
    public ISignup setFirstName( String value);
    public String getFirstName();
    public ISignup setLastName( String value);
    public String getLastName();
    public ISignup setName( String value);
    public String getName();
    public ISignup setNewPassword( String value);
    public String getNewPassword();
    public ISignup setPassword( String value);
    public String getPassword();
    public ISignup setPhone( String value);
    public String getPhone();
    }