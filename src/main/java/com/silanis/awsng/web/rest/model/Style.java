package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Style extends Model
      implements java.io.Serializable, IStyle
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_BACKGROUNDCOLOR = "backgroundColor";
    @JsonIgnore
    public static final String FIELD_COLOR = "color";
    
    // Empty Constructor
    public Style ( ) {}
    
    // Fields
    protected String _backgroundColor = null;
    protected String _color = null;
    
    // Accessors
        
    
    public Style setBackgroundColor( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._backgroundColor == value ) return this;
        this._backgroundColor = value;
        setDirty(FIELD_BACKGROUNDCOLOR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Style safeSetBackgroundColor( String value ){
        if ( value != null ) { this.setBackgroundColor( value ); }
        return this;
    }
    public String getBackgroundColor(){
        return _backgroundColor;
    }
    
        
    
    public Style setColor( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._color == value ) return this;
        this._color = value;
        setDirty(FIELD_COLOR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Style safeSetColor( String value ){
        if ( value != null ) { this.setColor( value ); }
        return this;
    }
    public String getColor(){
        return _color;
    }
    
    
}