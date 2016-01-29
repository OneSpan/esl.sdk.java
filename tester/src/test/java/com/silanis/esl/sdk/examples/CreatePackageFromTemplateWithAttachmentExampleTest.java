package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AttachmentRequirement;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Signer;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 2/6/15.
 */
public class CreatePackageFromTemplateWithAttachmentExampleTest {
    @Test
    public void verifyResult() {
        CreatePackageFromTemplateWithAttachmentExample example = new CreatePackageFromTemplateWithAttachmentExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        for (Signer signer : documentPackage.getSigners()) {
            for (AttachmentRequirement attachmentRequirement : signer.getAttachmentRequirements()) {
                assertThat(attachmentRequirement, notNullValue());
            }
        }
    }
}
