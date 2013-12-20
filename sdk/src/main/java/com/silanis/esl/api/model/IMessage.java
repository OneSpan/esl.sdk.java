package com.silanis.esl.api.model;
//
import java.util.List;

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