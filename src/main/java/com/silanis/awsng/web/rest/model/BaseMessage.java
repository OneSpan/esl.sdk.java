package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class BaseMessage extends Model
      implements java.io.Serializable, IBaseMessage
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CONTENT = "content";
    
    // Empty Constructor
    public BaseMessage ( ) {}
    
    // Fields
    protected String _content = "";
    
    // Accessors
        
    
    public BaseMessage setContent( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CONTENT,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._content == value ) return this;
        this._content = value;
        setDirty(FIELD_CONTENT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public BaseMessage safeSetContent( String value ){
        if ( value != null ) { this.setContent( value ); }
        return this;
    }
    public String getContent(){
        return _content;
    }
    
    
}