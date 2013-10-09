package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IGlobalActionsOptions {
    public IGlobalActionsOptions setConfirm( Boolean value);
    public Boolean getConfirm();
    public IGlobalActionsOptions setDownload( Boolean value);
    public Boolean getDownload();
    public IGlobalActionsOptions setSaveAsLayout( Boolean value);
    public Boolean getSaveAsLayout();
    }