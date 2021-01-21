package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OverviewOptions extends Model
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    @JsonIgnore
    public static final String FIELD_BODY = "body";
    @JsonIgnore
    public static final String FIELD_DOCUMENTSECTION = "documentSection";
    @JsonIgnore
    public static final String FIELD_UPLOADSECTION = "uploadSection";

    protected Boolean _title = true;
    protected Boolean _body = true;
    protected Boolean _documentSection = true;
    protected Boolean _uploadSection = true;

    // Empty Constructor
    public OverviewOptions() {
        // Do nothing
    }

    @JsonIgnore
    public OverviewOptions safeSetTitle(Boolean value) {
        if (value != null) {
            this.setTitle(value);
        }
        return this;
    }

    public Boolean getTitle() {
        return _title;
    }

    public OverviewOptions setTitle(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_TITLE, value);
        this._title = value;
        setDirty(FIELD_TITLE);
        return this;
    }

    @JsonIgnore
    public boolean evalTitle() {
        return _title == null || _title.booleanValue();
    }

    @JsonIgnore
    public OverviewOptions safeSetBody(Boolean value) {
        if (value != null) {
            this.setBody(value);
        }
        return this;
    }

    public Boolean getBody() {
        return _body;
    }

    public OverviewOptions setBody(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_BODY, value);
        this._body = value;
        setDirty(FIELD_BODY);
        return this;
    }

    @JsonIgnore
    public boolean evalBody() {
        return _body == null || _body.booleanValue();
    }

    @JsonIgnore
    public OverviewOptions safeSetDocumentSection(Boolean value) {
        if (value != null) {
            this.setDocumentSection(value);
        }
        return this;
    }

    public Boolean getDocumentSection() {
        return _documentSection;
    }

    public OverviewOptions setDocumentSection(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENTSECTION, value);
        this._documentSection = value;
        setDirty(FIELD_DOCUMENTSECTION);
        return this;
    }

    @JsonIgnore
    public boolean evalDocumentSection() {
        return _documentSection == null || _documentSection.booleanValue();
    }

    @JsonIgnore
    public OverviewOptions safeSetUploadSection(Boolean value) {
        if (value != null) {
            this.setUploadSection(value);
        }
        return this;
    }

    public Boolean getUploadSection() {
        return _uploadSection;
    }

    public OverviewOptions setUploadSection(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_UPLOADSECTION, value);
        this._uploadSection = value;
        setDirty(FIELD_UPLOADSECTION);
        return this;
    }

    @JsonIgnore
    public boolean evalUploadSection() {
        return _uploadSection == null || _uploadSection.booleanValue();
    }
}