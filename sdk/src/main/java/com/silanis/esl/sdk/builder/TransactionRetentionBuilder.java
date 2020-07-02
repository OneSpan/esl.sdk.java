package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.TransactionRetention;

public class TransactionRetentionBuilder {

    private Integer draft;
    private Integer sent;
    private Integer completed;
    private Integer archived;
    private Integer declined;
    private Integer optedOut;
    private Integer expired;
    private Integer lifetimeTotal;
    private Integer lifetimeUntilCompletion;

    private TransactionRetentionBuilder() {
    }

    /**
     * Create a new Transaction Retention.
     *
     * @return the Transaction Retention builder itself
     */
    public static TransactionRetentionBuilder newTransactionRetention() {
        return new TransactionRetentionBuilder();
    }

    /**
     * Set draft in days
     *
     * @param draft
     * @return This
     */
    public TransactionRetentionBuilder withDraft(int draft) {
        this.draft = draft;
        return this;
    }

    /**
     * Set sent in days
     *
     * @param sent
     * @return This
     */
    public TransactionRetentionBuilder withSent(int sent) {
        this.sent = sent;
        return this;
    }

    /**
     * Set completed in days
     *
     * @param completed
     * @return This
     */
    public TransactionRetentionBuilder withCompleted(int completed) {
        this.completed = completed;
        return this;
    }

    /**
     * Set archived in days
     *
     * @param archived
     * @return This
     */
    public TransactionRetentionBuilder withArchived(int archived) {
        this.archived = archived;
        return this;
    }

    /**
     * Set declined in days
     *
     * @param declined
     * @return This
     */
    public TransactionRetentionBuilder withDeclined(int declined) {
        this.declined = declined;
        return this;
    }

    /**
     * Set optedOut in days
     *
     * @param optedOut
     * @return This
     */
    public TransactionRetentionBuilder withOptedOut(int optedOut) {
        this.optedOut = optedOut;
        return this;
    }

    /**
     * Set expired in days
     *
     * @param expired
     * @return This
     */
    public TransactionRetentionBuilder withExpired(int expired) {
        this.expired = expired;
        return this;
    }
    
    /**
     * Set total life time in days
     * 
     * @param lifetimeTotal
     * @return
     */
    public TransactionRetentionBuilder withLifetimeTotal(int lifetimeTotal) {
        this.lifetimeTotal = lifetimeTotal;
        return this;
    }
    
    /**
     * Set life time until completion in days
     * 
     * @param lifetimeUntilCompletion
     * @return
     */
    public TransactionRetentionBuilder withLifetimeUntilCompletion(int lifetimeUntilCompletion) {
        this.lifetimeUntilCompletion = lifetimeUntilCompletion;
        return this;
    }

    /**
     * Builds the actual ExpiryTimeConfiguration with the specified values
     *
     * @return the ExpiryTimeConfiguration object
     */
    public TransactionRetention build() {
        TransactionRetention result = new TransactionRetention();
        result.setOptedOut(optedOut);
        result.setSent(sent);
        result.setExpired(expired);
        result.setDraft(draft);
        result.setDeclined(declined);
        result.setCompleted(completed);
        result.setArchived(archived);
        if (lifetimeTotal != null) {
            result.setLifetimeTotal(lifetimeTotal);
        }
        if (lifetimeUntilCompletion != null) {
            result.setLifetimeUntilCompletion(lifetimeUntilCompletion);
        }
        return result;
    }
}
