package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by mpoitras on 22/04/14.
 */
public class DesignerRedirectForPackageSenderExampleTest {
    @Test
    public void verifyResult() {
        DesignerRedirectForPackageSenderExample example = new DesignerRedirectForPackageSenderExample();
        example.run();

        assertThat(example.generatedLinkToDesignerForSender, not(isEmptyOrNullString()));
    }
}
