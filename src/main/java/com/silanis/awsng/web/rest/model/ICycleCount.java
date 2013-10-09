package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ICycleCount {
    public ICycleCount setCount( Integer value);
    public Integer getCount();
    public ICycleCount setCycle( Cycle value);
    public Cycle getCycle();
    }