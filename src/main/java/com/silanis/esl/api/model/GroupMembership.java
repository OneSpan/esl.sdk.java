package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class GroupMembership extends Model
      implements java.io.Serializable, IGroupMembership
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_GROUPID = "groupId";
    @JsonIgnore
    public static final String FIELD_GROUPNAME = "groupName";
    @JsonIgnore
    public static final String FIELD_MEMBERTYPE = "memberType";
    
    // Empty Constructor
    public GroupMembership ( ) {}
    
    // Fields
    protected String _groupId = "";
    protected String _groupName = "";
    protected MemberType _memberType = MemberType.REGULAR;
    
    // Accessors
        
    
    public GroupMembership setGroupId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_GROUPID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._groupId == value ) return this;
        this._groupId = value;
        setDirty(FIELD_GROUPID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMembership safeSetGroupId( String value ){
        if ( value != null ) { this.setGroupId( value ); }
        return this;
    }
    public String getGroupId(){
        return _groupId;
    }
    
        
    
    public GroupMembership setGroupName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_GROUPNAME,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._groupName == value ) return this;
        this._groupName = value;
        setDirty(FIELD_GROUPNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMembership safeSetGroupName( String value ){
        if ( value != null ) { this.setGroupName( value ); }
        return this;
    }
    public String getGroupName(){
        return _groupName;
    }
    
        
    
    public GroupMembership setMemberType( MemberType value ){
        SchemaSanitizer.throwOnNull(FIELD_MEMBERTYPE,value);
        // TODO With proper compare
        // if ( this._memberType == value ) return this;
        this._memberType = value;
        setDirty(FIELD_MEMBERTYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public GroupMembership safeSetMemberType( MemberType value ){
        if ( value != null ) { this.setMemberType( value ); }
        return this;
    }
    public MemberType getMemberType(){
        return _memberType;
    }
    
    
}