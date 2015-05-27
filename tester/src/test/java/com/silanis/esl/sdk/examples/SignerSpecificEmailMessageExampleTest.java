package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.SignerSpecificEmailMessageExample.EMAIL_MESSAGE;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 23/01/14
 * Time: 11:54 AM
 * 
 * Test SignerSpecificEmailMessageExample.
 */
public class SignerSpecificEmailMessageExampleTest {
    @Test
    public void verifyResult() {
        SignerSpecificEmailMessageExample example = new SignerSpecificEmailMessageExample(Props.get());
        example.run();
        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat(documentPackage.getSigner(example.email1).getMessage(), is(equalTo(EMAIL_MESSAGE)));
    }
}
