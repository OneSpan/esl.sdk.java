package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IHeaderOptions {
    public IHeaderOptions setBreadcrumbs( Boolean value);
    public Boolean getBreadcrumbs();
    public IHeaderOptions setFeedback( Boolean value);
    public Boolean getFeedback();
    public IHeaderOptions setGlobalActions( GlobalActionsOptions value);
    public GlobalActionsOptions getGlobalActions();
    public IHeaderOptions setGlobalNavigation( Boolean value);
    public Boolean getGlobalNavigation();
    public IHeaderOptions setSessionBar( Boolean value);
    public Boolean getSessionBar();
    public IHeaderOptions setTitleBar( TitleBarOptions value);
    public TitleBarOptions getTitleBar();
    }