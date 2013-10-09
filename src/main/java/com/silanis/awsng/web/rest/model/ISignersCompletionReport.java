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
public interface ISignersCompletionReport {
    public ISignersCompletionReport setCompleted( Boolean value);
    public Boolean getCompleted();
    public ISignersCompletionReport setEmail( String value);
    public String getEmail();
    public ISignersCompletionReport setFirstName( String value);
    public String getFirstName();
    public ISignersCompletionReport setFirstSigned( java.util.Date value);
    public java.util.Date getFirstSigned();
    public ISignersCompletionReport setId( String value);
    public String getId();
    public ISignersCompletionReport setLastName( String value);
    public String getLastName();
    public ISignersCompletionReport setLastSigned( java.util.Date value);
    public java.util.Date getLastSigned();
    }