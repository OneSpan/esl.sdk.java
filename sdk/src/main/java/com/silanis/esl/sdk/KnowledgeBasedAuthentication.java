package com.silanis.esl.sdk;

public class KnowledgeBasedAuthentication {

    private KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus = KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
    private SignerInformationForEquifaxCanada signerInformationForEquifaxCanada = null;
    private SignerInformationForEquifaxUSA signerInformationForEquifaxUSA = null;

    private SignerInformationForLexisNexis signerInformationForLexisNexis = null;

    public KnowledgeBasedAuthentication(){
    }

    public KnowledgeBasedAuthenticationStatus getKnowledgeBasedAuthenticationStatus() {
        return knowledgeBasedAuthenticationStatus;
    }

    public void setKnowledgeBasedAuthenticationStatus(KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus) {
        this.knowledgeBasedAuthenticationStatus = knowledgeBasedAuthenticationStatus;
    }

    public SignerInformationForEquifaxCanada getSignerInformationForEquifaxCanada() {
        return signerInformationForEquifaxCanada;
    }

    public void setSignerInformationForEquifaxCanada(SignerInformationForEquifaxCanada signerInformationForEquifaxCanada) {
        this.signerInformationForEquifaxCanada = signerInformationForEquifaxCanada;
    }

    public SignerInformationForEquifaxUSA getSignerInformationForEquifaxUSA() {
        return signerInformationForEquifaxUSA;
    }

    public void setSignerInformationForEquifaxUSA(SignerInformationForEquifaxUSA signerInformationForEquifaxUSA) {
        this.signerInformationForEquifaxUSA = signerInformationForEquifaxUSA;
    }

    public SignerInformationForLexisNexis getSignerInformationForLexisNexis() {
        return signerInformationForLexisNexis;
    }

    public void setSignerInformationForLexisNexis(SignerInformationForLexisNexis signerInformationForLexisNexis) {
        this.signerInformationForLexisNexis = signerInformationForLexisNexis;
    }
}

