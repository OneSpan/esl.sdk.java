package com.silanis.esl.sdk.examples;

import org.junit.Test;

public class CustomSenderInfoExampleTest {
    @Test
    public void verifyResult() {
        CustomSenderInfoExample customSenderInfoExample = new CustomSenderInfoExample( Props.get() );
        customSenderInfoExample.run();


    }
}
