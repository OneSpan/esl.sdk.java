package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICcExpiration {
    public ICcExpiration setMonth( Integer value);
    public Integer getMonth();
    public ICcExpiration setYear( Integer value);
    public Integer getYear();
    }