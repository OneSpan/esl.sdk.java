package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ILink {
    public ILink setHref( String value);
    public String getHref();
    public ILink setText( String value);
    public String getText();
    public ILink setTitle( String value);
    public String getTitle();
    }