package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class TextualSignatureStyle extends Model
      implements java.io.Serializable, ITextualSignatureStyle
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COLOR = "color";
    @JsonIgnore
    public static final String FIELD_FONT = "font";
    
    // Empty Constructor
    public TextualSignatureStyle ( ) {}
    
    // Fields
    protected String _color = null;
    protected String _font = null;
    
    // Accessors
        
    
    public TextualSignatureStyle setColor( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._color == value ) return this;
        this._color = value;
        setDirty(FIELD_COLOR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public TextualSignatureStyle safeSetColor( String value ){
        if ( value != null ) { this.setColor( value ); }
        return this;
    }
    public String getColor(){
        return _color;
    }
    
        
    
    public TextualSignatureStyle setFont( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._font == value ) return this;
        this._font = value;
        setDirty(FIELD_FONT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public TextualSignatureStyle safeSetFont( String value ){
        if ( value != null ) { this.setFont( value ); }
        return this;
    }
    public String getFont(){
        return _font;
    }
    
    
}