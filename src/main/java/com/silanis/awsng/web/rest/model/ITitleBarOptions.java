package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface ITitleBarOptions {
    public ITitleBarOptions setProgressBar( Boolean value);
    public Boolean getProgressBar();
    public ITitleBarOptions setTitle( Boolean value);
    public Boolean getTitle();
    }