package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Page extends Box
      implements java.io.Serializable, IPage
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_HEIGHT = "height";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_INDEX = "index";
    @JsonIgnore
    public static final String FIELD_LEFT = "left";
    @JsonIgnore
    public static final String FIELD_TOP = "top";
    @JsonIgnore
    public static final String FIELD_VERSION = "version";
    @JsonIgnore
    public static final String FIELD_WIDTH = "width";
    
    // Empty Constructor
    public Page ( ) {}
    
    // Fields
    protected String _id = "";
    protected Integer _index = 0;
    protected Integer _version = 0;
    
    // Accessors
        
    
    @Override
    public Page setHeight( Double value ){
        // TODO: Figure how to do refinements of validation rules
        super.setHeight(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Page safeSetHeight( Double value ){
        if ( value != null ) { this.setHeight( value ); }
        return this;
    }
    
        
    
    public Page setId( String value ){
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
    public Page safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    public Page setIndex( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_INDEX,value);
        // TODO With proper compare
        // if ( this._index == value ) return this;
        this._index = value;
        setDirty(FIELD_INDEX);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Page safeSetIndex( Integer value ){
        if ( value != null ) { this.setIndex( value ); }
        return this;
    }
    public Integer getIndex(){
        return _index;
    }
    
        
    
    @Override
    public Page setLeft( Double value ){
        // TODO: Figure how to do refinements of validation rules
        super.setLeft(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Page safeSetLeft( Double value ){
        if ( value != null ) { this.setLeft( value ); }
        return this;
    }
    
        
    
    @Override
    public Page setTop( Double value ){
        // TODO: Figure how to do refinements of validation rules
        super.setTop(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Page safeSetTop( Double value ){
        if ( value != null ) { this.setTop( value ); }
        return this;
    }
    
        
    
    public Page setVersion( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_VERSION,value);
        // TODO With proper compare
        // if ( this._version == value ) return this;
        this._version = value;
        setDirty(FIELD_VERSION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Page safeSetVersion( Integer value ){
        if ( value != null ) { this.setVersion( value ); }
        return this;
    }
    public Integer getVersion(){
        return _version;
    }
    
        
    
    @Override
    public Page setWidth( Double value ){
        // TODO: Figure how to do refinements of validation rules
        super.setWidth(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Page safeSetWidth( Double value ){
        if ( value != null ) { this.setWidth( value ); }
        return this;
    }
    
    
}