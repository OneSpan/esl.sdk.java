package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.awsng.web.rest.util.JsonDateDeserializer;
import com.silanis.awsng.web.rest.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class AttachmentBin extends Model
      implements java.io.Serializable, IAttachmentBin
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ATTACHMENTUID = "attachmentUid";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_INSERTDATE = "insertDate";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    
    // Empty Constructor
    public AttachmentBin ( ) {}
    
    // Fields
    protected String _attachmentUid = "";
    protected Map<String, Object> _data = null;
    protected String _id = "";
    protected java.util.Date _insertDate;
    protected String _name = "";
    
    // Accessors
        
    
    public AttachmentBin setAttachmentUid( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ATTACHMENTUID,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._attachmentUid == value ) return this;
        this._attachmentUid = value;
        setDirty(FIELD_ATTACHMENTUID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentBin safeSetAttachmentUid( String value ){
        if ( value != null ) { this.setAttachmentUid( value ); }
        return this;
    }
    public String getAttachmentUid(){
        return _attachmentUid;
    }
    
        
    
    public AttachmentBin setData( Map<String, Object> value ){
        // TODO With proper compare
        // if ( this._data == value ) return this;
        this._data = value;
        setDirty(FIELD_DATA);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentBin safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    public Map<String, Object> getData(){
        return _data;
    }
    
        
    
    public AttachmentBin setId( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ID,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._id == value ) return this;
        this._id = value;
        setDirty(FIELD_ID);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentBin safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public AttachmentBin setInsertDate( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_INSERTDATE,value);
        // TODO With proper compare
        // if ( this._insertDate == value ) return this;
        this._insertDate = value;
        setDirty(FIELD_INSERTDATE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentBin safeSetInsertDate( java.util.Date value ){
        if ( value != null ) { this.setInsertDate( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getInsertDate(){
        return _insertDate;
    }
    
        
    
    public AttachmentBin setName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_NAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._name == value ) return this;
        this._name = value;
        setDirty(FIELD_NAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public AttachmentBin safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
    
}