package com.silanis.esl.api.model;
//
import java.util.List;
import java.util.Map;

public interface IRole extends IEntity{
    public IRole setAttachmentRequirements( List<AttachmentRequirement> value);
    public List<AttachmentRequirement> getAttachmentRequirements();
    public IRole setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IRole setEmailMessage( BaseMessage value);
    public BaseMessage getEmailMessage();
    public IRole setId( String value);
    public String getId();
    public IRole setIndex( Integer value);
    public Integer getIndex();
    public IRole setLocked( Boolean value);
    public Boolean getLocked();
    public IRole setName( String value);
    public String getName();
    public IRole setReassign( Boolean value);
    public Boolean getReassign();
    public IRole setSigners( List<Signer> value);
    public List<Signer> getSigners();
    public IRole setType( RoleType value);
    public RoleType getType();
    }