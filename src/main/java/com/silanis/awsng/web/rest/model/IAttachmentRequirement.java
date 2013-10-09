package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IAttachmentRequirement extends IEntity{
    public IAttachmentRequirement setAttachmentBin( AttachmentBin value);
    public AttachmentBin getAttachmentBin();
    public IAttachmentRequirement setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IAttachmentRequirement setDescription( String value);
    public String getDescription();
    public IAttachmentRequirement setId( String value);
    public String getId();
    public IAttachmentRequirement setName( String value);
    public String getName();
    public IAttachmentRequirement setRequired( Boolean value);
    public Boolean getRequired();
    public IAttachmentRequirement setStatus( RequirementStatus value);
    public RequirementStatus getStatus();
    }