package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Error extends Model
      implements java.io.Serializable, IError
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CODE = "code";
    @JsonIgnore
    public static final String FIELD_ENTITY = "entity";
    @JsonIgnore
    public static final String FIELD_MESSAGE = "message";
    @JsonIgnore
    public static final String FIELD_MESSAGEKEY = "messageKey";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_TECHNICAL = "technical";
    
    // Empty Constructor
    public Error ( ) {}
    
    // Fields
    protected Integer _code = null;
    protected Entity _entity = null;
    protected String _message = "";
    protected String _messageKey = "";
    protected String _name = "";
    protected String _technical = null;
    
    // Accessors
        
    
    public Error setCode( Integer value ){
        // TODO With proper compare
        // if ( this._code == value ) return this;
        this._code = value;
        setDirty(FIELD_CODE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Error safeSetCode( Integer value ){
        if ( value != null ) { this.setCode( value ); }
        return this;
    }
    public Integer getCode(){
        return _code;
    }
    
        
    
    public Error setEntity( Entity value ){
        // TODO With proper compare
        // if ( this._entity == value ) return this;
        this._entity = value;
        setDirty(FIELD_ENTITY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Error safeSetEntity( Entity value ){
        if ( value != null ) { this.setEntity( value ); }
        return this;
    }
    public Entity getEntity(){
        return _entity;
    }
    
        
    
    public Error setMessage( String value ){
        SchemaSanitizer.throwOnNull(FIELD_MESSAGE,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._message == value ) return this;
        this._message = value;
        setDirty(FIELD_MESSAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Error safeSetMessage( String value ){
        if ( value != null ) { this.setMessage( value ); }
        return this;
    }
    public String getMessage(){
        return _message;
    }
    
        
    
    public Error setMessageKey( String value ){
        SchemaSanitizer.throwOnNull(FIELD_MESSAGEKEY,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._messageKey == value ) return this;
        this._messageKey = value;
        setDirty(FIELD_MESSAGEKEY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Error safeSetMessageKey( String value ){
        if ( value != null ) { this.setMessageKey( value ); }
        return this;
    }
    public String getMessageKey(){
        return _messageKey;
    }
    
        
    
    public Error setName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_NAME,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._name == value ) return this;
        this._name = value;
        setDirty(FIELD_NAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Error safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
        
    
    public Error setTechnical( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._technical == value ) return this;
        this._technical = value;
        setDirty(FIELD_TECHNICAL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Error safeSetTechnical( String value ){
        if ( value != null ) { this.setTechnical( value ); }
        return this;
    }
    public String getTechnical(){
        return _technical;
    }
    
    
}