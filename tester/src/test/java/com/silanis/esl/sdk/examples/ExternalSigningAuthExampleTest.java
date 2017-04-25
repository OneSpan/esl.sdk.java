package com.silanis.esl.sdk.examples;


import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.ExternalSigningAuth;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * This feature is not yet completed.
 * It is NOT recommended to be used it right now, because we expect some changes in model.
 */

public class ExternalSigningAuthExampleTest {

    @Test
    public void verifyResult() {

        ExternalSigningAuthExample example = new ExternalSigningAuthExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat("External Signing Auth null", documentPackage.getSigner(example.email1).getExternalSigningAuth(), notNullValue());

        Signer signer = documentPackage.getSigner(example.email1);
        assertThat("External Signing Auth is null", signer.getExternalSigningAuth(), notNullValue());
        assertThat("Identity Info is not Equal", signer.getExternalSigningAuth().getIdentityInfo(),
                is(ExternalSigningAuthExample.IDENTITY_INFO));
        assertThat("Provider Key is not  equal", signer.getExternalSigningAuth().getProviderKey(),
                is(ExternalSigningAuthExample.PROVIDER_KEY));


    }
}
