package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

import java.util.List;

/**
 * Created by schoi on 11/23/16.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class DocumentVisibilityConfiguration extends Entity
    implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_DOCUMENTUID = "documentUid";
    @JsonIgnore
    public static final String FIELD_ROLEUIDS = "roleUids";

    // Empty Constructor
    public DocumentVisibilityConfiguration() {
    }

    // Fields
    protected String _documentUid;
    protected List<String> _roleUids;


    public DocumentVisibilityConfiguration setDocumentUid(String value) {
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTUID, value);
        value = SchemaSanitizer.sanitize(value);
        value = SchemaSanitizer.trim(value);
        this._documentUid = value;
        setDirty(FIELD_DOCUMENTUID);
        return this;
    }

    @JsonIgnore
    public DocumentVisibilityConfiguration safeSetDocumentUid(String value) {
        if (value != null) {
            this.setDocumentUid(value);
        }
        return this;
    }

    public String getDocumentUid() {
        return _documentUid;
    }


    public DocumentVisibilityConfiguration setRoleUids(List<String> value) {
        this._roleUids = value;
        setDirty(FIELD_ROLEUIDS);
        return this;
    }

    @JsonIgnore
    public DocumentVisibilityConfiguration safeSetRoleUids(List<String> value) {
        if (value != null) {
            this.setRoleUids(value);
        }
        return this;
    }

    public List<String> getRoleUids() {
        return _roleUids;
    }
}
