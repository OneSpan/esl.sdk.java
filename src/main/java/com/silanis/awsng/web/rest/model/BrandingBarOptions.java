package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class BrandingBarOptions extends Model
      implements java.io.Serializable, IBrandingBarOptions
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_LOGO = "logo";
    
    // Empty Constructor
    public BrandingBarOptions ( ) {}
    
    // Fields
    protected Image _logo = null;
    
    // Accessors
        
    
    public BrandingBarOptions setLogo( Image value ){
        // TODO With proper compare
        // if ( this._logo == value ) return this;
        this._logo = value;
        setDirty(FIELD_LOGO);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BrandingBarOptions safeSetLogo( Image value ){
        if ( value != null ) { this.setLogo( value ); }
        return this;
    }
    public Image getLogo(){
        return _logo;
    }
    
    
}