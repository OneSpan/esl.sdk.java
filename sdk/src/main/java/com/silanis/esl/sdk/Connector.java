package com.silanis.esl.sdk;

public enum Connector {
    TRUST_VAULT(true, false), SFTP(false, false), IPAAS(false, false);

    private final boolean blockchain;
    private final boolean proofEmail;

    Connector(boolean blockchain, boolean proofEmail) {
        this.blockchain = blockchain;
        this.proofEmail = proofEmail;
    }

    public boolean isBlockchain() {
        return blockchain;
    }

    public boolean isProofEmail() {
        return proofEmail;
    }

    public String getValue() {
        return this.name();
    }
}
