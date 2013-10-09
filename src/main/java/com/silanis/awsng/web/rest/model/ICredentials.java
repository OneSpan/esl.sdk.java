package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICredentials {
    public ICredentials setEmail( String value);
    public String getEmail();
    public ICredentials setNewPassword( String value);
    public String getNewPassword();
    public ICredentials setPassword( String value);
    public String getPassword();
    }