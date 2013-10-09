package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IFieldValidation {
    public IFieldValidation setEnum( List<String> value);
    public List<String> getEnum();
    public IFieldValidation setErrorCode( Integer value);
    public Integer getErrorCode();
    public IFieldValidation setErrorMessage( String value);
    public String getErrorMessage();
    public IFieldValidation setMaxLength( Integer value);
    public Integer getMaxLength();
    public IFieldValidation setMinLength( Integer value);
    public Integer getMinLength();
    public IFieldValidation setPattern( String value);
    public String getPattern();
    public IFieldValidation setRequired( Boolean value);
    public Boolean getRequired();
    }