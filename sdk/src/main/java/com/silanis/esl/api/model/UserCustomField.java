package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class UserCustomField extends Entity
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
    public static final String FIELD_TRANSLATIONS = "translations";
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public UserCustomField ( ) {}
    
    // Fields
    protected List<Translation> _translations = null;
    protected String _value = "";
    
    // Accessors
        
    
    @Override
    public UserCustomField setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UserCustomField safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public UserCustomField setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UserCustomField safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public UserCustomField setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UserCustomField safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public UserCustomField setTranslations( List<Translation> value ){
        // TODO With proper compare
        // if ( this._translations == value ) return this;
        this._translations = value;
        setDirty(FIELD_TRANSLATIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public UserCustomField safeSetTranslations( List<Translation> value ){
        if ( value != null ) { this.setTranslations( value ); }
        return this;
    }
    public List<Translation> getTranslations(){
        return _translations;
    }
    // List adder
    public UserCustomField addTranslation( Translation value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._translations.add(value);
        setDirty(FIELD_TRANSLATIONS);
        return this;
    }
    
        
    
    public UserCustomField setValue( String value ){
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
    public UserCustomField safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    public String getValue(){
        return _value;
    }
    
    
}