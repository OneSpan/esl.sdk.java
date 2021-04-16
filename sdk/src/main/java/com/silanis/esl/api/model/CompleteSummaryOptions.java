package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown=true)
public class CompleteSummaryOptions extends Model
        implements java.io.Serializable
{

    @JsonIgnore
    public static final String FIELD_TITLE = "title";
    @JsonIgnore
    public static final String FIELD_MESSAGE = "message";
    @JsonIgnore
    public static final String FIELD_DOWNLOAD = "download";
    @JsonIgnore
    public static final String BUTTON_REVIEW = "review";
    @JsonIgnore
    public static final String BUTTON_CONTINUE = "continue";
    @JsonIgnore
    public static final String FIELD_DOCUMENTSECTION = "documentSection";
    @JsonIgnore
    public static final String FIELD_UPLOADSECTION = "uploadSection";

    // Empty Constructor
    public CompleteSummaryOptions() {}

    protected Boolean _title = true;
    protected Boolean _message = true;
    protected Boolean _download = true;
    protected Boolean _review = true;
    protected Boolean _continue = true;
    protected Boolean _documentSection = true;
    protected Boolean _uploadSection = true;

    public CompleteSummaryOptions setTitle( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_TITLE, value);
        this._title = value;
        setDirty(FIELD_TITLE);
        return this;
    }
    @JsonIgnore
    public CompleteSummaryOptions safeSetTitle( Boolean value ){
        if ( value != null ) { this.setTitle( value ); }
        return this;
    }
    public Boolean getTitle(){
        return _title;
    }
    @JsonIgnore
    public boolean evalTitle(){
        return _title == null ? true : _title.booleanValue();
    }

    public CompleteSummaryOptions setMessage( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_MESSAGE, value);
        this._message = value;
        setDirty(FIELD_MESSAGE);
        return this;
    }
    @JsonIgnore
    public CompleteSummaryOptions safeSetMessage( Boolean value ){
        if ( value != null ) { this.setMessage( value ); }
        return this;
    }
    public Boolean getMessage(){
        return _message;
    }
    @JsonIgnore
    public boolean evalMessage(){
        return _message == null ? true : _message.booleanValue();
    }

    public CompleteSummaryOptions setDownload( Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DOWNLOAD, value);
        this._download = value;
        setDirty(FIELD_DOWNLOAD);
        return this;
    }
    @JsonIgnore
    public CompleteSummaryOptions safeSetDownload( Boolean value ){
        if ( value != null ) { this.setDownload( value ); }
        return this;
    }
    public Boolean getDownload(){
        return _download;
    }
    @JsonIgnore
    public boolean evalDownload(){
        return _download == null ? true : _download.booleanValue();
    }

    public CompleteSummaryOptions setReview( Boolean value ){
        SchemaSanitizer.throwOnNull(BUTTON_REVIEW, value);
        this._review = value;
        setDirty(BUTTON_REVIEW);
        return this;
    }
    @JsonIgnore
    public CompleteSummaryOptions safeSetReview( Boolean value ){
        if ( value != null ) { this.setReview( value ); }
        return this;
    }
    public Boolean getReview(){
        return _review;
    }
    @JsonIgnore
    public boolean evalReview(){
        return _review == null ? true : _review.booleanValue();
    }

    public CompleteSummaryOptions setContinue( Boolean value ){
        SchemaSanitizer.throwOnNull(BUTTON_CONTINUE, value);
        this._continue = value;
        setDirty(BUTTON_CONTINUE);
        return this;
    }
    @JsonIgnore
    public CompleteSummaryOptions safeSetContinue( Boolean value ){
        if ( value != null ) { this.setContinue( value ); }
        return this;
    }
    public Boolean getContinue(){
        return _continue;
    }
    @JsonIgnore
    public boolean evalContinue(){
        return _continue == null ? true : _continue.booleanValue();
    }

    @JsonIgnore
    public CompleteSummaryOptions safeSetDocumentSection(Boolean value) {
        if (value != null) {
            this.setDocumentSection(value);
        }
        return this;
    }

    public Boolean getDocumentSection() {
        return _documentSection;
    }

    public CompleteSummaryOptions setDocumentSection(Boolean value) {
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
    public CompleteSummaryOptions safeSetUploadSection(Boolean value) {
        if (value != null) {
            this.setUploadSection(value);
        }
        return this;
    }

    public Boolean getUploadSection() {
        return _uploadSection;
    }

    public CompleteSummaryOptions setUploadSection(Boolean value) {
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