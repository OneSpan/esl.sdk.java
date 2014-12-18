package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by mina on 15/12/14.
 */
public class GetSigningStatusExampleTest {

    private static final String CURRENT_SIGNER_STATUS = "CURRENT_SIGNER";

    @Test
    public void verifyResult() {
        GetSigningStatusExample getSigningStatusExample = new GetSigningStatusExample(Props.get());
        getSigningStatusExample.run();

        String signerEmail = Props.get().getProperty( "1.email" );

        DocumentPackage documentPackage = getSigningStatusExample.getEslClient().getPackage(getSigningStatusExample.getPackageId());
        Signer signer = documentPackage.getSigner(signerEmail);


        assertThat("signer status is missing", signer.getStatus(), is(CURRENT_SIGNER_STATUS));
    }

}
