package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;
import org.apache.commons.lang3.StringUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SigningLogo extends Model
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_LANGUAGE = "language";

    @JsonIgnore
    public static final String FIELD_IMAGE = "image";

    // Fields
    protected String _language = "";
    protected String _image = "";

    // Accessors

    public String getLanguage() {
        return _language;
    }

    public SigningLogo setLanguage(String language) {
        SchemaSanitizer.throwOnNull(FIELD_LANGUAGE, language);
        this._language = language;
        setDirty(FIELD_LANGUAGE);
        return this;
    }

    @JsonIgnore
    public SigningLogo safeSetLanguage(String language) {
        if (StringUtils.isNotBlank(language)) {
            this.setLanguage(language);
        }
        return this;
    }

    public String getImage() {
        return _image;
    }

    public SigningLogo setImage(String image) {
        SchemaSanitizer.throwOnNull(FIELD_IMAGE, image);
        this._image = image;
        setDirty(FIELD_IMAGE);
        return this;
    }

    @JsonIgnore
    public SigningLogo safeSetImage(String image) {
        if (StringUtils.isNotBlank(image)) {
            this.setImage(image);
        }
        return this;
    }

}