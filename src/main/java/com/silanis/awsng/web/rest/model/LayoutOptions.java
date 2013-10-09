package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class LayoutOptions extends Model
      implements java.io.Serializable, ILayoutOptions
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_BRANDINGBAR = "brandingBar";
    @JsonIgnore
    public static final String FIELD_FOOTER = "footer";
    @JsonIgnore
    public static final String FIELD_HEADER = "header";
    @JsonIgnore
    public static final String FIELD_IFRAME = "iframe";
    @JsonIgnore
    public static final String FIELD_NAVIGATOR = "navigator";
    
    // Empty Constructor
    public LayoutOptions ( ) {}
    
    // Fields
    protected BrandingBarOptions _brandingBar = null;
    protected FooterOptions _footer = null;
    protected HeaderOptions _header = null;
    protected Boolean _iframe = false;
    protected Boolean _navigator = true;
    
    // Accessors
        
    
    public LayoutOptions setBrandingBar( BrandingBarOptions value ){
        // TODO With proper compare
        // if ( this._brandingBar == value ) return this;
        this._brandingBar = value;
        setDirty(FIELD_BRANDINGBAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutOptions safeSetBrandingBar( BrandingBarOptions value ){
        if ( value != null ) { this.setBrandingBar( value ); }
        return this;
    }
    public BrandingBarOptions getBrandingBar(){
        return _brandingBar;
    }
    
        
    
    public LayoutOptions setFooter( FooterOptions value ){
        // TODO With proper compare
        // if ( this._footer == value ) return this;
        this._footer = value;
        setDirty(FIELD_FOOTER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutOptions safeSetFooter( FooterOptions value ){
        if ( value != null ) { this.setFooter( value ); }
        return this;
    }
    public FooterOptions getFooter(){
        return _footer;
    }
    
        
    
    public LayoutOptions setHeader( HeaderOptions value ){
        // TODO With proper compare
        // if ( this._header == value ) return this;
        this._header = value;
        setDirty(FIELD_HEADER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutOptions safeSetHeader( HeaderOptions value ){
        if ( value != null ) { this.setHeader( value ); }
        return this;
    }
    public HeaderOptions getHeader(){
        return _header;
    }
    
        
    
    public LayoutOptions setIframe( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_IFRAME,value);
        // TODO With proper compare
        // if ( this._iframe == value ) return this;
        this._iframe = value;
        setDirty(FIELD_IFRAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutOptions safeSetIframe( Boolean value ){
        if ( value != null ) { this.setIframe( value ); }
        return this;
    }
    public Boolean getIframe(){
        return _iframe;
    }
    @JsonIgnore
    public boolean evalIframe(){
        return _iframe == null ? false : _iframe.booleanValue();
    }
    
        
    
    public LayoutOptions setNavigator( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_NAVIGATOR,value);
        // TODO With proper compare
        // if ( this._navigator == value ) return this;
        this._navigator = value;
        setDirty(FIELD_NAVIGATOR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public LayoutOptions safeSetNavigator( Boolean value ){
        if ( value != null ) { this.setNavigator( value ); }
        return this;
    }
    public Boolean getNavigator(){
        return _navigator;
    }
    @JsonIgnore
    public boolean evalNavigator(){
        return _navigator == null ? false : _navigator.booleanValue();
    }
    
    
}