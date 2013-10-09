package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IBill {
    public IBill setAddress( Address value);
    public Address getAddress();
    public IBill setCreditCard( CreditCard value);
    public CreditCard getCreditCard();
    public IBill setPhone( String value);
    public String getPhone();
    public IBill setPlan( String value);
    public String getPlan();
    public IBill setSenderQuantity( Integer value);
    public Integer getSenderQuantity();
    }