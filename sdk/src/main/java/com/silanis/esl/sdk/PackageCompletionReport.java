package com.silanis.esl.sdk;

import com.silanis.esl.api.model.PackageStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lena on 2014-05-29.
 */
public class PackageCompletionReport {
    private Date created;
    private List<DocumentsCompletionReport> documents = new ArrayList<DocumentsCompletionReport>();
    private String id;
    private String name;
    private List<SignersCompletionReport> signers = new ArrayList<SignersCompletionReport>();
    private PackageStatus packageStatus;
    private boolean trashed;

    public PackageCompletionReport(String name) {
        this.name = name;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<DocumentsCompletionReport> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DocumentsCompletionReport> documents) {
        this.documents = documents;
    }

    public void addDocument(DocumentsCompletionReport document) {
        this.documents.add(document);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SignersCompletionReport> getSigners() {
        return signers;
    }

    public void setSigners(List<SignersCompletionReport> signers) {
        this.signers = signers;
    }

    public void addSigner(SignersCompletionReport signer) {
        this.signers.add(signer);
    }

    public PackageStatus getPackageStatus() {
        return packageStatus;
    }

    public void setPackageStatus(PackageStatus packageStatus) {
        this.packageStatus = packageStatus;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }
}
