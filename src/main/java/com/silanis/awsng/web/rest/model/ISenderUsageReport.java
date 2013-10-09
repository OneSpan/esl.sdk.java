package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ISenderUsageReport {
    public ISenderUsageReport setPackages( Map<String, Object> value);
    public Map<String, Object> getPackages();
    public ISenderUsageReport setSender( Sender value);
    public Sender getSender();
    }