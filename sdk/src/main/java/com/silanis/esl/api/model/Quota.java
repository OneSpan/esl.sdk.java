package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Quota extends Model
      implements java.io.Serializable
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
    protected String _cycle = null;
    protected Integer _limit = 0;
    protected String _scope = "SENDER";
    protected String _target = "SIGNER";
    
    // Accessors
        
    
    public Quota setCycle( String value ){
        // TODO With proper compare
        // if ( this._cycle == value ) return this;
        this._cycle = value;
        setDirty(FIELD_CYCLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetCycle( String value ){
        if ( value != null ) { this.setCycle( value ); }
        return this;
    }
    public String getCycle(){
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
    
        
    
    public Quota setScope( String value ){
        // TODO With proper compare
        // if ( this._scope == value ) return this;
        this._scope = value;
        setDirty(FIELD_SCOPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetScope( String value ){
        if ( value != null ) { this.setScope( value ); }
        return this;
    }
    public String getScope(){
        return _scope;
    }
    
        
    
    public Quota setTarget( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TARGET,value);
        // TODO With proper compare
        // if ( this._target == value ) return this;
        this._target = value;
        setDirty(FIELD_TARGET);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Quota safeSetTarget( String value ){
        if ( value != null ) { this.setTarget( value ); }
        return this;
    }
    public String getTarget(){
        return _target;
    }
    
    
}