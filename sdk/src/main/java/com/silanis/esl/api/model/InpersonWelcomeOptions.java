package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class InpersonWelcomeOptions extends Model
        implements java.io.Serializable {

    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    @JsonIgnore
    public static final String FIELD_BODY = "body";
    @JsonIgnore
    public static final String FIELD_RECIPIENTNAME = "recipientName";
    @JsonIgnore
    public static final String FIELD_RECIPIENTEMAIL = "recipientEmail";
    @JsonIgnore
    public static final String FIELD_RECIPIENTACTIONREQUIRED = "recipientActionRequired";
    @JsonIgnore
    public static final String FIELD_RECIPIENTROLE = "recipientRole";
    @JsonIgnore
    public static final String FIELD_RECIPIENTSTATUS = "recipientStatus";
    protected Boolean _title = true;
    protected Boolean _body = true;
    protected Boolean _recipientName = true;
    protected Boolean _recipientEmail = true;
    protected Boolean _recipientActionRequired = true;
    protected Boolean _recipientRole = true;
    protected Boolean _recipientStatus = true;
    // Empty Constructor
    public InpersonWelcomeOptions() {
        // Do nothing
    }

    @JsonIgnore
    public InpersonWelcomeOptions safeSetTitle(Boolean value) {
        if (value != null) {
            this.setTitle(value);
        }
        return this;
    }

    public Boolean getTitle() {
        return _title;
    }

    public InpersonWelcomeOptions setTitle(Boolean value) {
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
    public InpersonWelcomeOptions safeSetBody(Boolean value) {
        if (value != null) {
            this.setBody(value);
        }
        return this;
    }

    public Boolean getBody() {
        return _body;
    }

    public InpersonWelcomeOptions setBody(Boolean value) {
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
    public InpersonWelcomeOptions safeSetRecipientName(Boolean value) {
        if (value != null) {
            this.setRecipientName(value);
        }
        return this;
    }

    public Boolean getRecipientName() {
        return _recipientName;
    }

    public InpersonWelcomeOptions setRecipientName(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_RECIPIENTNAME, value);
        this._recipientName = value;
        setDirty(FIELD_RECIPIENTNAME);
        return this;
    }

    @JsonIgnore
    public boolean evalRecipientName() {
        return _recipientName == null || _recipientName.booleanValue();
    }

    @JsonIgnore
    public InpersonWelcomeOptions safeSetRecipientEmail(Boolean value) {
        if (value != null) {
            this.setRecipientEmail(value);
        }
        return this;
    }

    public Boolean getRecipientEmail() {
        return _recipientEmail;
    }

    public InpersonWelcomeOptions setRecipientEmail(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_RECIPIENTEMAIL, value);
        this._recipientEmail = value;
        setDirty(FIELD_RECIPIENTEMAIL);
        return this;
    }

    @JsonIgnore
    public boolean evalRecipientEmail() {
        return _recipientEmail == null || _recipientEmail.booleanValue();
    }

    @JsonIgnore
    public InpersonWelcomeOptions safeSetRecipientActionRequired(Boolean value) {
        if (value != null) {
            this.setRecipientActionRequired(value);
        }
        return this;
    }

    public Boolean getRecipientActionRequired() {
        return _recipientActionRequired;
    }

    public InpersonWelcomeOptions setRecipientActionRequired(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_RECIPIENTACTIONREQUIRED, value);
        this._recipientActionRequired = value;
        setDirty(FIELD_RECIPIENTACTIONREQUIRED);
        return this;
    }

    @JsonIgnore
    public boolean evalRecipientActionRequired() {
        return _recipientActionRequired == null || _recipientActionRequired.booleanValue();
    }

    @JsonIgnore
    public InpersonWelcomeOptions safeSetRecipientRole(Boolean value) {
        if (value != null) {
            this.setRecipientRole(value);
        }
        return this;
    }

    public Boolean getRecipientRole() {
        return _recipientRole;
    }

    public InpersonWelcomeOptions setRecipientRole(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_RECIPIENTROLE, value);
        this._recipientRole = value;
        setDirty(FIELD_RECIPIENTROLE);
        return this;
    }

    @JsonIgnore
    public boolean evalRecipientRole() {
        return _recipientRole == null || _recipientRole.booleanValue();
    }

    @JsonIgnore
    public InpersonWelcomeOptions safeSetRecipientStatus(Boolean value) {
        if (value != null) {
            this.setRecipientStatus(value);
        }
        return this;
    }

    public Boolean getRecipientStatus() {
        return _recipientStatus;
    }

    public InpersonWelcomeOptions setRecipientStatus(Boolean value) {
        SchemaSanitizer.throwOnNull(FIELD_RECIPIENTSTATUS, value);
        this._recipientStatus = value;
        setDirty(FIELD_RECIPIENTSTATUS);
        return this;
    }

    @JsonIgnore
    public boolean evalRecipientStatus() {
        return _recipientStatus == null || _recipientStatus.booleanValue();
    }
}