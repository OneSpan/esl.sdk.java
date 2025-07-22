package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Group extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ACCOUNT = "account";
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_EMAILMEMBERS = "emailMembers";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_MEMBERS = "members";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_UPDATED = "updated";
    
    // Empty Constructor
    public Group ( ) {}
    
    // Fields
    protected Account _account = null;
    protected java.util.Date _created;
    protected String _email = null;
    protected Boolean _emailMembers = false;
    protected List<GroupMember> _members = new ArrayList<GroupMember>();
    protected java.util.Date _updated;
    
    // Accessors
        
    
    public Group setAccount( Account value ){
        // TODO With proper compare
        // if ( this._account == value ) return this;
        this._account = value;
        setDirty(FIELD_ACCOUNT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetAccount( Account value ){
        if ( value != null ) { this.setAccount( value ); }
        return this;
    }
    public Account getAccount(){
        return _account;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Group setCreated( java.util.Date value ){
       // SchemaSanitizer.throwOnNull(FIELD_CREATED,value);
        // TODO With proper compare
        // if ( this._created == value ) return this;
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }
    
        
    
    @Override
    public Group setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public Group setEmail( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public String getEmail(){
        return _email;
    }
    
        
    
    public Group setEmailMembers( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAILMEMBERS,value);
        // TODO With proper compare
        // if ( this._emailMembers == value ) return this;
        this._emailMembers = value;
        setDirty(FIELD_EMAILMEMBERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetEmailMembers( Boolean value ){
        if ( value != null ) { this.setEmailMembers( value ); }
        return this;
    }
    public Boolean getEmailMembers(){
        return _emailMembers;
    }
    @JsonIgnore
    public boolean evalEmailMembers(){
        return _emailMembers == null ? false : _emailMembers.booleanValue();
    }
    
        
    
    @Override
    public Group setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public Group setMembers( List<GroupMember> value ){
        SchemaSanitizer.throwOnNull(FIELD_MEMBERS,value);
        // TODO With proper compare
        // if ( this._members == value ) return this;
        this._members = value;
        setDirty(FIELD_MEMBERS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetMembers( List<GroupMember> value ){
        if ( value != null ) { this.setMembers( value ); }
        return this;
    }
    public List<GroupMember> getMembers(){
        return _members;
    }
    // List adder
    public Group addMember( GroupMember value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._members.add(value);
        setDirty(FIELD_MEMBERS);
        return this;
    }
    
        
    
    @Override
    public Group setName( String value ){
        // TODO: Figure how to do refinements of validation rules
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Group setUpdated( java.util.Date value ){
        //SchemaSanitizer.throwOnNull(FIELD_UPDATED,value);
        // TODO With proper compare
        // if ( this._updated == value ) return this;
        this._updated = value;
        setDirty(FIELD_UPDATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Group safeSetUpdated( java.util.Date value ){
        if ( value != null ) { this.setUpdated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getUpdated(){
        return _updated;
    }
    
    
}