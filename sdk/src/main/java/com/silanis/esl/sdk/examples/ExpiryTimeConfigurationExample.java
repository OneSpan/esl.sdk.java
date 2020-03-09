package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.ExpiryTimeConfiguration;

import static com.silanis.esl.sdk.builder.ExpiryTimeConfigurationBuilder.newExpiryTimeConfiguration;

public class ExpiryTimeConfigurationExample extends SDKSample  {

    public ExpiryTimeConfiguration expiryTimeConfigurationAfterUpdate;

    public static void main( String... args ) {
        new ExpiryTimeConfigurationExample().run();
    }

    @Override
    protected void execute() {

        ExpiryTimeConfiguration expiryTimeConfiguration = newExpiryTimeConfiguration()
                .withMaximumRemainingDays(60)
                .withRemainingDays(60)
                .build();

        eslClient.getDataRetentionSettingsService().setExpiryTimeConfiguration(expiryTimeConfiguration);
        expiryTimeConfigurationAfterUpdate = eslClient.getDataRetentionSettingsService().getExpiryTimeConfiguration();
    }
}
