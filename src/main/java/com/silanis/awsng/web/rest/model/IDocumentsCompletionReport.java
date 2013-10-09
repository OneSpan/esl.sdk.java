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
public interface IDocumentsCompletionReport {
    public IDocumentsCompletionReport setCompleted( Boolean value);
    public Boolean getCompleted();
    public IDocumentsCompletionReport setFirstSigned( java.util.Date value);
    public java.util.Date getFirstSigned();
    public IDocumentsCompletionReport setId( String value);
    public String getId();
    public IDocumentsCompletionReport setLastSigned( java.util.Date value);
    public java.util.Date getLastSigned();
    public IDocumentsCompletionReport setName( String value);
    public String getName();
    }