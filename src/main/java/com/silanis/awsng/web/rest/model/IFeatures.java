package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IFeatures {
    public IFeatures setAttachments( Boolean value);
    public Boolean getAttachments();
    public IFeatures setFasttrack( Boolean value);
    public Boolean getFasttrack();
    public IFeatures setGroups( Boolean value);
    public Boolean getGroups();
    }