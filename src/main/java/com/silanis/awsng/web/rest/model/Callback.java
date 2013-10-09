package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Callback extends Model
      implements java.io.Serializable, ICallback
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_EVENTS = "events";
    @JsonIgnore
    public static final String FIELD_URL = "url";
    
    // Empty Constructor
    public Callback ( ) {}
    
    // Fields
    protected List<CallbackEvent> _events = new ArrayList<CallbackEvent>();
    protected String _url = "";
    
    // Accessors
        
    
    public Callback setEvents( List<CallbackEvent> value ){
        SchemaSanitizer.throwOnNull(FIELD_EVENTS,value);
        // TODO With proper compare
        // if ( this._events == value ) return this;
        this._events = value;
        setDirty(FIELD_EVENTS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Callback safeSetEvents( List<CallbackEvent> value ){
        if ( value != null ) { this.setEvents( value ); }
        return this;
    }
    public List<CallbackEvent> getEvents(){
        return _events;
    }
    // List adder
    public Callback addEvent( CallbackEvent value ){
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