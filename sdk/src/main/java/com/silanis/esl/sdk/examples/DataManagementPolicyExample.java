package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DataManagementPolicy;

import static com.silanis.esl.sdk.builder.DataManagementPolicyBuilder.newDataManagementPolicy;
import static com.silanis.esl.sdk.builder.TransactionRetentionBuilder.newTransactionRetention;

public class DataManagementPolicyExample extends SDKSample {

    public DataManagementPolicy dataManagementPolicyAfterUpdate;

    public static void main(String... args) {
        new DataManagementPolicyExample().run();
    }

    @Override
    protected void execute() {

        DataManagementPolicy dataManagementPolicy = newDataManagementPolicy()
                .withTransactionRetention(newTransactionRetention()
                        .withArchived(60)
                        .withCompleted(60)
                        .withDeclined(60)
                        .withDraft(60)
                        .withExpired(60)
                        .withOptedOut(60)
                        .withSent(0)
                        .build())
                .build();

        eslClient.getDataRetentionSettingsService().setDataManagementPolicy(dataManagementPolicy);
        dataManagementPolicyAfterUpdate = eslClient.getDataRetentionSettingsService().getDataManagementPolicy();
    }
}
