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
@JsonIgnoreProperties(ignoreUnknown=true)
public class Message extends BaseMessage
      implements java.io.Serializable, IMessage
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CONTENT = "content";
    @JsonIgnore
    public static final String FIELD_CREATED = "created";
    @JsonIgnore
    public static final String FIELD_DOCUMENTS = "documents";
    @JsonIgnore
    public static final String FIELD_FROM = "from";
    @JsonIgnore
    public static final String FIELD_STATUS = "status";
    @JsonIgnore
    public static final String FIELD_TO = "to";
    
    // Empty Constructor
    public Message ( ) {}
    
    // Fields
    protected java.util.Date _created;
    protected List<Document> _documents = new ArrayList<Document>();
    protected User _from;
    protected MessageStatus _status = MessageStatus.NEW;
    protected List<User> _to = new ArrayList<User>();
    
    // Accessors
        
    
    @Override
    public Message setContent( String value ){
        super.setContent(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Message safeSetContent( String value ){
        if ( value != null ) { this.setContent( value ); }
        return this;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Message setCreated( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_CREATED,value);
        // TODO With proper compare
        // if ( this._created == value ) return this;
        this._created = value;
        setDirty(FIELD_CREATED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Message safeSetCreated( java.util.Date value ){
        if ( value != null ) { this.setCreated( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getCreated(){
        return _created;
    }
    
        
    
    public Message setDocuments( List<Document> value ){
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTS,value);
        // TODO With proper compare
        // if ( this._documents == value ) return this;
        this._documents = value;
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Message safeSetDocuments( List<Document> value ){
        if ( value != null ) { this.setDocuments( value ); }
        return this;
    }
    public List<Document> getDocuments(){
        return _documents;
    }
    // List adder
    public Message addDocument( Document value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._documents.add(value);
        setDirty(FIELD_DOCUMENTS);
        return this;
    }
    
        
    
    public Message setFrom( User value ){
        SchemaSanitizer.throwOnNull(FIELD_FROM,value);
        // TODO With proper compare
        // if ( this._from == value ) return this;
        this._from = value;
        setDirty(FIELD_FROM);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Message safeSetFrom( User value ){
        if ( value != null ) { this.setFrom( value ); }
        return this;
    }
    public User getFrom(){
        return _from;
    }
    
        
    
    public Message setStatus( MessageStatus value ){
        SchemaSanitizer.throwOnNull(FIELD_STATUS,value);
        // TODO With proper compare
        // if ( this._status == value ) return this;
        this._status = value;
        setDirty(FIELD_STATUS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Message safeSetStatus( MessageStatus value ){
        if ( value != null ) { this.setStatus( value ); }
        return this;
    }
    public MessageStatus getStatus(){
        return _status;
    }
    
        
    
    public Message setTo( List<User> value ){
        SchemaSanitizer.throwOnNull(FIELD_TO,value);
        // TODO With proper compare
        // if ( this._to == value ) return this;
        this._to = value;
        setDirty(FIELD_TO);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Message safeSetTo( List<User> value ){
        if ( value != null ) { this.setTo( value ); }
        return this;
    }
    public List<User> getTo(){
        return _to;
    }
    // List adder
    public Message addTo( User value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._to.add(value);
        setDirty(FIELD_TO);
        return this;
    }
    
    
}