package com.silanis.esl.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 12/11/15.
 */
public class SignedDocuments {
    private List<Document> documents = new ArrayList<Document>();

    public List<Document> getDocuments() {
        return documents;
    }

    public SignedDocuments setDocuments(List<Document> documents) {
        this.documents = documents;
        return this;
    }

    public SignedDocuments addDocument(Document document) {
        documents.add(document);
        return this;
    }
}
