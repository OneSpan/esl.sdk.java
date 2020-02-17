package com.silanis.esl.sdk.internal.converter;

public class DataManagementPolicyConverter {

    private com.silanis.esl.sdk.DataManagementPolicy sdkDataManagementPolicy = null;
    private com.silanis.esl.api.model.DataManagementPolicy apiDataManagementPolicy = null;

    /**
     * Construct with API DataManagementPolicy object involved in conversion.
     *
     * @param apiDataManagementPolicy
     */
    public DataManagementPolicyConverter(com.silanis.esl.api.model.DataManagementPolicy apiDataManagementPolicy) {
        this.apiDataManagementPolicy = apiDataManagementPolicy;
    }

    /**
     * Construct with SDK DataManagementPolicy object involved in conversion.
     *
     * @param sdkDataManagementPolicy
     */
    public DataManagementPolicyConverter(com.silanis.esl.sdk.DataManagementPolicy sdkDataManagementPolicy) {
        this.sdkDataManagementPolicy = sdkDataManagementPolicy;
    }

    public com.silanis.esl.sdk.DataManagementPolicy toSDKDataManagementPolicy() {
        com.silanis.esl.sdk.DataManagementPolicy sdkDataManagementPolicy = new com.silanis.esl.sdk.DataManagementPolicy();
        if(apiDataManagementPolicy.getTransactionRetention() != null) {
            TransactionRetentionConverter converter = new TransactionRetentionConverter(apiDataManagementPolicy.getTransactionRetention());
            sdkDataManagementPolicy.setTransactionRetention(converter.toSDKTransactionRetention());
        }
        return sdkDataManagementPolicy;
    }

    public com.silanis.esl.api.model.DataManagementPolicy toAPIDataManagementPolicy() {
        com.silanis.esl.api.model.DataManagementPolicy apiDataManagementPolicy = new com.silanis.esl.api.model.DataManagementPolicy();
        if(sdkDataManagementPolicy.getTransactionRetention() != null) {
            TransactionRetentionConverter converter = new TransactionRetentionConverter(sdkDataManagementPolicy.getTransactionRetention());
            apiDataManagementPolicy.setTransactionRetention(converter.toAPITransactionRetention());
        }
        return apiDataManagementPolicy;
    }
}
