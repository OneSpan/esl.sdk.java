package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class TitleBarOptions extends Model
      implements java.io.Serializable, ITitleBarOptions
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_PROGRESSBAR = "progressBar";
    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    
    // Empty Constructor
    public TitleBarOptions ( ) {}
    
    // Fields
    protected Boolean _progressBar = true;
    protected Boolean _title = true;
    
    // Accessors
        
    
    public TitleBarOptions setProgressBar( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_PROGRESSBAR,value);
        // TODO With proper compare
        // if ( this._progressBar == value ) return this;
        this._progressBar = value;
        setDirty(FIELD_PROGRESSBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public TitleBarOptions safeSetProgressBar( Boolean value ){
        if ( value != null ) { this.setProgressBar( value ); }
        return this;
    }
    public Boolean getProgressBar(){
        return _progressBar;
    }
    @JsonIgnore
    public boolean evalProgressBar(){
        return _progressBar == null ? false : _progressBar.booleanValue();
    }
    
        
    
    public TitleBarOptions setTitle( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_TITLE,value);
        // TODO With proper compare
        // if ( this._title == value ) return this;
        this._title = value;
        setDirty(FIELD_TITLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public TitleBarOptions safeSetTitle( Boolean value ){
        if ( value != null ) { this.setTitle( value ); }
        return this;
    }
    public Boolean getTitle(){
        return _title;
    }
    @JsonIgnore
    public boolean evalTitle(){
        return _title == null ? false : _title.booleanValue();
    }
    
    
}