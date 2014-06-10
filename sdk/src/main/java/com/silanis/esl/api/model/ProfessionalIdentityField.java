package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import com.silanis.esl.api.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ProfessionalIdentityField extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_CATEGORY = "category";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_TRANSLATIONS = "translations";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    
    // Empty Constructor
    public ProfessionalIdentityField ( ) {}
    
    // Fields
    protected String _category = "";
    protected List<Translation> _translations = null;
    protected String _type = "";
    protected String _value = "";
    
    // Accessors
        
    
    public ProfessionalIdentityField setCategory( String value ){
        SchemaSanitizer.throwOnNull(FIELD_CATEGORY,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._category == value ) return this;
        this._category = value;
        setDirty(FIELD_CATEGORY);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ProfessionalIdentityField safeSetCategory( String value ){
        if ( value != null ) { this.setCategory( value ); }
        return this;
    }
    public String getCategory(){
        return _category;
    }
    
        
    
    @Override
    public ProfessionalIdentityField setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ProfessionalIdentityField safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    @Override
    public ProfessionalIdentityField setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ProfessionalIdentityField safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    @Override
    public ProfessionalIdentityField setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ProfessionalIdentityField safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public ProfessionalIdentityField setTranslations( List<Translation> value ){
        // TODO With proper compare
        // if ( this._translations == value ) return this;
        this._translations = value;
        setDirty(FIELD_TRANSLATIONS);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ProfessionalIdentityField safeSetTranslations( List<Translation> value ){
        if ( value != null ) { this.setTranslations( value ); }
        return this;
    }
    public List<Translation> getTranslations(){
        return _translations;
    }
    // List adder
    public ProfessionalIdentityField addTranslation( Translation value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._translations.add(value);
        setDirty(FIELD_TRANSLATIONS);
        return this;
    }
    
        
    
    public ProfessionalIdentityField setType( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._type == value ) return this;
        this._type = value;
        setDirty(FIELD_TYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ProfessionalIdentityField safeSetType( String value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    public String getType(){
        return _type;
    }
    
        
    
    public ProfessionalIdentityField setValue( String value ){
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
    public ProfessionalIdentityField safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    public String getValue(){
        return _value;
    }
    
    
}