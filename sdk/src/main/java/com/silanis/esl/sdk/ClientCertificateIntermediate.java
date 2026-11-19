package com.silanis.esl.sdk;

import java.util.Date;

/**
 * The OneSpan intermediate CA that issues the client certificate presented on mTLS callbacks.
 *
 * <p>Add {@link #getChainPem()} to the callback endpoint's trust store. Every other property describes the
 * intermediate itself; {@code status} is {@code VALID}, {@code EXPIRED} or {@code NOT_YET_VALID}.
 */
public class ClientCertificateIntermediate {

    private String chainPem;
    private String subject;
    private String issuer;
    private String serialNumber;
    private String sha256Fingerprint;
    private Date notBefore;
    private Date notAfter;
    private String keyAlgorithm;
    private String signatureAlgorithm;
    private String status;

    public String getChainPem() {
        return chainPem;
    }

    public void setChainPem(String chainPem) {
        this.chainPem = chainPem;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSha256Fingerprint() {
        return sha256Fingerprint;
    }

    public void setSha256Fingerprint(String sha256Fingerprint) {
        this.sha256Fingerprint = sha256Fingerprint;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public void setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
    }

    public String getKeyAlgorithm() {
        return keyAlgorithm;
    }

    public void setKeyAlgorithm(String keyAlgorithm) {
        this.keyAlgorithm = keyAlgorithm;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
