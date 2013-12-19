package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class PackageReminder extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DATE = "date";
    @JsonIgnore
    public static final String FIELD_SENTDATE = "sentDate";
    
    // Empty Constructor
    public PackageReminder ( ) {}
    
    // Fields
    protected java.util.Date _date;
    protected java.util.Date _sentDate = null;
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public PackageReminder setDate( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_DATE,value);
        // TODO With proper compare
        // if ( this._date == value ) return this;
        this._date = value;
        setDirty(FIELD_DATE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminder safeSetDate( java.util.Date value ){
        if ( value != null ) { this.setDate( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getDate(){
        return _date;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public PackageReminder setSentDate( java.util.Date value ){
        // TODO With proper compare
        // if ( this._sentDate == value ) return this;
        this._sentDate = value;
        setDirty(FIELD_SENTDATE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public PackageReminder safeSetSentDate( java.util.Date value ){
        if ( value != null ) { this.setSentDate( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getSentDate(){
        return _sentDate;
    }
    
    
}