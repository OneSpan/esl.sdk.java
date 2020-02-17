package com.silanis.esl.sdk;

import java.io.Serializable;

public class DataManagementPolicy implements Serializable {

    private TransactionRetention transactionRetention;

    public DataManagementPolicy() {}

    public DataManagementPolicy(TransactionRetention transactionRetention) {
        this.transactionRetention = transactionRetention;
    }

    public void setTransactionRetention(TransactionRetention transactionRetention) {
        this.transactionRetention = transactionRetention;
    }

    public TransactionRetention getTransactionRetention() {
        return transactionRetention;
    }
}
