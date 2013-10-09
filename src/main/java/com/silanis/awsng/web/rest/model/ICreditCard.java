package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICreditCard {
    public ICreditCard setCvv( String value);
    public String getCvv();
    public ICreditCard setExpiration( CcExpiration value);
    public CcExpiration getExpiration();
    public ICreditCard setName( String value);
    public String getName();
    public ICreditCard setNumber( String value);
    public String getNumber();
    public ICreditCard setType( String value);
    public String getType();
    }