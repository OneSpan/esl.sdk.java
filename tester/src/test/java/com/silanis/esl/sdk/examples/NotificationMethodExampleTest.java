package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.NotificationMethod;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static com.silanis.esl.sdk.NotificationMethod.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class NotificationMethodExampleTest {
    @Test
    public void verifyResult() {
        NotificationMethodExample example = new NotificationMethodExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        assertThat( "Signer 1 notification method was not set correctly.", documentPackage.getSigner(example.email1).getNotification().getMethods(), is(new HashSet<>(Arrays.asList(NotificationMethod.EMAIL))) );
        assertThat( "Signer 2 notification method was not set correctly.", documentPackage.getSigner(example.email2).getNotification().getMethods(), is(new HashSet<>(Arrays.asList(EMAIL, SMS))) );
    }
}