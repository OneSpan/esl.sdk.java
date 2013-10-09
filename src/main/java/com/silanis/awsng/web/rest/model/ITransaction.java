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
public interface ITransaction {
    public ITransaction setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public ITransaction setCreditCard( CreditCard value);
    public CreditCard getCreditCard();
    public ITransaction setPrice( Price value);
    public Price getPrice();
    }