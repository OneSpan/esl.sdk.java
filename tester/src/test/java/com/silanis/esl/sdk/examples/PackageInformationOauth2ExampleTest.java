package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;

import org.junit.Test;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.provider.EslOauthClientConfig;
import com.silanis.esl.sdk.provider.EslOauthClientProvider;

public class PackageInformationOauth2ExampleTest {
    
    @Test
    public void verifyResult() {
        PackageInformationOauth2Example example = new PackageInformationOauth2Example();

        EslOauthClientConfig config = new EslOauthClientConfig.Builder()
            .withAuthenticationServer(example.getOauthServerUrl())
            .withClientId(example.getClientId())
            .withClientSecret(example.getClientSecret())
            .withApiUrl(example.getApiUrl())
            .build();

        EslClient eslClient = EslOauthClientProvider.getInstance().getEslClient(config);

        example.setEslClient(eslClient);

        example.run();

        assertThat("Support configuration was not set correctly. ", example.supportConfiguration, notNullValue());
        assertThat("Support configuration was not set correctly. ", example.supportConfiguration.getEmail(), not(isEmptyOrNullString()));
        assertThat("Support configuration was not set correctly. ", example.supportConfiguration.getPhone(), not(isEmptyOrNullString()));
    }
}
