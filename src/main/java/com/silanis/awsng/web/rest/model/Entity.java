package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Entity extends Model
      implements java.io.Serializable, IEntity
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    
    // Empty Constructor
    public Entity ( ) {}
    
    // Fields
    protected Map<String, Object> _data = null;
    protected String _id = "";
    protected String _name = "";
    
    // Accessors
        
    
    public Entity setData( Map<String, Object> value ){
        // TODO With proper compare
        // if ( this._data == value ) return this;
        this._data = value;
        setDirty(FIELD_DATA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Entity safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    public Map<String, Object> getData(){
        return _data;
    }
    
        
    
    public Entity setId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._id == value ) return this;
        this._id = value;
        setDirty(FIELD_ID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Entity safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    public Entity setName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_NAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._name == value ) return this;
        this._name = value;
        setDirty(FIELD_NAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Entity safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
    
}