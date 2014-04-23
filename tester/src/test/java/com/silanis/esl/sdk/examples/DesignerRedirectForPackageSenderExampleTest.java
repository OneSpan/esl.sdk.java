package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class DesignerRedirectForPackageSenderExampleTest {
    @Test
    public void verifyResult() {
        DesignerRedirectForPackageSenderExample designerRedirectForPackageSenderExample = new DesignerRedirectForPackageSenderExample( Props.get() );
        designerRedirectForPackageSenderExample.run();

        assertThat(designerRedirectForPackageSenderExample.getGeneratedLinkToDesignerForSender(), is(notNullValue()));
    }

}
