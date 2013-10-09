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
public class Report extends Model
      implements java.io.Serializable, IReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_FROM = "from";
    @JsonIgnore
    public static final String FIELD_TO = "to";
    
    // Empty Constructor
    public Report ( ) {}
    
    // Fields
    protected java.util.Date _from;
    protected java.util.Date _to;
    
    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Report setFrom( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_FROM,value);
        // TODO With proper compare
        // if ( this._from == value ) return this;
        this._from = value;
        setDirty(FIELD_FROM);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Report safeSetFrom( java.util.Date value ){
        if ( value != null ) { this.setFrom( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getFrom(){
        return _from;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Report setTo( java.util.Date value ){
        SchemaSanitizer.throwOnNull(FIELD_TO,value);
        // TODO With proper compare
        // if ( this._to == value ) return this;
        this._to = value;
        setDirty(FIELD_TO);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Report safeSetTo( java.util.Date value ){
        if ( value != null ) { this.setTo( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getTo(){
        return _to;
    }
    
    
}