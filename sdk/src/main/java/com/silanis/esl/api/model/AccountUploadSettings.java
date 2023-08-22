package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUploadSettings extends Model
        implements java.io.Serializable{

    @JsonIgnore
    public static final String FIELD_ALLOWED_FILE_TYPES = "allowedFileTypes";
    protected List<String> _allowedFileTypes;

    public AccountUploadSettings() {
        /* Empty */
    }

    public AccountUploadSettings setAllowedFileTypes(List<String> value ){
        SchemaSanitizer.throwOnNull(FIELD_ALLOWED_FILE_TYPES, value);
        this._allowedFileTypes = value;
        setDirty(FIELD_ALLOWED_FILE_TYPES);
        return this;
    }

    @JsonIgnore
    public AccountUploadSettings safeSetSend(List<String> value ){
        if ( value != null ) {
            this.setAllowedFileTypes(value);
        }
        return this;
    }

    public List<String> getAllowedFileTypes(){
        return _allowedFileTypes;
    }

    @JsonIgnore
    public List<String> evalAllowedFileTypes(){
        return _allowedFileTypes == null ? Arrays.asList("") : _allowedFileTypes;
    }

}
