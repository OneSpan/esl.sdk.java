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
public class CompletionReport extends Report
      implements java.io.Serializable, ICompletionReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_FROM = "from";
    @JsonIgnore
    public static final String FIELD_SENDERS = "senders";
    @JsonIgnore
    public static final String FIELD_TO = "to";
    
    // Empty Constructor
    public CompletionReport ( ) {}
    
    // Fields
    protected List<SenderCompletionReport> _senders = new ArrayList<SenderCompletionReport>();
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public CompletionReport setFrom( java.util.Date value ){
        super.setFrom(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CompletionReport safeSetFrom( java.util.Date value ){
        if ( value != null ) { this.setFrom( value ); }
        return this;
    }
    
        
    
    public CompletionReport setSenders( List<SenderCompletionReport> value ){
        SchemaSanitizer.throwOnNull(FIELD_SENDERS,value);
        // TODO With proper compare
        // if ( this._senders == value ) return this;
        this._senders = value;
        setDirty(FIELD_SENDERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CompletionReport safeSetSenders( List<SenderCompletionReport> value ){
        if ( value != null ) { this.setSenders( value ); }
        return this;
    }
    public List<SenderCompletionReport> getSenders(){
        return _senders;
    }
    // List adder
    public CompletionReport addSender( SenderCompletionReport value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._senders.add(value);
        setDirty(FIELD_SENDERS);
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    @Override
    public CompletionReport setTo( java.util.Date value ){
        super.setTo(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CompletionReport safeSetTo( java.util.Date value ){
        if ( value != null ) { this.setTo( value ); }
        return this;
    }
    
    
}