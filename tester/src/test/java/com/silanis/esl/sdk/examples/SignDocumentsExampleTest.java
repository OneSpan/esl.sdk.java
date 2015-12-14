package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Signature;
import org.hamcrest.Matcher;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 12/14/15.
 */
public class SignDocumentsExampleTest {
    @Test
    public void verifyResult() {

        SignDocumentsExample example = new SignDocumentsExample( Props.get() );
        example.run();

        assertSignatures(example.retrievedPackageBeforeSigning.getDocuments(), example.senderEmail, nullValue(), example.email1, nullValue());
        assertThat(example.retrievedPackageBeforeSigning.getStatus(), is(PackageStatus.SENT));

        assertSignatures(example.retrievedPackageAfterSigningApproval1.getDocuments(), example.senderEmail, notNullValue(), example.email1, nullValue());
        assertThat(example.retrievedPackageAfterSigningApproval1.getStatus(), is(PackageStatus.SENT));

        assertSignatures(example.retrievedPackageAfterSigningApproval2.getDocuments(), example.senderEmail, notNullValue(), example.email1, notNullValue());
        assertThat(example.retrievedPackageAfterSigningApproval2.getStatus(), is(PackageStatus.COMPLETED));
    }

    private void assertSignatures(List<Document> documents, String senderEmail, Matcher<Object> senderAccepted, String signerEmail, Matcher<Object> signerAccepted) {
        for(Document document : documents) {
            for(Signature signature : document.getSignatures()) {
                if(senderEmail.equals(signature.getSignerEmail()))
                    assertThat(signature.getAccepted(), is(senderAccepted));
                if(signerEmail.equals(signature.getSignerEmail()))
                    assertThat(signature.getAccepted(), is(signerAccepted));
            }
        }
    }
}
