package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class AccountProviders extends Model
      implements java.io.Serializable, IAccountProviders
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DOCUMENTS = "documents";
    @JsonIgnore
    public static final String FIELD_USERS = "users";
    
    // Empty Constructor
    public AccountProviders ( ) {}
    
    // Fields
    protected List<Provider> _documents = new ArrayList<Provider>();
    protected List<Provider> _users = new ArrayList<Provider>();
    
    // Accessors
        
    
    public AccountProviders setDocuments( List<Provider> value ){
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTS,value);
        // TODO With proper compare
        // if ( this._documents == value ) return this;
        this._documents = value;
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AccountProviders safeSetDocuments( List<Provider> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    public List<Provider> getDocuments(){
        return _documents;
    }
    // List adder
    public AccountProviders addDocument( Provider value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._documents.add(value);
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    
        
    
    public AccountProviders setUsers( List<Provider> value ){
        SchemaSanitizer.throwOnNull(FIELD_USERS,value);
        // TODO With proper compare
        // if ( this._users == value ) return this;
        this._users = value;
        setDirty(FIELD_USERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AccountProviders safeSetUsers( List<Provider> value ){
        if ( value != null ) { this.setUsers( value ); }
        return this;
    }
    public List<Provider> getUsers(){
        return _users;
    }
    // List adder
    public AccountProviders addUser( Provider value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._users.add(value);
        setDirty(FIELD_USERS);
        return this;
    }
    
    
}