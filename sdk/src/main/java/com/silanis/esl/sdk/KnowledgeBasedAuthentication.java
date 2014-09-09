package com.silanis.esl.sdk;

import com.silanis.esl.api.model.KnowledgeBasedAuthenticationStatus;

/**
 * Created by schoi on 9/8/14.
 */
public class KnowledgeBasedAuthentication {

    private KnowledgeBasedAuthenticationStatus knowledgeBasedAuthenticationStatus = KnowledgeBasedAuthenticationStatus.NOT_YET_ATTEMPTED;
    private SignerInformationForEquifaxCanada signerInformationForEquifaxCanada;
    private SignerInformationForEquifaxUSA signerInformationForEquifaxUSA;

    public KnowledgeBasedAuthentication(){
    }

    public KnowledgeBasedAuthentication(SignerInformationForEquifaxCanada signerInformationForEquifaxCanada, SignerInformationForEquifaxUSA signerInformationForEquifaxUSA) {
        this.signerInformationForEquifaxCanada = signerInformationForEquifaxCanada;
        this.signerInformationForEquifaxUSA = signerInformationForEquifaxUSA;
    }

    public boolean isEquifaxCanada() {
        return signerInformationForEquifaxCanada != null;
    }

    public boolean isEquifaxUSA() {
        return signerInformationForEquifaxUSA != null;
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
}
