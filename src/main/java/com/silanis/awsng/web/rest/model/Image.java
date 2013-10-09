package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Image extends Model
      implements java.io.Serializable, IImage
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_LINK = "link";
    @JsonIgnore
    public static final String FIELD_SRC = "src";
    
    // Empty Constructor
    public Image ( ) {}
    
    // Fields
    protected String _link = "";
    protected String _src = "";
    
    // Accessors
        
    
    public Image setLink( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._link == value ) return this;
        this._link = value;
        setDirty(FIELD_LINK);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Image safeSetLink( String value ){
        if ( value != null ) { this.setLink( value ); }
        return this;
    }
    public String getLink(){
        return _link;
    }
    
        
    
    public Image setSrc( String value ){
        SchemaSanitizer.throwOnNull(FIELD_SRC,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._src == value ) return this;
        this._src = value;
        setDirty(FIELD_SRC);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Image safeSetSrc( String value ){
        if ( value != null ) { this.setSrc( value ); }
        return this;
    }
    public String getSrc(){
        return _src;
    }
    
    
}