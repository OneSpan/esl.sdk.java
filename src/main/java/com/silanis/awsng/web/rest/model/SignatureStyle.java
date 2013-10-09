package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SignatureStyle extends Model
      implements java.io.Serializable, ISignatureStyle
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_HANDDRAWN = "handdrawn";
    @JsonIgnore
    public static final String FIELD_TEXTUAL = "textual";
    
    // Empty Constructor
    public SignatureStyle ( ) {}
    
    // Fields
    protected String _handdrawn = "";
    protected TextualSignatureStyle _textual = null;
    
    // Accessors
        
    
    public SignatureStyle setHanddrawn( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._handdrawn == value ) return this;
        this._handdrawn = value;
        setDirty(FIELD_HANDDRAWN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignatureStyle safeSetHanddrawn( String value ){
        if ( value != null ) { this.setHanddrawn( value ); }
        return this;
    }
    public String getHanddrawn(){
        return _handdrawn;
    }
    
        
    
    public SignatureStyle setTextual( TextualSignatureStyle value ){
        // TODO With proper compare
        // if ( this._textual == value ) return this;
        this._textual = value;
        setDirty(FIELD_TEXTUAL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignatureStyle safeSetTextual( TextualSignatureStyle value ){
        if ( value != null ) { this.setTextual( value ); }
        return this;
    }
    public TextualSignatureStyle getTextual(){
        return _textual;
    }
    
    
}