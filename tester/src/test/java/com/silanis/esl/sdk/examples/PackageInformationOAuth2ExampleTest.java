package com.silanis.esl.sdk.examples;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.IsNot.not;

import org.junit.Test;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.provider.EslOAuthClientConfig;
import com.silanis.esl.sdk.provider.EslOAuthClientProvider;

public class PackageInformationOAuth2ExampleTest {

    @Test
    public void verifyResult() {
        PackageInformationOAuth2Example example = new PackageInformationOAuth2Example();

        EslOAuthClientConfig config = new EslOAuthClientConfig.Builder()
            .withAuthenticationServer(example.getOAuthServerUrl())
            .withClientId(example.getClientId())
            .withClientSecret(example.getClientSecret())
            .withApiUrl(example.getApiUrl())
            .build();

        EslClient eslClient = EslOAuthClientProvider.getInstance().getEslClient(config);

        example.setEslClient(eslClient);

        example.run();

        assertThat("Support configuration was not set correctly. ", example.supportConfiguration, notNullValue());
        assertThat("Support configuration was not set correctly. ", example.supportConfiguration.getEmail(), not(isEmptyOrNullString()));
        assertThat("Support configuration was not set correctly. ", example.supportConfiguration.getPhone(), not(isEmptyOrNullString()));
        assertThat("Package information not found. ", example.aPackage, notNullValue());
        assertThat("Package information not found. ", example.aPackage.getId().getId(), not(isEmptyOrNullString()));
        assertThat("Package information not found. ", example.aPackage.getName(), not(isEmptyOrNullString()));

    }
}
