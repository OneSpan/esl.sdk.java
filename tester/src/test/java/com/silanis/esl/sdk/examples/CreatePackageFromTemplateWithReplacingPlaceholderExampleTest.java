package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.Signer;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.silanis.esl.sdk.examples.CreatePackageFromTemplateWithReplacingPlaceholderExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 3/10/15.
 */
public class CreatePackageFromTemplateWithReplacingPlaceholderExampleTest {

    @Test
    public void verifyResult() {
        CreatePackageFromTemplateWithReplacingPlaceholderExample example = new CreatePackageFromTemplateWithReplacingPlaceholderExample(Props.get());
        example.run();

        assertThat("Package Signers are not added correctly. ", example.retrievedPackage.getSigners().size(), is(3));
        assertThat("Package Signers are not set correctly. ", example.retrievedPackage.getPlaceholders().size(), is(0));

        Signer signer1 = example.retrievedPackage.getSigners().get(1);
        Signer signer2 = example.retrievedPackage.getSigners().get(2);

        assertThat("Package Signer1 is not set correctly. ", signer1.getFirstName(), is(TEMPLATE_SIGNER_FIRST));
        assertThat("Package Signer1 is not set correctly. ", signer1.getLastName(), is(TEMPLATE_SIGNER_LAST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getFirstName(), is(PACKAGE_SIGNER_FIRST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getLastName(), is(PACKAGE_SIGNER_LAST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getId(), is(PLACEHOLDER_ID));


        List<Signature> signatures = (ArrayList<Signature>)example.retrievedPackage.getDocument(DOCUMENT_NAME).getSignatures();

        assertThat("Package Signaturs are not set correctly. ", signatures.size(), is(2));
        Signature sig1 = getSignatureForEmail(signatures, example.email1);
        assertThat(sig1, notNullValue());
        Signature sig2 = getSignatureForEmail(signatures, example.email2);
        assertThat(sig2, notNullValue());
    }

    private Signature getSignatureForEmail(List<Signature> signatures, String email) {
        for (Signature signature : signatures) {
            if (StringUtils.equals(signature.getSignerEmail(), email)) {
                return signature;
            }
        }
        return null;
    }
}
