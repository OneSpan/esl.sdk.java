package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
public interface IMessage extends IBaseMessage{
    public IMessage setContent( String value);
    public String getContent();
    public IMessage setCreated( java.util.Date value);
    public java.util.Date getCreated();
    public IMessage setDocuments( List<Document> value);
    public List<Document> getDocuments();
    public IMessage setFrom( User value);
    public User getFrom();
    public IMessage setStatus( MessageStatus value);
    public MessageStatus getStatus();
    public IMessage setTo( List<User> value);
    public List<User> getTo();
    }