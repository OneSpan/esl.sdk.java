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
public interface IPackageCompletionReport {
    public IPackageCompletionReport setDocuments( List<DocumentsCompletionReport> value);
    public List<DocumentsCompletionReport> getDocuments();
    public IPackageCompletionReport setId( String value);
    public String getId();
    public IPackageCompletionReport setName( String value);
    public String getName();
    public IPackageCompletionReport setSigners( List<SignersCompletionReport> value);
    public List<SignersCompletionReport> getSigners();
    public IPackageCompletionReport setStatus( PackageStatus value);
    public PackageStatus getStatus();
    public IPackageCompletionReport setTrashed( Boolean value);
    public Boolean getTrashed();
    public IPackageCompletionReport setUpdated( java.util.Date value);
    public java.util.Date getUpdated();
    }