package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Account extends Entity
      implements java.io.Serializable, IAccount
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COMPANY = "company";
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LICENSES = "licenses";
    @JsonIgnore
    public static final String FIELD_LOGOURL = "logoUrl";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_OWNER = "owner";
    @JsonIgnore
    public static final String FIELD_PROVIDERS = "providers";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    
    // Empty Constructor
    public Account ( ) {}
    
    // Fields
    protected Company _company;
    protected java.util.Date _created;
    protected List<License> _licenses = new ArrayList<License>();
    protected String _logoUrl = "";
    protected String _owner = "";
    protected AccountProviders _providers = null;
    protected java.util.Date _updated;
    
    // Accessors
        
    
    public Account setCompany( Company value ){
        SchemaSanitizer.throwOnNull(FIELD_COMPANY,value);
        // TODO With proper compare
        // if ( this._company == value ) return this;
        this._company = value;
        setDirty(FIELD_COMPANY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetCompany( Company value ){
        if ( value != null ) { this.setCompany( value ); }
        return this;
    }
    public Company getCompany(){
        return _company;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Account setCreated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_CREATED,value);
        // TODO With proper compare
        // if ( this._created == value ) return this;
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }
    
        
    
    @Override
    public Account setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public Account setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public Account setLicenses( List<License> value ){
        SchemaSanitizer.throwOnNull(FIELD_LICENSES,value);
        // TODO With proper compare
        // if ( this._licenses == value ) return this;
        this._licenses = value;
        setDirty(FIELD_LICENSES);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetLicenses( List<License> value ){
        if ( value != null ) { this.setLicenses( value ); }
        return this;
    }
    public List<License> getLicenses(){
        return _licenses;
    }
    // List adder
    public Account addLicense( License value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._licenses.add(value);
        setDirty(FIELD_LICENSES);
        return this;
    }
    
        
    
    public Account setLogoUrl( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._logoUrl == value ) return this;
        this._logoUrl = value;
        setDirty(FIELD_LOGOURL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetLogoUrl( String value ){
        if ( value != null ) { this.setLogoUrl( value ); }
        return this;
    }
    public String getLogoUrl(){
        return _logoUrl;
    }
    
        
    
    @Override
    public Account setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public Account setOwner( String value ){
        SchemaSanitizer.throwOnNull(FIELD_OWNER,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._owner == value ) return this;
        this._owner = value;
        setDirty(FIELD_OWNER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetOwner( String value ){
        if ( value != null ) { this.setOwner( value ); }
        return this;
    }
    public String getOwner(){
        return _owner;
    }
    
        
    
    public Account setProviders( AccountProviders value ){
        // TODO With proper compare
        // if ( this._providers == value ) return this;
        this._providers = value;
        setDirty(FIELD_PROVIDERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetProviders( AccountProviders value ){
        if ( value != null ) { this.setProviders( value ); }
        return this;
    }
    public AccountProviders getProviders(){
        return _providers;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Account setUpdated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_UPDATED,value);
        // TODO With proper compare
        // if ( this._updated == value ) return this;
        this._updated = value;
        setDirty(FIELD_UPDATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Account safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getUpdated(){
        return _updated;
    }
    
    
}