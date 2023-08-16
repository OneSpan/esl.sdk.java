package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDesignerSettings extends Model
        implements java.io.Serializable{
    @JsonIgnore
    public static final String FIELD_SEND = "send";
    @JsonIgnore
    public static final String FIELD_DONE = "done";
    @JsonIgnore
    public static final String FIELD_SETTINGS = "settings";
    @JsonIgnore
    public static final String FIELD_DOCUMENT_VISIBILITY = "documentVisibility";
    @JsonIgnore
    public static final String FIELD_ADD_DOCUMENT = "addDocument";
    @JsonIgnore
    public static final String FIELD_EDIT_DOCUMENT = "editDocument";
    @JsonIgnore
    public static final String FIELD_DELETE_DOCUMENT = "deleteDocument";
    @JsonIgnore
    public static final String FIELD_ADD_SIGNER = "addSigner";
    @JsonIgnore
    public static final String FIELD_EDIT_RECIPIENT = "editRecipient";
    @JsonIgnore
    public static final String FIELD_ROLE_PICKER_SENDER = "rolePickerSender";
    @JsonIgnore
    public static final String FIELD_SAVE_LAYOUT = "saveLayout";
    @JsonIgnore
    public static final String FIELD_APPLY_LAYOUT = "applyLayout";
    @JsonIgnore
    public static final String FIELD_SHOW_SHARED_LAYOUTS = "showSharedLayouts";
    @JsonIgnore
    public static final String FIELD_DEFAULT_SIGNATURE_TYPE = "defaultSignatureType";

    protected Boolean _send;
    protected Boolean _done;
    protected Boolean _settings;
    protected Boolean _documentVisibility;
    protected Boolean _addDocument;
    protected Boolean _editDocument;
    protected Boolean _deleteDocument;
    protected Boolean _addSigner;
    protected Boolean _editRecipient;
    protected Boolean _rolePickerSender;
    protected Boolean _saveLayout;
    protected Boolean _applyLayout;
    protected Boolean _showSharedLayouts;
    protected String _defaultSignatureType;


    public AccountDesignerSettings() {
        /* Empty */
    }

    public AccountDesignerSettings setSend(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SEND, value);
        this._send = value;
        setDirty(FIELD_SEND);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetSend(Boolean value ){
        if ( value != null ) {
            this.setSend(value);
        }
        return this;
    }

    public Boolean getSend(){
        return _send;
    }

    @JsonIgnore
    public boolean evalSend(){
        return _send == null ? true : _send.booleanValue();
    }

    public AccountDesignerSettings setDone(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DONE, value);
        this._done = value;
        setDirty(FIELD_DONE);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetDone(Boolean value ){
        if ( value != null ) {
            this.setDone( value );
        }
        return this;
    }

    public Boolean getDone(){
        return _done;
    }

    @JsonIgnore
    public boolean evalDone(){
        return _done == null ? true : _done.booleanValue();
    }

    public AccountDesignerSettings setSettings(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SETTINGS, value);
        this._settings = value;
        setDirty(FIELD_SETTINGS);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetSettings(Boolean value ){
        if ( value != null ) {
            this.setSettings( value );
        }
        return this;
    }

    public Boolean getSettings(){
        return _settings;
    }

    @JsonIgnore
    public boolean evalSettings(){
        return _settings == null ? true : _settings.booleanValue();
    }

    public AccountDesignerSettings setAddDocument(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ADD_DOCUMENT, value);
        this._addDocument = value;
        setDirty(FIELD_ADD_DOCUMENT);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetAddDocument(Boolean value ){
        if ( value != null ) {
            this.setAddDocument( value );
        }
        return this;
    }

    public Boolean getAddDocument(){
        return _addDocument;
    }

    @JsonIgnore
    public boolean evalAddDocument(){
        return _addDocument == null ? true : _addDocument.booleanValue();
    }

    public AccountDesignerSettings setDocumentVisibility(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DOCUMENT_VISIBILITY, value);
        this._documentVisibility = value;
        setDirty(FIELD_DOCUMENT_VISIBILITY);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetDocumentVisibility(Boolean value ){
        if ( value != null ) {
            this.setDocumentVisibility( value );
        }
        return this;
    }

    public Boolean getDocumentVisibility(){
        return _documentVisibility;
    }

    @JsonIgnore
    public boolean evalDocumentVisibility(){
        return _documentVisibility == null ? true : _documentVisibility.booleanValue();
    }

    public AccountDesignerSettings setEditDocument(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EDIT_DOCUMENT, value);
        this._editDocument = value;
        setDirty(FIELD_EDIT_DOCUMENT);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetEditDocument(Boolean value ){
        if ( value != null ) {
            this.setEditDocument( value );
        }
        return this;
    }

    public Boolean getEditDocument(){
        return _editDocument;
    }

    @JsonIgnore
    public boolean evalEditDocument(){
        return _editDocument == null ? true : _editDocument.booleanValue();
    }

    public AccountDesignerSettings setDeleteDocument(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_DELETE_DOCUMENT, value);
        this._deleteDocument = value;
        setDirty(FIELD_DELETE_DOCUMENT);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetDeleteDocument(Boolean value ){
        if ( value != null ) {
            this.setDeleteDocument( value );
        }
        return this;
    }

    public Boolean getDeleteDocument(){
        return _deleteDocument;
    }

    @JsonIgnore
    public boolean evalDeleteDocument(){
        return _deleteDocument == null ? true : _deleteDocument.booleanValue();
    }

    public AccountDesignerSettings setAddSigner(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ADD_SIGNER, value);
        this._addSigner = value;
        setDirty(FIELD_ADD_SIGNER);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetAddSigner(Boolean value ){
        if ( value != null ) {
            this.setAddSigner( value );
        }
        return this;
    }

    public Boolean getAddSigner(){
        return _addSigner;
    }

    @JsonIgnore
    public boolean evalAddSigner(){
        return _addSigner == null ? true : _addSigner.booleanValue();
    }

    public AccountDesignerSettings setEditRecipient(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_EDIT_RECIPIENT, value);
        this._editRecipient = value;
        setDirty(FIELD_EDIT_RECIPIENT);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetEditRecipient(Boolean value ){
        if ( value != null ) {
            this.setEditRecipient( value );
        }
        return this;
    }

    public Boolean getEditRecipient(){
        return _editRecipient;
    }

    @JsonIgnore
    public boolean evalEditRecipient(){
        return _editRecipient == null ? true : _editRecipient.booleanValue();
    }

    public AccountDesignerSettings setRolePickerSender(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_ROLE_PICKER_SENDER, value);
        this._rolePickerSender = value;
        setDirty(FIELD_ROLE_PICKER_SENDER);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetRolePickerSender(Boolean value ){
        if ( value != null ) {
            this.setRolePickerSender( value );
        }
        return this;
    }

    public Boolean getRolePickerSender(){
        return _rolePickerSender;
    }

    @JsonIgnore
    public boolean evalRolePickerSender(){
        return _rolePickerSender == null ? true : _rolePickerSender.booleanValue();
    }

    public AccountDesignerSettings setSaveLayout(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SAVE_LAYOUT, value);
        this._saveLayout = value;
        setDirty(FIELD_SAVE_LAYOUT);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetSaveLayout(Boolean value ){
        if ( value != null ) {
            this.setSaveLayout( value );
        }
        return this;
    }

    public Boolean getSaveLayout(){
        return _saveLayout;
    }

    @JsonIgnore
    public boolean evalSaveLayout(){
        return _saveLayout == null ? true : _saveLayout.booleanValue();
    }

    public AccountDesignerSettings setApplyLayout(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_APPLY_LAYOUT, value);
        this._applyLayout = value;
        setDirty(FIELD_APPLY_LAYOUT);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetApplyLayout(Boolean value ){
        if ( value != null ) {
            this.setApplyLayout( value );
        }
        return this;
    }

    public Boolean getApplyLayout(){
        return _applyLayout;
    }

    @JsonIgnore
    public boolean evalApplyLayout(){
        return _applyLayout == null ? true : _applyLayout.booleanValue();
    }

    public AccountDesignerSettings setShowSharedLayouts(Boolean value ){
        SchemaSanitizer.throwOnNull(FIELD_SHOW_SHARED_LAYOUTS, value);
        this._showSharedLayouts = value;
        setDirty(FIELD_SHOW_SHARED_LAYOUTS);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetShowSharedLayouts(Boolean value ){
        if ( value != null ) {
            this.setShowSharedLayouts( value );
        }
        return this;
    }

    public Boolean getShowSharedLayouts(){
        return _showSharedLayouts;
    }

    @JsonIgnore
    public boolean evalShowSharedLayouts(){
        return _showSharedLayouts == null ? true : _showSharedLayouts.booleanValue();
    }

    public AccountDesignerSettings setDefaultSignatureType(String value ){
        SchemaSanitizer.throwOnNull(FIELD_DEFAULT_SIGNATURE_TYPE, value);
        this._defaultSignatureType = value;
        setDirty(FIELD_DEFAULT_SIGNATURE_TYPE);
        return this;
    }

    @JsonIgnore
    public AccountDesignerSettings safeSetDefaultSignatureType(String value ){
        if ( value != null ) {
            this.setDefaultSignatureType( value );
        }
        return this;
    }

    @JsonIgnore
    public String evalDefaultSignatureType(){
        return _defaultSignatureType == null ? "sign" : _defaultSignatureType;
    }
    public String getDefaultSignatureType(){
        return _defaultSignatureType;
    }

}
