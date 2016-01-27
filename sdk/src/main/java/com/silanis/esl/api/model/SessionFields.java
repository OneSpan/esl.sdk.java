package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SessionFields extends Model
        implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_FIELDS = "fields";

    // Empty Constructor
    public SessionFields ( ) {}

    // Fields
    protected Map<String, String> _fields = null;

    public SessionFields setFields( Map<String, String> value ){
        this._fields = value;
        setDirty(FIELD_FIELDS);
        return this;
    }
    @JsonIgnore
    public SessionFields safeSetFields( Map<String, String> value ){
        if ( value != null ) { this.setFields(value); }
        return this;
    }
    public Map<String, String> getFields(){
        return _fields;
    }
    // Map adder
    public SessionFields addFields( Map value ){
        if (value == null) { throw new IllegalArgumentException("Argument cannot be null"); }
        this._fields.putAll(value);
        setDirty(FIELD_FIELDS);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder fieldStringBuilder = new StringBuilder();
        if(null == _fields) {
            return "";
        }
        for(Map.Entry<String, String> entry : _fields.entrySet()) {
            fieldStringBuilder.append(entry.getKey()).append(": ").append(entry.getValue());
        }
        return fieldStringBuilder.toString();
    }
}
