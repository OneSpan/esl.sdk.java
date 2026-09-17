package com.silanis.esl.api.model;
//
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class FieldClickableArea extends Model
      implements java.io.Serializable
{

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_WIDTH = "width";
    @JsonIgnore
    public static final String FIELD_HEIGHT = "height";
    @JsonIgnore
    public static final String FIELD_ALIGNMENT = "alignment";

    // Empty Constructor
    public FieldClickableArea ( ) {}

    // Fields
    protected Double _width = null;
    protected Double _height = null;
    protected String _alignment = null;

    // Accessors

    public FieldClickableArea setWidth( Double value ){
        this._width = value;
        setDirty(FIELD_WIDTH);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldClickableArea safeSetWidth( Double value ){
        if ( value != null ) { this.setWidth( value ); }
        return this;
    }
    public Double getWidth(){
        return _width;
    }



    public FieldClickableArea setHeight( Double value ){
        this._height = value;
        setDirty(FIELD_HEIGHT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldClickableArea safeSetHeight( Double value ){
        if ( value != null ) { this.setHeight( value ); }
        return this;
    }
    public Double getHeight(){
        return _height;
    }



    public FieldClickableArea setAlignment( String value ){
        this._alignment = value;
        setDirty(FIELD_ALIGNMENT);
        return this;
    }
    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public FieldClickableArea safeSetAlignment( String value ){
        if ( value != null ) { this.setAlignment( value ); }
        return this;
    }
    public String getAlignment(){
        return _alignment;
    }
}
