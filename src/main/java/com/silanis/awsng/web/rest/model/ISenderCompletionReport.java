package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ISenderCompletionReport {
    public ISenderCompletionReport setPackages( List<PackageCompletionReport> value);
    public List<PackageCompletionReport> getPackages();
    public ISenderCompletionReport setSender( Sender value);
    public Sender getSender();
    }