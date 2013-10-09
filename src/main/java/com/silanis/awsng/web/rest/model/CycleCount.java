package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CycleCount extends Model
      implements java.io.Serializable, ICycleCount
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COUNT = "count";
    @JsonIgnore
    public static final String FIELD_CYCLE = "cycle";
    
    // Empty Constructor
    public CycleCount ( ) {}
    
    // Fields
    protected Integer _count = 0;
    protected Cycle _cycle = Cycle.DAY;
    
    // Accessors
        
    
    public CycleCount setCount( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_COUNT,value);
        // TODO With proper compare
        // if ( this._count == value ) return this;
        this._count = value;
        setDirty(FIELD_COUNT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CycleCount safeSetCount( Integer value ){
        if ( value != null ) { this.setCount( value ); }
        return this;
    }
    public Integer getCount(){
        return _count;
    }
    
        
    
    public CycleCount setCycle( Cycle value ){
        SchemaSanitizer.throwOnNull(FIELD_CYCLE,value);
        // TODO With proper compare
        // if ( this._cycle == value ) return this;
        this._cycle = value;
        setDirty(FIELD_CYCLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CycleCount safeSetCycle( Cycle value ){
        if ( value != null ) { this.setCycle( value ); }
        return this;
    }
    public Cycle getCycle(){
        return _cycle;
    }
    
    
}