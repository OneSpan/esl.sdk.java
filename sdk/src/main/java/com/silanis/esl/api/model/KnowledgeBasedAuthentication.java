package com.silanis.esl.api.model;
//

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.util.SchemaSanitizer;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KnowledgeBasedAuthentication extends Model
        implements java.io.Serializable {

    // Dirty Flag Constants
    @JsonIgnore
    public static final String FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS = "knowledgeBasedAuthenticationStatus";

    @JsonIgnore
    public static final String FIELD_SIGNERINFORMATIONFORLEXISNEXIS = "signerInformationForLexisNexis";
    // Fields
    protected String _knowledgeBasedAuthenticationStatus = "NOT_YET_ATTEMPTED";
    protected SignerInformationForLexisNexis _signerInformationForLexisNexis = null;

    // Empty Constructor
    public KnowledgeBasedAuthentication() {
    }


    // Accessors

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetKnowledgeBasedAuthenticationStatus(String value) {
        if (value != null) {
            this.setKnowledgeBasedAuthenticationStatus(value);
        }
        return this;
    }

    public String getKnowledgeBasedAuthenticationStatus() {
        return _knowledgeBasedAuthenticationStatus;
    }

    public KnowledgeBasedAuthentication setKnowledgeBasedAuthenticationStatus(String value) {
        SchemaSanitizer.throwOnNull(FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS, value);
        // TODO With proper compare
        // if ( this._knowledgeBasedAuthenticationStatus == value ) return this;
        this._knowledgeBasedAuthenticationStatus = value;
        setDirty(FIELD_KNOWLEDGEBASEDAUTHENTICATIONSTATUS);
        return this;
    }

    // Used internally by aws. Invokes a the corresponding setter if the value is not null
    @JsonIgnore
    public KnowledgeBasedAuthentication safeSetSignerInformationForLexisNexis(SignerInformationForLexisNexis value) {
        if (value != null) {
            this.setSignerInformationForLexisNexis(value);
        }
        return this;
    }

    public SignerInformationForLexisNexis getSignerInformationForLexisNexis() {
        return _signerInformationForLexisNexis;
    }

    public KnowledgeBasedAuthentication setSignerInformationForLexisNexis(SignerInformationForLexisNexis value) {
        // TODO With proper compare
        this._signerInformationForLexisNexis = value;
        setDirty(FIELD_SIGNERINFORMATIONFORLEXISNEXIS);
        return this;
    }
}