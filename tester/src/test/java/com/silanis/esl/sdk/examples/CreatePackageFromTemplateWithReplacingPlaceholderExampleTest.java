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
        CreatePackageFromTemplateWithReplacingPlaceholderExample example = new CreatePackageFromTemplateWithReplacingPlaceholderExample();
        example.run();

        assertThat("Package Signers are not added correctly. ", example.retrievedPackage.getSigners().size(), is(6));
        assertThat("Package Signers are not set correctly. ", example.retrievedPackage.getPlaceholders().size(), is(0));

        Signer signer1 = example.retrievedPackage.getSigner(example.email1);
        Signer signer2 = example.retrievedPackage.getSigner(example.email2);
        Signer signer3 = example.retrievedPackage.getSigner(example.email3);
        Signer signer4 = example.retrievedPackage.getSigner(example.email4);
        Signer signer5 = example.retrievedPackage.getSigner(example.email5);

        assertThat("Package Signer1 is not set correctly. ", signer1.getFirstName(), is(TEMPLATE_SIGNER_FIRST));
        assertThat("Package Signer1 is not set correctly. ", signer1.getLastName(), is(TEMPLATE_SIGNER_LAST));
        assertThat("Package Signer1 is not set correctly. ", signer1.getSigningOrder(), is(3));
        assertThat("Package Signer2 is not set correctly. ", signer2.getFirstName(), is(PACKAGE_SIGNER_FIRST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getLastName(), is(PACKAGE_SIGNER_LAST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getId(), is(PLACEHOLDER_ID1));
        assertThat("Package Signer2 is not set correctly. ", signer2.getSigningOrder(), is(1));
        assertThat("Package Signer3 is not set correctly. ", signer3.getFirstName(), is(PACKAGE_SIGNER_FIRST));
        assertThat("Package Signer3 is not set correctly. ", signer3.getLastName(), is(PACKAGE_SIGNER_LAST));
        assertThat("Package Signer3 is not set correctly. ", signer3.getId(), is(PLACEHOLDER_ID2));
        assertThat("Package Signer3 is not set correctly. ", signer3.getSigningOrder(), is(2));
        assertThat("Package Signer4 is not set correctly. ", signer4.getFirstName(), is(PACKAGE_SIGNER_FIRST));
        assertThat("Package Signer4 is not set correctly. ", signer4.getLastName(), is(PACKAGE_SIGNER_LAST));
        assertThat("Package Signer4 is not set correctly. ", signer4.getSigningOrder(), is(4));
        assertThat("Package Signer5 is not set correctly. ", signer5.getFirstName(), is(PACKAGE_SIGNER_FIRST));
        assertThat("Package Signer5 is not set correctly. ", signer5.getLastName(), is(PACKAGE_SIGNER_LAST));
        assertThat("Package Signer5 is not set correctly. ", signer5.getSigningOrder(), is(5));

        List<Signature> signatures = (ArrayList<Signature>)example.retrievedPackage.getDocument(DOCUMENT_NAME).getSignatures();

        assertThat("Package Signaturs are not set correctly. ", signatures.size(), is(3));
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
