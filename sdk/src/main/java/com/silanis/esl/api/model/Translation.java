package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Translation extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_DESCRIPTION = "description";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LANGUAGE = "language";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    
    // Empty Constructor
    public Translation ( ) {}
    
    // Fields
    protected String _description = "";
    protected String _language = "";
    
    // Accessors
        
    
    @Override
    public Translation setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Translation safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public Translation setDescription( String value ){
        SchemaSanitizer.throwOnNull(FIELD_DESCRIPTION,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._description == value ) return this;
        this._description = value;
        setDirty(FIELD_DESCRIPTION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Translation safeSetDescription( String value ){
        if ( value != null ) { this.setDescription( value ); }
        return this;
    }
    public String getDescription(){
        return _description;
    }
    
        
    
    @Override
    public Translation setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Translation safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public Translation setLanguage( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LANGUAGE,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._language == value ) return this;
        this._language = value;
        setDirty(FIELD_LANGUAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Translation safeSetLanguage( String value ){
        if ( value != null ) { this.setLanguage( value ); }
        return this;
    }
    public String getLanguage(){
        return _language;
    }
    
        
    
    @Override
    public Translation setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Translation safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
    
}