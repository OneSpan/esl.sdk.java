package com.silanis.esl.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataManagementPolicy extends Model {

    private static final long serialVersionUID = 1L;
    @JsonIgnore
    private static final String FIELD_TRANSACTION_RETENTION = "transactionRetention";

    private TransactionRetention transactionRetention;

    public DataManagementPolicy() {}

    public DataManagementPolicy(TransactionRetention transactionRetention) {
        this.transactionRetention = transactionRetention;
    }

    public DataManagementPolicy setTransactionRetention(TransactionRetention transactionRetention) {
        this.transactionRetention = transactionRetention;
        setDirty(FIELD_TRANSACTION_RETENTION);
        return this;
    }

    public TransactionRetention getTransactionRetention() {
        return transactionRetention;
    }
}
