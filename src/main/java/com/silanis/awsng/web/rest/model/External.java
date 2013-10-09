package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class External extends Model
      implements java.io.Serializable, IExternal
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_PROVIDER = "provider";
    @JsonIgnore
    public static final String FIELD_PROVIDERNAME = "providerName";
    
    // Empty Constructor
    public External ( ) {}
    
    // Fields
    protected Map<String, Object> _data = null;
    protected String _id = "";
    protected String _provider = "";
    protected String _providerName = null;
    
    // Accessors
        
    
    public External setData( Map<String, Object> value ){
        // TODO With proper compare
        // if ( this._data == value ) return this;
        this._data = value;
        setDirty(FIELD_DATA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public External safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    public Map<String, Object> getData(){
        return _data;
    }
    
        
    
    public External setId( String value ){
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
    public External safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    public External setProvider( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PROVIDER,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._provider == value ) return this;
        this._provider = value;
        setDirty(FIELD_PROVIDER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public External safeSetProvider( String value ){
        if ( value != null ) { this.setProvider( value ); }
        return this;
    }
    public String getProvider(){
        return _provider;
    }
    
        
    
    public External setProviderName( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._providerName == value ) return this;
        this._providerName = value;
        setDirty(FIELD_PROVIDERNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public External safeSetProviderName( String value ){
        if ( value != null ) { this.setProviderName( value ); }
        return this;
    }
    public String getProviderName(){
        return _providerName;
    }
    
    
}