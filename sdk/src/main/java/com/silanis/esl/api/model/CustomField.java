package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomField extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_REQUIRED = "required";
    @JsonIgnore
    public static final String FIELD_TRANSLATIONS = "translations";
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public CustomField ( ) {}
    
    // Fields
    protected Boolean _required = false;
    protected List<Translation> _translations = new ArrayList<Translation>();
    protected String _value = "";
    
    // Accessors
        
    
    @Override
    public CustomField setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CustomField safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public CustomField setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CustomField safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public CustomField setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CustomField safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public CustomField setRequired( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_REQUIRED,value);
        // TODO With proper compare
        // if ( this._required == value ) return this;
        this._required = value;
        setDirty(FIELD_REQUIRED);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CustomField safeSetRequired( Boolean value ){
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
    
        
    
    public CustomField setTranslations( List<Translation> value ){
        SchemaSanitizer.throwOnNull(FIELD_TRANSLATIONS,value);
        // TODO With proper compare
        // if ( this._translations == value ) return this;
        this._translations = value;
        setDirty(FIELD_TRANSLATIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CustomField safeSetTranslations( List<Translation> value ){
        if ( value != null ) { this.setTranslations( value ); }
        return this;
    }
    public List<Translation> getTranslations(){
        return _translations;
    }
    // List adder
    public CustomField addTranslation( Translation value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._translations.add(value);
        setDirty(FIELD_TRANSLATIONS);
        return this;
    }
    
        
    
    public CustomField setValue( String value ){
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._value == value ) return this;
        this._value = value;
        setDirty(FIELD_VALUE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public CustomField safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    public String getValue(){
        return _value;
    }
    
    
}