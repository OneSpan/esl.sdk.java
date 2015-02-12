package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CycleCount extends Model
      implements java.io.Serializable
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
    protected String _cycle = "DAY";
    
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
    
        
    
    public CycleCount setCycle( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CYCLE,value);
        // TODO With proper compare
        // if ( this._cycle == value ) return this;
        this._cycle = value;
        setDirty(FIELD_CYCLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CycleCount safeSetCycle( String value ){
        if ( value != null ) { this.setCycle( value ); }
        return this;
    }
    public String getCycle(){
        return _cycle;
    }
    
    
}