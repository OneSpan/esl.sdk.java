package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
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

        Signer signer1 = example.retrievedPackage.getSigners().get(example.email1);
        Signer signer2 = example.retrievedPackage.getSigners().get(example.email2);

        assertThat("Package Signer1 is not set correctly. ", signer1.getFirstName(), is(example.TEMPLATE_SIGNER_FIRST));
        assertThat("Package Signer1 is not set correctly. ", signer1.getLastName(), is(example.TEMPLATE_SIGNER_LAST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getFirstName(), is(example.PACKAGE_SIGNER_FIRST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getLastName(), is(example.PACKAGE_SIGNER_LAST));
        assertThat("Package Signer2 is not set correctly. ", signer2.getId(), is(example.PLACEHOLDER_ID));


        ArrayList<Signature> signatures = (ArrayList<Signature>)example.retrievedPackage.getDocument(example.DOCUMENT_NAME).getSignatures();

        assertThat("Package Signaturs are not set correctly. ", signatures.size(), is(2));
        assertThat("Package Signature1 is not set correctly. ", signatures.get(0).getSignerEmail(), is(example.email1));
        assertThat("Package Signature1 is not set correctly. ", signatures.get(1).getSignerEmail(), is(example.email2));
    }
}
