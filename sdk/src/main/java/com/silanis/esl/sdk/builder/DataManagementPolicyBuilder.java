package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DataManagementPolicy;
import com.silanis.esl.sdk.TransactionRetention;

public class DataManagementPolicyBuilder {

    private TransactionRetention transactionRetention;

    private DataManagementPolicyBuilder() {
    }

    /**
     * Create a new Data Management Policy.
     *
     * @return the Data Management Policy builder itself
     */
    public static DataManagementPolicyBuilder newDataManagementPolicy() {
        return new DataManagementPolicyBuilder();
    }

    /**
     * Set transaction retention
     *
     * @param transactionRetention
     * @return This
     */
    public DataManagementPolicyBuilder withTransactionRetention(TransactionRetention transactionRetention) {
        this.transactionRetention = transactionRetention;
        return this;
    }

    /**
     * Builds the actual ExpiryTimeConfiguration with the specified values
     *
     * @return the ExpiryTimeConfiguration object
     */
    public DataManagementPolicy build() {
        DataManagementPolicy result = new DataManagementPolicy();
        result.setTransactionRetention(transactionRetention);
        return result;
    }
}
