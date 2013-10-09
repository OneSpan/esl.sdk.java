package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ViewSettings extends Settings
      implements java.io.Serializable, IViewSettings
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_LAYOUT = "layout";
    @JsonIgnore
    public static final String FIELD_STYLE = "style";
    
    // Empty Constructor
    public ViewSettings ( ) {}
    
    // Fields
    protected LayoutOptions _layout = null;
    protected LayoutStyle _style = null;
    
    // Accessors
        
    
    public ViewSettings setLayout( LayoutOptions value ){
        // TODO With proper compare
        // if ( this._layout == value ) return this;
        this._layout = value;
        setDirty(FIELD_LAYOUT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ViewSettings safeSetLayout( LayoutOptions value ){
        if ( value != null ) { this.setLayout( value ); }
        return this;
    }
    public LayoutOptions getLayout(){
        return _layout;
    }
    
        
    
    public ViewSettings setStyle( LayoutStyle value ){
        // TODO With proper compare
        // if ( this._style == value ) return this;
        this._style = value;
        setDirty(FIELD_STYLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ViewSettings safeSetStyle( LayoutStyle value ){
        if ( value != null ) { this.setStyle( value ); }
        return this;
    }
    public LayoutStyle getStyle(){
        return _style;
    }
    
    
}