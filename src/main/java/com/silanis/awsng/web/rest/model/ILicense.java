package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ILicense {
    public ILicense setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public ILicense setPaidUntil( java.util.Date value);
    public java.util.Date getPaidUntil();
    public ILicense setPlan( Plan value);
    public Plan getPlan();
    public ILicense setStatus( LicenseStatus value);
    public LicenseStatus getStatus();
    public ILicense setTransactions( List<Transaction> value);
    public List<Transaction> getTransactions();
    }