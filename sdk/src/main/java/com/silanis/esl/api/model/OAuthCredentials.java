package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class OAuthCredentials extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CONNECTIONDATA = "connectionData";
    @JsonIgnore
    public static final String FIELD_ESLUSERUID = "eslUserUid";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_PROVIDERID = "providerId";
    
    // Empty Constructor
    public OAuthCredentials ( ) {}
    
    // Fields
    protected String _connectionData = "";
    protected String _eslUserUid = "";
    protected Integer _id;
    protected String _providerId = "";
    
    // Accessors
        
    
    public OAuthCredentials setConnectionData( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CONNECTIONDATA,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._connectionData == value ) return this;
        this._connectionData = value;
        setDirty(FIELD_CONNECTIONDATA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public OAuthCredentials safeSetConnectionData( String value ){
        if ( value != null ) { this.setConnectionData( value ); }
        return this;
    }
    public String getConnectionData(){
        return _connectionData;
    }
    
        
    
    public OAuthCredentials setEslUserUid( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ESLUSERUID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._eslUserUid == value ) return this;
        this._eslUserUid = value;
        setDirty(FIELD_ESLUSERUID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public OAuthCredentials safeSetEslUserUid( String value ){
        if ( value != null ) { this.setEslUserUid( value ); }
        return this;
    }
    public String getEslUserUid(){
        return _eslUserUid;
    }
    
        
    
    public OAuthCredentials setId( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_ID,value);
        // TODO With proper compare
        // if ( this._id == value ) return this;
        this._id = value;
        setDirty(FIELD_ID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public OAuthCredentials safeSetId( Integer value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public Integer getId(){
        return _id;
    }
    
        
    
    public OAuthCredentials setProviderId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_PROVIDERID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._providerId == value ) return this;
        this._providerId = value;
        setDirty(FIELD_PROVIDERID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public OAuthCredentials safeSetProviderId( String value ){
        if ( value != null ) { this.setProviderId( value ); }
        return this;
    }
    public String getProviderId(){
        return _providerId;
    }
    
    
}