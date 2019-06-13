package com.silanis.esl.api.model;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;
import static com.silanis.esl.api.util.SchemaSanitizer.trim;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ReferencedConditions extends Model implements Serializable {

    @JsonIgnore
    public static final String PACKAGE_ID = "packageId";
    @JsonIgnore
    public static final String REF_DOCUMENTS = "documents";

    private String packageId;
    private List<ReferencedDocument> documents;

    public ReferencedConditions setPackageId(String value) {

        throwOnNull(PACKAGE_ID, value);
        value = trim(value);

        this.packageId = value;
        setDirty(PACKAGE_ID);
        return this;
    }

    public String getPackageId() {
        return packageId;
    }

    public ReferencedConditions setDocuments(List<ReferencedDocument> value) {

        throwOnNull(REF_DOCUMENTS, value);

        this.documents = value;
        setDirty(REF_DOCUMENTS);
        return this;
    }

    public List<ReferencedDocument> getDocuments() {
        return documents;
    }
}