package com.silanis.esl.api.model;
//
import java.util.List;

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