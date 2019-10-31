package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.JsonDateDeserializer;
import com.silanis.esl.api.util.JsonDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Approval extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ACCEPTED = "accepted";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_FIELDS = "fields";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_ROLE = "role";
    @JsonIgnore
    public static final String FIELD_SIGNED = "signed";
    @JsonIgnore
    public static final String FIELD_OPTIONAL = "optional";
    @JsonIgnore
    public static final String FIELD_DISABLED = "disabled";
    @JsonIgnore
    public static final String FIELD_ENFORCE_CAPTURE_SIGNATURE = "enforceCaptureSignature";
    
    // Empty Constructor
    public Approval ( ) {}
    
    // Fields
    protected java.util.Date _accepted = null;
    protected List<Field> _fields = new ArrayList<Field>();
    protected String _role = "";
    protected java.util.Date _signed = null;
    protected Boolean _optional = false;
    protected Boolean _disabled = false;
    protected Boolean _enforceCaptureSignature = false;

    // Accessors
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Approval setAccepted( java.util.Date value ){
        // TODO With proper compare
        // if ( this._accepted == value ) return this;
        this._accepted = value;
        setDirty(FIELD_ACCEPTED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetAccepted( java.util.Date value ){
        if ( value != null ) { this.setAccepted( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getAccepted(){
        return _accepted;
    }
    
        
    
    @Override
    public Approval setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public Approval setFields( List<Field> value ){
        SchemaSanitizer.throwOnNull(FIELD_FIELDS,value);
        // TODO With proper compare
        // if ( this._fields == value ) return this;
        this._fields = value;
        setDirty(FIELD_FIELDS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetFields( List<Field> value ){
        if ( value != null ) { this.setFields( value ); }
        return this;
    }
    public List<Field> getFields(){
        return _fields;
    }
    // List adder
    public Approval addField( Field value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._fields.add(value);
        setDirty(FIELD_FIELDS);
        return this;
    }
    
        
    
    @Override
    public Approval setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public Approval setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public Approval setRole( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ROLE,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._role == value ) return this;
        this._role = value;
        setDirty(FIELD_ROLE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetRole( String value ){
        if ( value != null ) { this.setRole( value ); }
        return this;
    }
    public String getRole(){
        return _role;
    }
    
        
    
    @JsonDeserialize(using = JsonDateDeserializer.class)
    public Approval setSigned( java.util.Date value ){
        // TODO With proper compare
        // if ( this._signed == value ) return this;
        this._signed = value;
        setDirty(FIELD_SIGNED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetSigned( java.util.Date value ){
        if ( value != null ) { this.setSigned( value ); }
        return this;
    }
    @JsonSerialize(using = JsonDateSerializer.class)
    public java.util.Date getSigned(){
        return _signed;
    }

    public Approval setOptional(Boolean value){
        this._optional = value;
        setDirty(FIELD_OPTIONAL);
        return this;
    }
    @JsonIgnore
    public Approval safeSetOptional(Boolean value){
        if ( value != null ) { this.setOptional(value); }
        return this;
    }
    public Boolean getOptional() { return _optional; }

    public Approval setDisabled(Boolean value){
        this._disabled = value;
        setDirty(FIELD_DISABLED);
        return this;
    }
    @JsonIgnore
    public Approval safeSetDisabled(Boolean value){
        if ( value != null ) { this.setDisabled(value); }
        return this;
    }
    public Boolean getDisabled() { return _disabled; }

    public Approval setEnforceCaptureSignature(Boolean value) {
        this._enforceCaptureSignature = value;
        setDirty(FIELD_ENFORCE_CAPTURE_SIGNATURE);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Approval safeSetEnforceCaptureSignature(Boolean value) {
        if (value != null) {
            this.setEnforceCaptureSignature(value);
        }
        return this;
    }

    public Boolean getEnforceCaptureSignature() {
        return _enforceCaptureSignature;
    }
}