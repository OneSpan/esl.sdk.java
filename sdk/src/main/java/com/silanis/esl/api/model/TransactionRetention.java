package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import static com.silanis.esl.api.util.SchemaSanitizer.throwOnNull;

public class TransactionRetention extends Model {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    private static final String FIELD_DRAFT = "draft";
    @JsonIgnore
    private static final String FIELD_SENT = "sent";
    @JsonIgnore
    private static final String FIELD_COMPLETED = "completed";
    @JsonIgnore
    private static final String FIELD_ARCHIVED = "archived";
    @JsonIgnore
    private static final String FIELD_DECLINED = "declined";
    @JsonIgnore
    private static final String FIELD_OPTEDOUT = "optedOut";
    @JsonIgnore
    private static final String FIELD_EXPIRED = "expired";
    @JsonIgnore
    private static final String FIELD_LIFETIMETOTAL = "lifetimeTotal";
    @JsonIgnore
    private static final String FIELD_LIFETIMEUNTILCOMPLETION = "lifetimeUntilCompletion";
    @JsonIgnore
    private static final String FIELD_INCLUDESENT = "includeSent";

    private Integer draft;
    private Integer sent;
    private Integer completed;
    private Integer archived;
    private Integer declined;
    private Integer optedOut;
    private Integer expired;
    private Integer lifetimeTotal;
    private Integer lifetimeUntilCompletion;
    private Boolean includeSent;
    
    public TransactionRetention() {}

    public TransactionRetention(Integer draft, Integer sent, Integer completed, Integer archived,
                                Integer declined, Integer optedOut, Integer expired, Boolean includeSent) {
        this.draft = draft;
        this.sent = sent;
        this.completed = completed;
        this.archived = archived;
        this.declined = declined;
        this.optedOut = optedOut;
        this.expired = expired;
        this.includeSent = includeSent;

    }

    public Integer getDraft() {
        return draft;
    }

    public TransactionRetention setDraft(Integer draft) {
        throwOnNull(FIELD_DRAFT, draft);
        this.draft = draft;
        setDirty(FIELD_DRAFT);
        return this;
    }

    public Integer getSent() {
        return sent;
    }

    public TransactionRetention setSent(Integer sent) {
        throwOnNull(FIELD_SENT, sent);
        this.sent = sent;
        setDirty(FIELD_SENT);
        return this;
    }

    public Integer getCompleted() {
        return completed;
    }

    public TransactionRetention setCompleted(Integer completed) {
        throwOnNull(FIELD_COMPLETED, completed);
        this.completed = completed;
        setDirty(FIELD_COMPLETED);
        return this;
    }

    public Integer getArchived() {
        return archived;
    }

    public TransactionRetention setArchived(Integer archived) {
        throwOnNull(FIELD_ARCHIVED, archived);
        this.archived = archived;
        setDirty(FIELD_ARCHIVED);
        return this;
    }

    public Integer getDeclined() {
        return declined;
    }

    public TransactionRetention setDeclined(Integer declined) {
        throwOnNull(FIELD_DECLINED, declined);
        this.declined = declined;
        setDirty(FIELD_DECLINED);
        return this;
    }

    public Integer getOptedOut() {
        return optedOut;
    }

    public TransactionRetention setOptedOut(Integer optedOut) {
        throwOnNull(FIELD_OPTEDOUT, optedOut);
        this.optedOut = optedOut;
        setDirty(FIELD_OPTEDOUT);
        return this;
    }

    public Integer getExpired() {
        return expired;
    }

    public TransactionRetention setExpired(Integer expired) {
        throwOnNull(FIELD_EXPIRED, expired);
        this.expired = expired;
        setDirty(FIELD_EXPIRED);
        return this;
    }

    public Integer getLifetimeTotal() {
        return lifetimeTotal;
    }

    public TransactionRetention setLifetimeTotal(Integer lifetimeTotal) {
        throwOnNull(FIELD_LIFETIMETOTAL, lifetimeTotal);
        this.lifetimeTotal = lifetimeTotal;
        setDirty(FIELD_LIFETIMETOTAL);
        return this;
    }

    public Integer getLifetimeUntilCompletion() {
        return lifetimeUntilCompletion;
    }

    public TransactionRetention setLifetimeUntilCompletion(Integer lifetimeUntilCompletion) {
        throwOnNull(FIELD_LIFETIMEUNTILCOMPLETION, lifetimeUntilCompletion);
        this.lifetimeUntilCompletion = lifetimeUntilCompletion;
        setDirty(FIELD_LIFETIMEUNTILCOMPLETION);
        return this;
    }

    public Boolean getIncludeSent() {
        return includeSent;
    }

    public TransactionRetention setIncludeSent(Boolean includeSent) {
        throwOnNull(FIELD_INCLUDESENT, includeSent);
        this.includeSent = includeSent;
        setDirty(FIELD_INCLUDESENT);
        return this;
    }
}
