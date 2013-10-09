package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Quota extends Model
      implements java.io.Serializable, IQuota
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CYCLE = "cycle";
    @JsonIgnore
    public static final String FIELD_LIMIT = "limit";
    @JsonIgnore
    public static final String FIELD_SCOPE = "scope";
    @JsonIgnore
    public static final String FIELD_TARGET = "target";
    
    // Empty Constructor
    public Quota ( ) {}
    
    // Fields
    protected Cycle _cycle = null;
    protected Integer _limit = 0;
    protected Scope _scope = Scope.SENDER;
    protected Target _target = Target.SIGNER;
    
    // Accessors
        
    
    public Quota setCycle( Cycle value ){
        // TODO With proper compare
        // if ( this._cycle == value ) return this;
        this._cycle = value;
        setDirty(FIELD_CYCLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetCycle( Cycle value ){
        if ( value != null ) { this.setCycle( value ); }
        return this;
    }
    public Cycle getCycle(){
        return _cycle;
    }
    
        
    
    public Quota setLimit( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_LIMIT,value);
        // TODO With proper compare
        // if ( this._limit == value ) return this;
        this._limit = value;
        setDirty(FIELD_LIMIT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetLimit( Integer value ){
        if ( value != null ) { this.setLimit( value ); }
        return this;
    }
    public Integer getLimit(){
        return _limit;
    }
    
        
    
    public Quota setScope( Scope value ){
        // TODO With proper compare
        // if ( this._scope == value ) return this;
        this._scope = value;
        setDirty(FIELD_SCOPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetScope( Scope value ){
        if ( value != null ) { this.setScope( value ); }
        return this;
    }
    public Scope getScope(){
        return _scope;
    }
    
        
    
    public Quota setTarget( Target value ){
        SchemaSanitizer.throwOnNull(FIELD_TARGET,value);
        // TODO With proper compare
        // if ( this._target == value ) return this;
        this._target = value;
        setDirty(FIELD_TARGET);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetTarget( Target value ){
        if ( value != null ) { this.setTarget( value ); }
        return this;
    }
    public Target getTarget(){
        return _target;
    }
    
    
}