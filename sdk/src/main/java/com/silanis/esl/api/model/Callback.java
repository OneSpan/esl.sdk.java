package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.ArrayList;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Callback extends Model
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_EVENTS = "events";
    @JsonIgnore
    public static final String FIELD_URL = "url";
    
    // Empty Constructor
    public Callback ( ) {}
    
    // Fields
    protected List<String> _events = new ArrayList<String>();
    protected String _url = "";
    
    // Accessors
        
    
    public Callback setEvents( List<String> value ){
        SchemaSanitizer.throwOnNull(FIELD_EVENTS,value);
        // TODO With proper compare
        // if ( this._events == value ) return this;
        this._events = value;
        setDirty(FIELD_EVENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Callback safeSetEvents( List<String> value ){
        if ( value != null ) { this.setEvents( value ); }
        return this;
    }
    public List<String> getEvents(){
        return _events;
    }
    // List adder
    public Callback addEvent( String value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._events.add(value);
        setDirty(FIELD_EVENTS);
        return this;
    }
    
        
    
    public Callback setUrl( String value ){
        SchemaSanitizer.throwOnNull(FIELD_URL,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._url == value ) return this;
        this._url = value;
        setDirty(FIELD_URL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Callback safeSetUrl( String value ){
        if ( value != null ) { this.setUrl( value ); }
        return this;
    }
    public String getUrl(){
        return _url;
    }
    
    
}