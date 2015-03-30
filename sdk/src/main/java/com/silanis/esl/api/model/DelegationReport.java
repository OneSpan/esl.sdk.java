package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.List;
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
    protected List<DelegationEventReport> _delegationEventReports = new ArrayList<DelegationEventReport>();
    
    // Accessors
        
    
    public DelegationReport setDelegationEventReports( List<DelegationEventReport> value ){
        SchemaSanitizer.throwOnNull(FIELD_DELEGATIONEVENTREPORTS,value);
        // TODO With proper compare
        // if ( this._delegationEventReports == value ) return this;
        this._delegationEventReports = value;
        setDirty(FIELD_DELEGATIONEVENTREPORTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationReport safeSetDelegationEventReports( List<DelegationEventReport> value ){
        if ( value != null ) { this.setDelegationEventReports( value ); }
        return this;
    }
    public List<DelegationEventReport> getDelegationEventReports(){
        return _delegationEventReports;
    }
    // List adder
    public DelegationReport addDelegationEventReport( DelegationEventReport value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._delegationEventReports.add(value);
        setDirty(FIELD_DELEGATIONEVENTREPORTS);
        return this;
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