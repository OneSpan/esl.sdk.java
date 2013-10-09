package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IPage extends IBox{
    public IPage setHeight( Double value);
    public Double getHeight();
    public IPage setId( String value);
    public String getId();
    public IPage setIndex( Integer value);
    public Integer getIndex();
    public IPage setLeft( Double value);
    public Double getLeft();
    public IPage setTop( Double value);
    public Double getTop();
    public IPage setVersion( Integer value);
    public Integer getVersion();
    public IPage setWidth( Double value);
    public Double getWidth();
    }