package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class DelegationEventReport extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_EVENTDATE = "eventDate";
    @JsonIgnore
    public static final String FIELD_EVENTDESCRIPTION = "eventDescription";
    @JsonIgnore
    public static final String FIELD_EVENTTYPE = "eventType";
    @JsonIgnore
    public static final String FIELD_EVENTUSER = "eventUser";
    
    // Empty Constructor
    public DelegationEventReport ( ) {}
    
    // Fields
    protected java.util.Date _eventDate;
    protected String _eventDescription = "";
    protected String _eventType = "";
    protected String _eventUser = "";
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public DelegationEventReport setEventDate( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_EVENTDATE,value);
        // TODO With proper compare
        // if ( this._eventDate == value ) return this;
        this._eventDate = value;
        setDirty(FIELD_EVENTDATE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationEventReport safeSetEventDate( java.util.Date value ){
        if ( value != null ) { this.setEventDate( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getEventDate(){
        return _eventDate;
    }
    
        
    
    public DelegationEventReport setEventDescription( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EVENTDESCRIPTION,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._eventDescription == value ) return this;
        this._eventDescription = value;
        setDirty(FIELD_EVENTDESCRIPTION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationEventReport safeSetEventDescription( String value ){
        if ( value != null ) { this.setEventDescription( value ); }
        return this;
    }
    public String getEventDescription(){
        return _eventDescription;
    }
    
        
    
    public DelegationEventReport setEventType( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EVENTTYPE,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._eventType == value ) return this;
        this._eventType = value;
        setDirty(FIELD_EVENTTYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationEventReport safeSetEventType( String value ){
        if ( value != null ) { this.setEventType( value ); }
        return this;
    }
    public String getEventType(){
        return _eventType;
    }
    
        
    
    public DelegationEventReport setEventUser( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EVENTUSER,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._eventUser == value ) return this;
        this._eventUser = value;
        setDirty(FIELD_EVENTUSER);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DelegationEventReport safeSetEventUser( String value ){
        if ( value != null ) { this.setEventUser( value ); }
        return this;
    }
    public String getEventUser(){
        return _eventUser;
    }
    
    
}