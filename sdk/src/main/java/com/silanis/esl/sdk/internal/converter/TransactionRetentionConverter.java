package com.silanis.esl.sdk.internal.converter;

public class TransactionRetentionConverter {

    private com.silanis.esl.sdk.TransactionRetention sdkTransactionRetention = null;
    private com.silanis.esl.api.model.TransactionRetention apiTransactionRetention = null;

    /**
     * Construct with API TransactionRetention object involved in conversion.
     *
     * @param apiTransactionRetention
     */
    public TransactionRetentionConverter(com.silanis.esl.api.model.TransactionRetention apiTransactionRetention) {
        this.apiTransactionRetention = apiTransactionRetention;
    }

    /**
     * Construct with SDK TransactionRetention object involved in conversion.
     *
     * @param sdkTransactionRetention
     */
    public TransactionRetentionConverter(com.silanis.esl.sdk.TransactionRetention sdkTransactionRetention) {
        this.sdkTransactionRetention = sdkTransactionRetention;
    }

    public com.silanis.esl.sdk.TransactionRetention toSDKTransactionRetention() {
        if (apiTransactionRetention == null) {
            return sdkTransactionRetention;
        }
        com.silanis.esl.sdk.TransactionRetention sdkTransactionRetention = new com.silanis.esl.sdk.TransactionRetention();
        sdkTransactionRetention.setArchived(apiTransactionRetention.getArchived());
        sdkTransactionRetention.setCompleted(apiTransactionRetention.getCompleted());
        sdkTransactionRetention.setDeclined(apiTransactionRetention.getDeclined());
        sdkTransactionRetention.setDraft(apiTransactionRetention.getDraft());
        sdkTransactionRetention.setExpired(apiTransactionRetention.getExpired());
        sdkTransactionRetention.setOptedOut(apiTransactionRetention.getOptedOut());
        sdkTransactionRetention.setSent(apiTransactionRetention.getSent());
        return sdkTransactionRetention;

    }

    public com.silanis.esl.api.model.TransactionRetention toAPITransactionRetention() {
        if (sdkTransactionRetention == null) {
            return apiTransactionRetention;
        }
        com.silanis.esl.api.model.TransactionRetention apiTransactionRetention = new com.silanis.esl.api.model.TransactionRetention();
        apiTransactionRetention.setArchived(sdkTransactionRetention.getArchived());
        apiTransactionRetention.setCompleted(sdkTransactionRetention.getCompleted());
        apiTransactionRetention.setDeclined(sdkTransactionRetention.getDeclined());
        apiTransactionRetention.setDraft(sdkTransactionRetention.getDraft());
        apiTransactionRetention.setExpired(sdkTransactionRetention.getExpired());
        apiTransactionRetention.setOptedOut(sdkTransactionRetention.getOptedOut());
        apiTransactionRetention.setSent(sdkTransactionRetention.getSent());
        return apiTransactionRetention;
    }
}
