package com.silanis.esl.api.model;
//

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DelegationReport extends Report
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DELEGATIONEVENTREPORTS = "delegationEventReports";
    @JsonIgnore
    public static final String FIELD_FROM = "from";
    @JsonIgnore
    public static final String FIELD_TO = "to";
    
    // Empty Constructor
    public DelegationReport ( ) {}
    
    // Fields
    protected Map<String, Collection<DelegationEventReport>> _delegationEventReports = new HashMap<String, Collection<DelegationEventReport>>();
    
    // Accessors


    public DelegationReport setDelegationEvents( Map<String, Collection<DelegationEventReport>> value ){
        SchemaSanitizer.throwOnNull(FIELD_DELEGATIONEVENTREPORTS,value);
        // TODO With proper compare
        // if ( this._delegationEventReports == value ) return this;
        this._delegationEventReports = value;
        setDirty(FIELD_DELEGATIONEVENTREPORTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationReport safeSetDelegationEvents( Map<String, Collection<DelegationEventReport>> value ){
        if ( value != null ) { this.setDelegationEvents( value ); }
        return this;
    }
    public Map<String, Collection<DelegationEventReport>> getDelegationEvents(){
        return _delegationEventReports;
    }

        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public DelegationReport setFrom( java.util.Date value ){
        super.setFrom(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationReport safeSetFrom( java.util.Date value ){
        if ( value != null ) { this.setFrom( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public DelegationReport setTo( java.util.Date value ){
        super.setTo(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationReport safeSetTo( java.util.Date value ){
        if ( value != null ) { this.setTo( value ); }
        return this;
    }
    
    
}