package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IError {
    public IError setCode( Integer value);
    public Integer getCode();
    public IError setEntity( Entity value);
    public Entity getEntity();
    public IError setMessage( String value);
    public String getMessage();
    public IError setMessageKey( String value);
    public String getMessageKey();
    public IError setName( String value);
    public String getName();
    public IError setTechnical( String value);
    public String getTechnical();
    }