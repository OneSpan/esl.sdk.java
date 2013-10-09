package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Result<T> extends Model
      implements java.io.Serializable, IResult<T>
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COUNT = "count";
    @JsonIgnore
    public static final String FIELD_RESULTS = "results";
    
    // Empty Constructor
    public Result ( ) {}
    
    // Fields
    protected Integer _count = 0;
    protected List<T> _results = new ArrayList<T>();
    
    // Accessors
        
    
    public Result<T> setCount( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_COUNT,value);
        // TODO With proper compare
        // if ( this._count == value ) return this;
        this._count = value;
        setDirty(FIELD_COUNT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Result<T> safeSetCount( Integer value ){
        if ( value != null ) { this.setCount( value ); }
        return this;
    }
    public Integer getCount(){
        return _count;
    }
    
        
    
    public Result<T> setResults( List<T> value ){
        SchemaSanitizer.throwOnNull(FIELD_RESULTS,value);
        // TODO With proper compare
        // if ( this._results == value ) return this;
        this._results = value;
        setDirty(FIELD_RESULTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Result<T> safeSetResults( List<T> value ){
        if ( value != null ) { this.setResults( value ); }
        return this;
    }
    public List<T> getResults(){
        return _results;
    }
    // List adder
    public Result<T> addResult( T value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._results.add(value);
        setDirty(FIELD_RESULTS);
        return this;
    }
    
    
}