package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown=true)
public class Field extends Entity
      implements java.io.Serializable
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_BINDING = "binding";
    @JsonIgnore
    public static final String FIELD_DATA = "data";
    @JsonIgnore
    public static final String FIELD_EXTRACT = "extract";
    @JsonIgnore
    public static final String FIELD_EXTRACTANCHOR = "extractAnchor";
    @JsonIgnore
    public static final String FIELD_HEIGHT = "height";
    @JsonIgnore
    public static final String FIELD_ID = "id";
    @JsonIgnore
    public static final String FIELD_LEFT = "left";
    @JsonIgnore
    public static final String FIELD_NAME = "name";
    @JsonIgnore
    public static final String FIELD_PAGE = "page";
    @JsonIgnore
    public static final String FIELD_SUBTYPE = "subtype";
    @JsonIgnore
    public static final String FIELD_TOP = "top";
    @JsonIgnore
    public static final String FIELD_TYPE = "type";
    @JsonIgnore
    public static final String FIELD_VALIDATION = "validation";
    @JsonIgnore
    public static final String FIELD_VALUE = "value";
    @JsonIgnore
    public static final String FIELD_WIDTH = "width";
    @JsonIgnore
    public static final String FIELD_FONT_SIZE = "fontSize";
    
    // Empty Constructor
    public Field ( ) {}
    
    // Fields
    protected String _binding = null;
    protected Boolean _extract = false;
    protected ExtractAnchor _extractAnchor = null;
    protected Double _height = 0.0;
    protected Double _left = 0.0;
    protected Integer _page = 0;
    protected String _subtype = "FULLNAME";
    protected Double _top = 0.0;
    protected String _type = "SIGNATURE";
    protected FieldValidation _validation = null;
    protected String _value = "";
    protected Double _width = 0.0;
    protected Integer _fontSize = null;
    
    // Accessors
        
    
    public Field setBinding( String value ){
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._binding == value ) return this;
        this._binding = value;
        setDirty(FIELD_BINDING);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetBinding( String value ){
        if ( value != null ) { this.setBinding( value ); }
        return this;
    }
    public String getBinding(){
        return _binding;
    }
    
        
    
    @Override
    public Field setData( Map<String, Object> value ){
        super.setData(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetData( Map<String, Object> value ){
        if ( value != null ) { this.setData( value ); }
        return this;
    }
    
        
    
    public Field setExtract( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EXTRACT,value);
        // TODO With proper compare
        // if ( this._extract == value ) return this;
        this._extract = value;
        setDirty(FIELD_EXTRACT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetExtract( Boolean value ){
        if ( value != null ) { this.setExtract( value ); }
        return this;
    }
    public Boolean getExtract(){
        return _extract;
    }
    @JsonIgnore
    public boolean evalExtract(){
        return _extract == null ? false : _extract.booleanValue();
    }
    
        
    
    public Field setExtractAnchor( ExtractAnchor value ){
        // TODO With proper compare
        // if ( this._extractAnchor == value ) return this;
        this._extractAnchor = value;
        setDirty(FIELD_EXTRACTANCHOR);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetExtractAnchor( ExtractAnchor value ){
        if ( value != null ) { this.setExtractAnchor( value ); }
        return this;
    }
    public ExtractAnchor getExtractAnchor(){
        return _extractAnchor;
    }
    
        
    
    public Field setHeight( Double value ){
        // TODO With proper compare
        // if ( this._height == value ) return this;
        this._height = value;
        setDirty(FIELD_HEIGHT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetHeight( Double value ){
        if ( value != null ) { this.setHeight( value ); }
        return this;
    }
    public Double getHeight(){
        return _height;
    }
    
        
    
    @Override
    public Field setId( String value ){
        super.setId(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetId( String value ){
        if ( value != null ) { this.setId( value ); }
        return this;
    }
    
        
    
    public Field setLeft( Double value ){
        // TODO With proper compare
        // if ( this._left == value ) return this;
        this._left = value;
        setDirty(FIELD_LEFT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetLeft( Double value ){
        if ( value != null ) { this.setLeft( value ); }
        return this;
    }
    public Double getLeft(){
        return _left;
    }
    
        
    
    @Override
    public Field setName( String value ){
        super.setName(value);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetName( String value ){
        if ( value != null ) { this.setName( value ); }
        return this;
    }
    
        
    
    public Field setPage( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_PAGE,value);
        // TODO With proper compare
        // if ( this._page == value ) return this;
        this._page = value;
        setDirty(FIELD_PAGE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetPage( Integer value ){
        if ( value != null ) { this.setPage( value ); }
        return this;
    }
    public Integer getPage(){
        return _page;
    }
    
        
    
    public Field setSubtype( String value ){
        SchemaSanitizer.throwOnNull(FIELD_SUBTYPE,value);
        // TODO With proper compare
        // if ( this._subtype == value ) return this;
        this._subtype = value;
        setDirty(FIELD_SUBTYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetSubtype( String value ){
        if ( value != null ) { this.setSubtype( value ); }
        return this;
    }
    public String getSubtype(){
        return _subtype;
    }
    
        
    
    public Field setTop( Double value ){
        // TODO With proper compare
        // if ( this._top == value ) return this;
        this._top = value;
        setDirty(FIELD_TOP);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetTop( Double value ){
        if ( value != null ) { this.setTop( value ); }
        return this;
    }
    public Double getTop(){
        return _top;
    }
    
        
    
    public Field setType( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TYPE,value);
        // TODO With proper compare
        // if ( this._type == value ) return this;
        this._type = value;
        setDirty(FIELD_TYPE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetType( String value ){
        if ( value != null ) { this.setType( value ); }
        return this;
    }
    public String getType(){
        return _type;
    }
    
        
    
    public Field setValidation( FieldValidation value ){
        // TODO With proper compare
        // if ( this._validation == value ) return this;
        this._validation = value;
        setDirty(FIELD_VALIDATION);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetValidation( FieldValidation value ){
        if ( value != null ) { this.setValidation( value ); }
        return this;
    }
    public FieldValidation getValidation(){
        return _validation;
    }
    
        
    
    public Field setValue( String value ){
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
    public Field safeSetValue( String value ){
        if ( value != null ) { this.setValue( value ); }
        return this;
    }
    public String getValue(){
        return _value;
    }
    
        
    
    public Field setWidth( Double value ){
        // TODO With proper compare
        // if ( this._width == value ) return this;
        this._width = value;
        setDirty(FIELD_WIDTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetWidth( Double value ){
        if ( value != null ) { this.setWidth( value ); }
        return this;
    }
    public Double getWidth(){
        return _width;
    }



    public Field setFontSize( Integer value ){
        this._fontSize = value;
        setDirty(FIELD_FONT_SIZE);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public Field safeSetFontSize( Integer value ){
        if ( value != null ) { this.setFontSize( value ); }
        return this;
    }
    public Integer getFontSize(){
        return _fontSize;
    }
}