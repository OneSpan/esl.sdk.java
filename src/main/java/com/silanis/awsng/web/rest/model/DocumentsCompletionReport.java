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
public class DocumentsCompletionReport extends Model
      implements java.io.Serializable, IDocumentsCompletionReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COMPLETED = "completed";
    @JsonIgnore
    public static final String FIELD_FIRSTSIGNED = "firstSigned";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LASTSIGNED = "lastSigned";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    
    // Empty Constructor
    public DocumentsCompletionReport ( ) {}
    
    // Fields
    protected Boolean _completed = null;
    protected java.util.Date _firstSigned = null;
    protected String _id = "";
    protected java.util.Date _lastSigned = null;
    protected String _name = "";
    
    // Accessors
        
    
    public DocumentsCompletionReport setCompleted( Boolean value ){
        // TODO With proper compare
        // if ( this._completed == value ) return this;
        this._completed = value;
        setDirty(FIELD_COMPLETED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DocumentsCompletionReport safeSetCompleted( Boolean value ){
        if ( value != null ) { this.setCompleted( value ); }
        return this;
    }
    public Boolean getCompleted(){
        return _completed;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public DocumentsCompletionReport setFirstSigned( java.util.Date value ){
        // TODO With proper compare
        // if ( this._firstSigned == value ) return this;
        this._firstSigned = value;
        setDirty(FIELD_FIRSTSIGNED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DocumentsCompletionReport safeSetFirstSigned( java.util.Date value ){
        if ( value != null ) { this.setFirstSigned( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getFirstSigned(){
        return _firstSigned;
    }
    
        
    
    public DocumentsCompletionReport setId( String value ){
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
    public DocumentsCompletionReport safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public DocumentsCompletionReport setLastSigned( java.util.Date value ){
        // TODO With proper compare
        // if ( this._lastSigned == value ) return this;
        this._lastSigned = value;
        setDirty(FIELD_LASTSIGNED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public DocumentsCompletionReport safeSetLastSigned( java.util.Date value ){
        if ( value != null ) { this.setLastSigned( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getLastSigned(){
        return _lastSigned;
    }
    
        
    
    public DocumentsCompletionReport setName( String value ){
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
    public DocumentsCompletionReport safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    public String getName(){
        return _name;
    }
    
    
}