package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SenderUsageReport extends Model
      implements java.io.Serializable, ISenderUsageReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_PACKAGES = "packages";
    @JsonIgnore
    public static final String FIELD_SENDER = "sender";
    
    // Empty Constructor
    public SenderUsageReport ( ) {}
    
    // Fields
    protected Map<String, Object> _packages;
    protected Sender _sender;
    
    // Accessors
        
    
    public SenderUsageReport setPackages( Map<String, Object> value ){
        SchemaSanitizer.throwOnNull(FIELD_PACKAGES,value);
        // TODO With proper compare
        // if ( this._packages == value ) return this;
        this._packages = value;
        setDirty(FIELD_PACKAGES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SenderUsageReport safeSetPackages( Map<String, Object> value ){
        if ( value != null ) { this.setPackages( value ); }
        return this;
    }
    public Map<String, Object> getPackages(){
        return _packages;
    }
    
        
    
    public SenderUsageReport setSender( Sender value ){
        SchemaSanitizer.throwOnNull(FIELD_SENDER,value);
        // TODO With proper compare
        // if ( this._sender == value ) return this;
        this._sender = value;
        setDirty(FIELD_SENDER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SenderUsageReport safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    public Sender getSender(){
        return _sender;
    }
    
    
}