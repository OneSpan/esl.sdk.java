package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class HeaderOptions extends Model
      implements java.io.Serializable, IHeaderOptions
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_BREADCRUMBS = "breadcrumbs";
    @JsonIgnore
    public static final String FIELD_FEEDBACK = "feedback";
    @JsonIgnore
    public static final String FIELD_GLOBALACTIONS = "globalActions";
    @JsonIgnore
    public static final String FIELD_GLOBALNAVIGATION = "globalNavigation";
    @JsonIgnore
    public static final String FIELD_SESSIONBAR = "sessionBar";
    @JsonIgnore
    public static final String FIELD_TITLEBAR = "titleBar";
    
    // Empty Constructor
    public HeaderOptions ( ) {}
    
    // Fields
    protected Boolean _breadcrumbs = true;
    protected Boolean _feedback = true;
    protected GlobalActionsOptions _globalActions = null;
    protected Boolean _globalNavigation = true;
    protected Boolean _sessionBar = true;
    protected TitleBarOptions _titleBar = null;
    
    // Accessors
        
    
    public HeaderOptions setBreadcrumbs( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_BREADCRUMBS,value);
        // TODO With proper compare
        // if ( this._breadcrumbs == value ) return this;
        this._breadcrumbs = value;
        setDirty(FIELD_BREADCRUMBS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public HeaderOptions safeSetBreadcrumbs( Boolean value ){
        if ( value != null ) { this.setBreadcrumbs( value ); }
        return this;
    }
    public Boolean getBreadcrumbs(){
        return _breadcrumbs;
    }
    @JsonIgnore
    public boolean evalBreadcrumbs(){
        return _breadcrumbs == null ? false : _breadcrumbs.booleanValue();
    }
    
        
    
    public HeaderOptions setFeedback( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_FEEDBACK,value);
        // TODO With proper compare
        // if ( this._feedback == value ) return this;
        this._feedback = value;
        setDirty(FIELD_FEEDBACK);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public HeaderOptions safeSetFeedback( Boolean value ){
        if ( value != null ) { this.setFeedback( value ); }
        return this;
    }
    public Boolean getFeedback(){
        return _feedback;
    }
    @JsonIgnore
    public boolean evalFeedback(){
        return _feedback == null ? false : _feedback.booleanValue();
    }
    
        
    
    public HeaderOptions setGlobalActions( GlobalActionsOptions value ){
        // TODO With proper compare
        // if ( this._globalActions == value ) return this;
        this._globalActions = value;
        setDirty(FIELD_GLOBALACTIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public HeaderOptions safeSetGlobalActions( GlobalActionsOptions value ){
        if ( value != null ) { this.setGlobalActions( value ); }
        return this;
    }
    public GlobalActionsOptions getGlobalActions(){
        return _globalActions;
    }
    
        
    
    public HeaderOptions setGlobalNavigation( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_GLOBALNAVIGATION,value);
        // TODO With proper compare
        // if ( this._globalNavigation == value ) return this;
        this._globalNavigation = value;
        setDirty(FIELD_GLOBALNAVIGATION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public HeaderOptions safeSetGlobalNavigation( Boolean value ){
        if ( value != null ) { this.setGlobalNavigation( value ); }
        return this;
    }
    public Boolean getGlobalNavigation(){
        return _globalNavigation;
    }
    @JsonIgnore
    public boolean evalGlobalNavigation(){
        return _globalNavigation == null ? false : _globalNavigation.booleanValue();
    }
    
        
    
    public HeaderOptions setSessionBar( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SESSIONBAR,value);
        // TODO With proper compare
        // if ( this._sessionBar == value ) return this;
        this._sessionBar = value;
        setDirty(FIELD_SESSIONBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public HeaderOptions safeSetSessionBar( Boolean value ){
        if ( value != null ) { this.setSessionBar( value ); }
        return this;
    }
    public Boolean getSessionBar(){
        return _sessionBar;
    }
    @JsonIgnore
    public boolean evalSessionBar(){
        return _sessionBar == null ? false : _sessionBar.booleanValue();
    }
    
        
    
    public HeaderOptions setTitleBar( TitleBarOptions value ){
        // TODO With proper compare
        // if ( this._titleBar == value ) return this;
        this._titleBar = value;
        setDirty(FIELD_TITLEBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public HeaderOptions safeSetTitleBar( TitleBarOptions value ){
        if ( value != null ) { this.setTitleBar( value ); }
        return this;
    }
    public TitleBarOptions getTitleBar(){
        return _titleBar;
    }
    
    
}