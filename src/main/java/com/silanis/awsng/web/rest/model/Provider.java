package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Provider extends Entity
      implements java.io.Serializable, IProvider
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_PROVIDES = "provides";
    
    // Empty Constructor
    public Provider ( ) {}
    
    // Fields
    protected String _provides;
    
    // Accessors
        
    
    @Override
    public Provider setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Provider safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public Provider setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Provider safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Provider setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Provider safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public Provider setProvides( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PROVIDES,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._provides == value ) return this;
        this._provides = value;
        setDirty(FIELD_PROVIDES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Provider safeSetProvides( String value ){
        if ( value != null ) { this.setProvides( value ); }
        return this;
    }
    public String getProvides(){
        return _provides;
    }
    
    
}