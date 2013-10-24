package com.silanis.esl.api.model;
//
import java.util.List;

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