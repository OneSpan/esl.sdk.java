package com.silanis.esl.api.model;
//

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