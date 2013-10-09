package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CeremonyEvents extends Model
      implements java.io.Serializable, ICeremonyEvents
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COMPLETE = "complete";
    
    // Empty Constructor
    public CeremonyEvents ( ) {}
    
    // Fields
    protected CeremonyEventComplete _complete = null;
    
    // Accessors
        
    
    public CeremonyEvents setComplete( CeremonyEventComplete value ){
        // TODO With proper compare
        // if ( this._complete == value ) return this;
        this._complete = value;
        setDirty(FIELD_COMPLETE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CeremonyEvents safeSetComplete( CeremonyEventComplete value ){
        if ( value != null ) { this.setComplete( value ); }
        return this;
    }
    public CeremonyEventComplete getComplete(){
        return _complete;
    }
    
    
}