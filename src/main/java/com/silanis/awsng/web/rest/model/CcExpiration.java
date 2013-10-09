package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CcExpiration extends Model
      implements java.io.Serializable, ICcExpiration
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_MONTH = "month";
    @JsonIgnore
    public static final String FIELD_YEAR = "year";
    
    // Empty Constructor
    public CcExpiration ( ) {}
    
    // Fields
    protected Integer _month = 1;
    protected Integer _year = 2012;
    
    // Accessors
        
    
    public CcExpiration setMonth( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_MONTH,value);
        // TODO With proper compare
        // if ( this._month == value ) return this;
        this._month = value;
        setDirty(FIELD_MONTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CcExpiration safeSetMonth( Integer value ){
        if ( value != null ) { this.setMonth( value ); }
        return this;
    }
    public Integer getMonth(){
        return _month;
    }
    
        
    
    public CcExpiration setYear( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_YEAR,value);
        // TODO With proper compare
        // if ( this._year == value ) return this;
        this._year = value;
        setDirty(FIELD_YEAR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CcExpiration safeSetYear( Integer value ){
        if ( value != null ) { this.setYear( value ); }
        return this;
    }
    public Integer getYear(){
        return _year;
    }
    
    
}