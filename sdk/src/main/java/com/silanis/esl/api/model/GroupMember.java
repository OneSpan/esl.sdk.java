package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class GroupMember extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_MEMBERTYPE = "memberType";
    @JsonIgnore
    public static final String FIELD_PENDING = "pending";
    @JsonIgnore
    public static final String FIELD_USERID = "userId";
    
    // Empty Constructor
    public GroupMember ( ) {}
    
    // Fields
    protected String _email = "";
    protected String _firstName = "";
    protected String _lastName = "";
    protected String _memberType = "REGULAR";
    protected Boolean _pending = false;
    protected String _userId = "";
    
    // Accessors
        
    
    public GroupMember setEmail( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAIL,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMember safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public String getEmail(){
        return _email;
    }
    
        
    
    public GroupMember setFirstName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_FIRSTNAME,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._firstName == value ) return this;
        this._firstName = value;
        setDirty(FIELD_FIRSTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMember safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    public String getFirstName(){
        return _firstName;
    }
    
        
    
    public GroupMember setLastName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LASTNAME,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._lastName = value;
        setDirty(FIELD_LASTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMember safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    public String getLastName(){
        return _lastName;
    }
    
        
    
    public GroupMember setMemberType( String value ){
        SchemaSanitizer.throwOnNull(FIELD_MEMBERTYPE,value);
        // TODO With proper compare
        // if ( this._memberType == value ) return this;
        this._memberType = value;
        setDirty(FIELD_MEMBERTYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMember safeSetMemberType( String value ){
        if ( value != null ) { this.setMemberType( value ); }
        return this;
    }
    public String getMemberType(){
        return _memberType;
    }
    
        
    
    public GroupMember setPending( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_PENDING,value);
        // TODO With proper compare
        // if ( this._pending == value ) return this;
        this._pending = value;
        setDirty(FIELD_PENDING);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMember safeSetPending( Boolean value ){
        if ( value != null ) { this.setPending( value ); }
        return this;
    }
    public Boolean getPending(){
        return _pending;
    }
    @JsonIgnore
    public boolean evalPending(){
        return _pending == null ? false : _pending.booleanValue();
    }
    
        
    
    public GroupMember setUserId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_USERID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._userId == value ) return this;
        this._userId = value;
        setDirty(FIELD_USERID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMember safeSetUserId( String value ){
        if ( value != null ) { this.setUserId( value ); }
        return this;
    }
    public String getUserId(){
        return _userId;
    }
    
    
}