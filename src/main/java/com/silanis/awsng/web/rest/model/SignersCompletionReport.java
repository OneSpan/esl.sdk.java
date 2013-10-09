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
public class SignersCompletionReport extends Model
      implements java.io.Serializable, ISignersCompletionReport
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_COMPLETED = "completed";
    @JsonIgnore
    public static final String FIELD_EMAIL = "email";
    @JsonIgnore
    public static final String FIELD_FIRSTNAME = "firstName";
    @JsonIgnore
    public static final String FIELD_FIRSTSIGNED = "firstSigned";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LASTNAME = "lastName";
    @JsonIgnore
    public static final String FIELD_LASTSIGNED = "lastSigned";
    
    // Empty Constructor
    public SignersCompletionReport ( ) {}
    
    // Fields
    protected Boolean _completed = null;
    protected String _email = "";
    protected String _firstName = "";
    protected java.util.Date _firstSigned = null;
    protected String _id = "";
    protected String _lastName = "";
    protected java.util.Date _lastSigned = null;
    
    // Accessors
        
    
    public SignersCompletionReport setCompleted( Boolean value ){
        // TODO With proper compare
        // if ( this._completed == value ) return this;
        this._completed = value;
        setDirty(FIELD_COMPLETED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignersCompletionReport safeSetCompleted( Boolean value ){
        if ( value != null ) { this.setCompleted( value ); }
        return this;
    }
    public Boolean getCompleted(){
        return _completed;
    }
    
        
    
    public SignersCompletionReport setEmail( String value ){
        SchemaSanitizer.throwOnNull(FIELD_EMAIL,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._email == value ) return this;
        this._email = value;
        setDirty(FIELD_EMAIL);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignersCompletionReport safeSetEmail( String value ){
        if ( value != null ) { this.setEmail( value ); }
        return this;
    }
    public String getEmail(){
        return _email;
    }
    
        
    
    public SignersCompletionReport setFirstName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_FIRSTNAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._firstName == value ) return this;
        this._firstName = value;
        setDirty(FIELD_FIRSTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignersCompletionReport safeSetFirstName( String value ){
        if ( value != null ) { this.setFirstName( value ); }
        return this;
    }
    public String getFirstName(){
        return _firstName;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public SignersCompletionReport setFirstSigned( java.util.Date value ){
        // TODO With proper compare
        // if ( this._firstSigned == value ) return this;
        this._firstSigned = value;
        setDirty(FIELD_FIRSTSIGNED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignersCompletionReport safeSetFirstSigned( java.util.Date value ){
        if ( value != null ) { this.setFirstSigned( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getFirstSigned(){
        return _firstSigned;
    }
    
        
    
    public SignersCompletionReport setId( String value ){
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
    public SignersCompletionReport safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    public String getId(){
        return _id;
    }
    
        
    
    public SignersCompletionReport setLastName( String value ){
        SchemaSanitizer.throwOnNull(FIELD_LASTNAME,value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._lastName == value ) return this;
        this._lastName = value;
        setDirty(FIELD_LASTNAME);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignersCompletionReport safeSetLastName( String value ){
        if ( value != null ) { this.setLastName( value ); }
        return this;
    }
    public String getLastName(){
        return _lastName;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public SignersCompletionReport setLastSigned( java.util.Date value ){
        // TODO With proper compare
        // if ( this._lastSigned == value ) return this;
        this._lastSigned = value;
        setDirty(FIELD_LASTSIGNED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SignersCompletionReport safeSetLastSigned( java.util.Date value ){
        if ( value != null ) { this.setLastSigned( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getLastSigned(){
        return _lastSigned;
    }
    
    
}