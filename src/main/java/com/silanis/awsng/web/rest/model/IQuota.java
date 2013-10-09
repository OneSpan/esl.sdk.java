package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IQuota {
    public IQuota setCycle( Cycle value);
    public Cycle getCycle();
    public IQuota setLimit( Integer value);
    public Integer getLimit();
    public IQuota setScope( Scope value);
    public Scope getScope();
    public IQuota setTarget( Target value);
    public Target getTarget();
    }