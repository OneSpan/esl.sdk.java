package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class UsageReport extends Report
      implements java.io.Serializable, IUsageReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_FROM = "from";
    @JsonIgnore
    public static final String FIELD_SENDERS = "senders";
    @JsonIgnore
    public static final String FIELD_TO = "to";
    
    // Empty Constructor
    public UsageReport ( ) {}
    
    // Fields
    protected List<SenderUsageReport> _senders = new ArrayList<SenderUsageReport>();
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public UsageReport setFrom( java.util.Date value ){
        super.setFrom(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UsageReport safeSetFrom( java.util.Date value ){
        if ( value != null ) { this.setFrom( value ); }
        return this;
    }
    
        
    
    public UsageReport setSenders( List<SenderUsageReport> value ){
        SchemaSanitizer.throwOnNull(FIELD_SENDERS,value);
        // TODO With proper compare
        // if ( this._senders == value ) return this;
        this._senders = value;
        setDirty(FIELD_SENDERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UsageReport safeSetSenders( List<SenderUsageReport> value ){
        if ( value != null ) { this.setSenders( value ); }
        return this;
    }
    public List<SenderUsageReport> getSenders(){
        return _senders;
    }
    // List adder
    public UsageReport addSender( SenderUsageReport value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._senders.add(value);
        setDirty(FIELD_SENDERS);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public UsageReport setTo( java.util.Date value ){
        super.setTo(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UsageReport safeSetTo( java.util.Date value ){
        if ( value != null ) { this.setTo( value ); }
        return this;
    }
    
    
}