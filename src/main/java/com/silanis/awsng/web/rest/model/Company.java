package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Company extends Entity
      implements java.io.Serializable, ICompany
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ADDRESS = "address";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    
    // Empty Constructor
    public Company ( ) {}
    
    // Fields
    protected Address _address = null;
    
    // Accessors
        
    
    public Company setAddress( Address value ){
        // TODO With proper compare
        // if ( this._address == value ) return this;
        this._address = value;
        setDirty(FIELD_ADDRESS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Company safeSetAddress( Address value ){
        if ( value != null ) { this.setAddress( value ); }
        return this;
    }
    public Address getAddress(){
        return _address;
    }
    
        
    
    @Override
    public Company setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Company safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public Company setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Company safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Company setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Company safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
    
}