package com.silanis.esl.sdk;

import java.io.Serializable;

public class TransactionRetention implements Serializable {

    private static final long serialVersionUID = 2088031049767010920L;
    private Integer draft;
    private Integer sent;
    private Integer completed;
    private Integer archived;
    private Integer declined;
    private Integer optedOut;
    private Integer expired;
    private Integer lifetimeTotal;
    private Integer lifetimeUntilCompletion;

    public TransactionRetention() {}

    public TransactionRetention(Integer draft, Integer sent, Integer completed, Integer archived,
                                Integer declined, Integer optedOut, Integer expired) {
        this.draft = draft;
        this.sent = sent;
        this.completed = completed;
        this.archived = archived;
        this.declined = declined;
        this.optedOut = optedOut;
        this.expired = expired;

    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public Integer getSent() {
        return sent;
    }

    public void setSent(Integer sent) {
        this.sent = sent;
    }

    public Integer getCompleted() {
        return completed;
    }

    public void setCompleted(Integer completed) {
        this.completed = completed;
    }

    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    public Integer getDeclined() {
        return declined;
    }

    public void setDeclined(Integer declined) {
        this.declined = declined;
    }

    public Integer getOptedOut() {
        return optedOut;
    }

    public void setOptedOut(Integer optedOut) {
        this.optedOut = optedOut;
    }

    public Integer getExpired() {
        return expired;
    }

    public void setExpired(Integer expired) {
        this.expired = expired;
    }

    public Integer getLifetimeTotal() {
        return lifetimeTotal;
    }

    public void setLifetimeTotal(Integer lifetimeTotal) {
        this.lifetimeTotal = lifetimeTotal;
    }

    public Integer getLifetimeUntilCompletion() {
        return lifetimeUntilCompletion;
    }
    
    public void setLifetimeUntilCompletion(Integer lifetimeUntilCompletion) {
        this.lifetimeUntilCompletion = lifetimeUntilCompletion;
    }
    
}
