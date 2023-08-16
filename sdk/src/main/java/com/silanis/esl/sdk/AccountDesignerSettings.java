package com.silanis.esl.sdk;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDesignerSettings {

    private Boolean send;
    private Boolean done;
    private Boolean settings;
    private Boolean documentVisibility;
    private Boolean addDocument;
    private Boolean editDocument;
    private Boolean deleteDocument;
    private Boolean addSigner;
    private Boolean editRecipient;
    private Boolean rolePickerSender;
    private Boolean saveLayout;
    private Boolean applyLayout;
    private Boolean showSharedLayouts;
    private String defaultSignatureType;

    public Boolean getSend() {
        return send;
    }

    public void setSend(Boolean send) {
        this.send = send;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public Boolean getSettings() {
        return settings;
    }

    public void setSettings(Boolean settings) {
        this.settings = settings;
    }

    public Boolean getDocumentVisibility() {
        return documentVisibility;
    }

    public void setDocumentVisibility(Boolean documentVisibility) {
        this.documentVisibility = documentVisibility;
    }

    public Boolean getAddDocument() {
        return addDocument;
    }

    public void setAddDocument(Boolean addDocument) {
        this.addDocument = addDocument;
    }

    public Boolean getEditDocument() {
        return editDocument;
    }

    public void setEditDocument(Boolean editDocument) {
        this.editDocument = editDocument;
    }

    public Boolean getDeleteDocument() {
        return deleteDocument;
    }

    public void setDeleteDocument(Boolean deleteDocument) {
        this.deleteDocument = deleteDocument;
    }

    public Boolean getAddSigner() {
        return addSigner;
    }

    public void setAddSigner(Boolean addSigner) {
        this.addSigner = addSigner;
    }

    public Boolean getEditRecipient() {
        return editRecipient;
    }

    public void setEditRecipient(Boolean editRecipient) {
        this.editRecipient = editRecipient;
    }

    public Boolean getRolePickerSender() {
        return rolePickerSender;
    }

    public void setRolePickerSender(Boolean rolePickerSender) {
        this.rolePickerSender = rolePickerSender;
    }

    public Boolean getSaveLayout() {
        return saveLayout;
    }

    public void setSaveLayout(Boolean saveLayout) {
        this.saveLayout = saveLayout;
    }

    public Boolean getApplyLayout() {
        return applyLayout;
    }

    public void setApplyLayout(Boolean applyLayout) {
        this.applyLayout = applyLayout;
    }

    public Boolean getShowSharedLayouts() {
        return showSharedLayouts;
    }

    public void setShowSharedLayouts(Boolean showSharedLayouts) {
        this.showSharedLayouts = showSharedLayouts;
    }

    public String getDefaultSignatureType() {
        return defaultSignatureType;
    }

    public void setDefaultSignatureType(String defaultSignatureType) {
        this.defaultSignatureType = defaultSignatureType;
    }
}
