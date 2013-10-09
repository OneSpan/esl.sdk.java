package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IImage {
    public IImage setLink( String value);
    public String getLink();
    public IImage setSrc( String value);
    public String getSrc();
    }