package com.silanis.esl.api.model;
//
import java.util.Map;

public interface IAttachmentBin {
    public IAttachmentBin setAttachmentUid( String value);
    public String getAttachmentUid();
    public IAttachmentBin setData( Map<String, Object> value);
    public Map<String, Object> getData();
    public IAttachmentBin setId( String value);
    public String getId();
    public IAttachmentBin setInsertDate( java.util.Date value);
    public java.util.Date getInsertDate();
    public IAttachmentBin setName( String value);
    public String getName();
    }