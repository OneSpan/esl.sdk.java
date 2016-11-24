package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 11/23/16.
 */
public class DocumentVisibilityConfiguration {

    private String documentUid;
    private List<String> signerIds = new ArrayList<String>();

    public String getDocumentUid() {
        return documentUid;
    }

    public void setDocumentUid(String documentUid) {
        this.documentUid = documentUid;
    }

    public List<String> getSignerIds() {
        return signerIds;
    }

    public void setSignerIds(List<String> signerIds) {
        this.signerIds = signerIds;
    }

    public void addSignerId(String signerId) {
        if(signerIds == null)
            this.signerIds = new ArrayList<String>();

        this.signerIds.add(signerId);
    }
}
