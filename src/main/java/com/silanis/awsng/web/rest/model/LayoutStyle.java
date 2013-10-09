package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class LayoutStyle extends Model
      implements java.io.Serializable, ILayoutStyle
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_BRANDINGBAR = "brandingBar";
    @JsonIgnore
    public static final String FIELD_DIALOG = "dialog";
    @JsonIgnore
    public static final String FIELD_TITLEBAR = "titleBar";
    @JsonIgnore
    public static final String FIELD_TOOLBAR = "toolbar";
    
    // Empty Constructor
    public LayoutStyle ( ) {}
    
    // Fields
    protected Image _brandingBar = null;
    protected Style _dialog = null;
    protected Style _titleBar = null;
    protected Style _toolbar = null;
    
    // Accessors
        
    
    public LayoutStyle setBrandingBar( Image value ){
        // TODO With proper compare
        // if ( this._brandingBar == value ) return this;
        this._brandingBar = value;
        setDirty(FIELD_BRANDINGBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutStyle safeSetBrandingBar( Image value ){
        if ( value != null ) { this.setBrandingBar( value ); }
        return this;
    }
    public Image getBrandingBar(){
        return _brandingBar;
    }
    
        
    
    public LayoutStyle setDialog( Style value ){
        // TODO With proper compare
        // if ( this._dialog == value ) return this;
        this._dialog = value;
        setDirty(FIELD_DIALOG);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutStyle safeSetDialog( Style value ){
        if ( value != null ) { this.setDialog( value ); }
        return this;
    }
    public Style getDialog(){
        return _dialog;
    }
    
        
    
    public LayoutStyle setTitleBar( Style value ){
        // TODO With proper compare
        // if ( this._titleBar == value ) return this;
        this._titleBar = value;
        setDirty(FIELD_TITLEBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutStyle safeSetTitleBar( Style value ){
        if ( value != null ) { this.setTitleBar( value ); }
        return this;
    }
    public Style getTitleBar(){
        return _titleBar;
    }
    
        
    
    public LayoutStyle setToolbar( Style value ){
        // TODO With proper compare
        // if ( this._toolbar == value ) return this;
        this._toolbar = value;
        setDirty(FIELD_TOOLBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutStyle safeSetToolbar( Style value ){
        if ( value != null ) { this.setToolbar( value ); }
        return this;
    }
    public Style getToolbar(){
        return _toolbar;
    }
    
    
}