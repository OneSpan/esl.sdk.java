package com.silanis.awsng.web.rest.model;
//
import com.fasterxml.jackson.annotation.*;
import java.util.List;
import java.util.ArrayList;
import com.silanis.awsng.web.rest.util.SchemaSanitizer;
@JsonIgnoreProperties(ignoreUnknown=true)
public class ExtractAnchor extends Model
      implements java.io.Serializable, IExtractAnchor
{
    
    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_ANCHORPOINT = "anchorPoint";
    @JsonIgnore
    public static final String FIELD_CHARACTERINDEX = "characterIndex";
    @JsonIgnore
    public static final String FIELD_HEIGHT = "height";
    @JsonIgnore
    public static final String FIELD_INDEX = "index";
    @JsonIgnore
    public static final String FIELD_LEFTOFFSET = "leftOffset";
    @JsonIgnore
    public static final String FIELD_TEXT = "text";
    @JsonIgnore
    public static final String FIELD_TOPOFFSET = "topOffset";
    @JsonIgnore
    public static final String FIELD_WIDTH = "width";
    
    // Empty Constructor
    public ExtractAnchor ( ) {}
    
    // Fields
    protected String _anchorPoint = "TOPLEFT";
    protected Integer _characterIndex;
    protected Integer _height;
    protected Integer _index;
    protected Integer _leftOffset;
    protected String _text = "";
    protected Integer _topOffset;
    protected Integer _width;
    
    // Accessors
        
    
    public ExtractAnchor setAnchorPoint( String value ){
        SchemaSanitizer.throwOnNull(FIELD_ANCHORPOINT,value);
        SchemaSanitizer.throwIfOutsideConstraints(FIELD_ANCHORPOINT,value, "TOPLEFT,TOPRIGHT,BOTTOMLEFT,BOTTOMRIGHT" );
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._anchorPoint == value ) return this;
        this._anchorPoint = value;
        setDirty(FIELD_ANCHORPOINT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetAnchorPoint( String value ){
        if ( value != null ) { this.setAnchorPoint( value ); }
        return this;
    }
    public String getAnchorPoint(){
        return _anchorPoint;
    }
    
        
    
    public ExtractAnchor setCharacterIndex( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_CHARACTERINDEX,value);
        // TODO With proper compare
        // if ( this._characterIndex == value ) return this;
        this._characterIndex = value;
        setDirty(FIELD_CHARACTERINDEX);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetCharacterIndex( Integer value ){
        if ( value != null ) { this.setCharacterIndex( value ); }
        return this;
    }
    public Integer getCharacterIndex(){
        return _characterIndex;
    }
    
        
    
    public ExtractAnchor setHeight( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_HEIGHT,value);
        // TODO With proper compare
        // if ( this._height == value ) return this;
        this._height = value;
        setDirty(FIELD_HEIGHT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetHeight( Integer value ){
        if ( value != null ) { this.setHeight( value ); }
        return this;
    }
    public Integer getHeight(){
        return _height;
    }
    
        
    
    public ExtractAnchor setIndex( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_INDEX,value);
        // TODO With proper compare
        // if ( this._index == value ) return this;
        this._index = value;
        setDirty(FIELD_INDEX);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetIndex( Integer value ){
        if ( value != null ) { this.setIndex( value ); }
        return this;
    }
    public Integer getIndex(){
        return _index;
    }
    
        
    
    public ExtractAnchor setLeftOffset( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_LEFTOFFSET,value);
        // TODO With proper compare
        // if ( this._leftOffset == value ) return this;
        this._leftOffset = value;
        setDirty(FIELD_LEFTOFFSET);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetLeftOffset( Integer value ){
        if ( value != null ) { this.setLeftOffset( value ); }
        return this;
    }
    public Integer getLeftOffset(){
        return _leftOffset;
    }
    
        
    
    public ExtractAnchor setText( String value ){
        SchemaSanitizer.throwOnNull(FIELD_TEXT,value);
        value = SchemaSanitizer.trim(value);
        // TODO With proper compare
        // if ( this._text == value ) return this;
        this._text = value;
        setDirty(FIELD_TEXT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetText( String value ){
        if ( value != null ) { this.setText( value ); }
        return this;
    }
    public String getText(){
        return _text;
    }
    
        
    
    public ExtractAnchor setTopOffset( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_TOPOFFSET,value);
        // TODO With proper compare
        // if ( this._topOffset == value ) return this;
        this._topOffset = value;
        setDirty(FIELD_TOPOFFSET);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetTopOffset( Integer value ){
        if ( value != null ) { this.setTopOffset( value ); }
        return this;
    }
    public Integer getTopOffset(){
        return _topOffset;
    }
    
        
    
    public ExtractAnchor setWidth( Integer value ){
        SchemaSanitizer.throwOnNull(FIELD_WIDTH,value);
        // TODO With proper compare
        // if ( this._width == value ) return this;
        this._width = value;
        setDirty(FIELD_WIDTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public ExtractAnchor safeSetWidth( Integer value ){
        if ( value != null ) { this.setWidth( value ); }
        return this;
    }
    public Integer getWidth(){
        return _width;
    }
    
    
}