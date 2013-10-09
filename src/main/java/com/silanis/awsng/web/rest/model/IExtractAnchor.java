package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IExtractAnchor {
    public IExtractAnchor setAnchorPoint( String value);
    public String getAnchorPoint();
    public IExtractAnchor setCharacterIndex( Integer value);
    public Integer getCharacterIndex();
    public IExtractAnchor setHeight( Integer value);
    public Integer getHeight();
    public IExtractAnchor setIndex( Integer value);
    public Integer getIndex();
    public IExtractAnchor setLeftOffset( Integer value);
    public Integer getLeftOffset();
    public IExtractAnchor setText( String value);
    public String getText();
    public IExtractAnchor setTopOffset( Integer value);
    public Integer getTopOffset();
    public IExtractAnchor setWidth( Integer value);
    public Integer getWidth();
    }