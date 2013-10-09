package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class FieldValidation extends Model
      implements java.io.Serializable, IFieldValidation
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ENUM = "enum";
    @JsonIgnore
    public static final String FIELD_ERRORCODE = "errorCode";
    @JsonIgnore
    public static final String FIELD_ERRORMESSAGE = "errorMessage";
    @JsonIgnore
    public static final String FIELD_MAXLENGTH = "maxLength";
    @JsonIgnore
    public static final String FIELD_MINLENGTH = "minLength";
    @JsonIgnore
    public static final String FIELD_PATTERN = "pattern";
    @JsonIgnore
    public static final String FIELD_REQUIRED = "required";
    
    // Empty Constructor
    public FieldValidation ( ) {}
    
    // Fields
    protected List<String> _enum = null;
    protected Integer _errorCode = null;
    protected String _errorMessage = "";
    protected Integer _maxLength = null;
    protected Integer _minLength = null;
    protected String _pattern = "";
    protected Boolean _required = false;
    
    // Accessors
        
    
    public FieldValidation setEnum( List<String> value ){
        // TODO With proper compare
        // if ( this._enum == value ) return this;
        this._enum = value;
        setDirty(FIELD_ENUM);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetEnum( List<String> value ){
        if ( value != null ) { this.setEnum( value ); }
        return this;
    }
    public List<String> getEnum(){
        return _enum;
    }
    // List adder
    public FieldValidation addEnum( String value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._enum.add(value);
        setDirty(FIELD_ENUM);
        return this;
    }
    
        
    
    public FieldValidation setErrorCode( Integer value ){
        // TODO With proper compare
        // if ( this._errorCode == value ) return this;
        this._errorCode = value;
        setDirty(FIELD_ERRORCODE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetErrorCode( Integer value ){
        if ( value != null ) { this.setErrorCode( value ); }
        return this;
    }
    public Integer getErrorCode(){
        return _errorCode;
    }
    
        
    
    public FieldValidation setErrorMessage( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._errorMessage == value ) return this;
        this._errorMessage = value;
        setDirty(FIELD_ERRORMESSAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetErrorMessage( String value ){
        if ( value != null ) { this.setErrorMessage( value ); }
        return this;
    }
    public String getErrorMessage(){
        return _errorMessage;
    }
    
        
    
    public FieldValidation setMaxLength( Integer value ){
        // TODO With proper compare
        // if ( this._maxLength == value ) return this;
        this._maxLength = value;
        setDirty(FIELD_MAXLENGTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetMaxLength( Integer value ){
        if ( value != null ) { this.setMaxLength( value ); }
        return this;
    }
    public Integer getMaxLength(){
        return _maxLength;
    }
    
        
    
    public FieldValidation setMinLength( Integer value ){
        // TODO With proper compare
        // if ( this._minLength == value ) return this;
        this._minLength = value;
        setDirty(FIELD_MINLENGTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetMinLength( Integer value ){
        if ( value != null ) { this.setMinLength( value ); }
        return this;
    }
    public Integer getMinLength(){
        return _minLength;
    }
    
        
    
    public FieldValidation setPattern( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._pattern == value ) return this;
        this._pattern = value;
        setDirty(FIELD_PATTERN);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetPattern( String value ){
        if ( value != null ) { this.setPattern( value ); }
        return this;
    }
    public String getPattern(){
        return _pattern;
    }
    
        
    
    public FieldValidation setRequired( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_REQUIRED,value);
        // TODO With proper compare
        // if ( this._required == value ) return this;
        this._required = value;
        setDirty(FIELD_REQUIRED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldValidation safeSetRequired( Boolean value ){
        if ( value != null ) { this.setRequired( value ); }
        return this;
    }
    public Boolean getRequired(){
        return _required;
    }
    @JsonIgnore
    public boolean evalRequired(){
        return _required == null ? false : _required.booleanValue();
    }
    
    
}