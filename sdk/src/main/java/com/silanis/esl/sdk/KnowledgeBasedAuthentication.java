package com.silanis.esl.sdk;

public class KnowledgeBasedAuthentication {

    private KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus = KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;

    private SignerInformationForLexisNexis signerInformationForLexisNexis = null;

    public KnowledgeBasedAuthentication(){
    }

    public KnowledgeBasedAuthenticationStatus getKnowledgeBasedAuthenticationStatus() {
        return knowledgeBasedAuthenticationStatus;
    }

    public void setKnowledgeBasedAuthenticationStatus(KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus) {
        this.knowledgeBasedAuthenticationStatus = knowledgeBasedAuthenticationStatus;
    }

    public SignerInformationForLexisNexis getSignerInformationForLexisNexis() {
        return signerInformationForLexisNexis;
    }

    public void setSignerInformationForLexisNexis(SignerInformationForLexisNexis signerInformationForLexisNexis) {
        this.signerInformationForLexisNexis = signerInformationForLexisNexis;
    }
}

