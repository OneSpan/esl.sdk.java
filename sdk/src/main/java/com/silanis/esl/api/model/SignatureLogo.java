package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SignatureLogo extends Model
        implements java.io.Serializable
{

    @JsonIgnore
    public static final String FIELD_IMAGE = "image";

    // Empty Constructor
    public SignatureLogo() {}

    protected String _image;

    public SignatureLogo setImage(String value ){
        SchemaSanitizer.throwOnNull(FIELD_IMAGE, value);
        this._image = value;
        setDirty(FIELD_IMAGE);
        return this;
    }
    @JsonIgnore
    public SignatureLogo safeSetImage(String value ){
        if ( value != null ) { this.setImage( value ); }
        return this;
    }
    public String getImage(){
        return _image;
    }

}