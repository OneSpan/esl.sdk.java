package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class SenderCompletionReport extends Model
      implements java.io.Serializable, ISenderCompletionReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_PACKAGES = "packages";
    @JsonIgnore
    public static final String FIELD_SENDER = "sender";
    
    // Empty Constructor
    public SenderCompletionReport ( ) {}
    
    // Fields
    protected List<PackageCompletionReport> _packages = new ArrayList<PackageCompletionReport>();
    protected Sender _sender;
    
    // Accessors
        
    
    public SenderCompletionReport setPackages( List<PackageCompletionReport> value ){
        SchemaSanitizer.throwOnNull(FIELD_PACKAGES,value);
        // TODO With proper compare
        // if ( this._packages == value ) return this;
        this._packages = value;
        setDirty(FIELD_PACKAGES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SenderCompletionReport safeSetPackages( List<PackageCompletionReport> value ){
        if ( value != null ) { this.setPackages( value ); }
        return this;
    }
    public List<PackageCompletionReport> getPackages(){
        return _packages;
    }
    // List adder
    public SenderCompletionReport addPackage( PackageCompletionReport value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._packages.add(value);
        setDirty(FIELD_PACKAGES);
        return this;
    }
    
        
    
    public SenderCompletionReport setSender( Sender value ){
        SchemaSanitizer.throwOnNull(FIELD_SENDER,value);
        // TODO With proper compare
        // if ( this._sender == value ) return this;
        this._sender = value;
        setDirty(FIELD_SENDER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SenderCompletionReport safeSetSender( Sender value ){
        if ( value != null ) { this.setSender( value ); }
        return this;
    }
    public Sender getSender(){
        return _sender;
    }
    
    
}