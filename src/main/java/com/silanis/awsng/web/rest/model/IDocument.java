package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IDocument extends IEntity{
    public IDocument setApprovals( List<Approval> value);
    public List<Approval> getApprovals();
    public IDocument setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IDocument setDescription( String value);
    public String getDescription();
    public IDocument setExternal( External value);
    public External getExternal();
    public IDocument setExtract( Boolean value);
    public Boolean getExtract();
    public IDocument setFields( List<Field> value);
    public List<Field> getFields();
    public IDocument setId( String value);
    public String getId();
    public IDocument setIndex( Integer value);
    public Integer getIndex();
    public IDocument setName( String value);
    public String getName();
    public IDocument setPages( List<Page> value);
    public List<Page> getPages();
    public IDocument setSize( Integer value);
    public Integer getSize();
    }