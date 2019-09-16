package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.Properties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SigningTheme extends Model
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_COLOR = "color";

    // Fields
    protected Properties _color = new Properties();

    // Accessors

    public Properties getColor() {
        return _color;
    }

    public SigningTheme setColor(Properties value) {
        SchemaSanitizer.throwOnNull(FIELD_COLOR, value);
        // TODO With proper compare
        // if ( this._color == value ) return this;
        this._color = value;
        setDirty(FIELD_COLOR);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public SigningTheme safeSetColor(Properties value) {
        if (value != null) {
            this.setColor(value);
        }
        return this;
    }
}