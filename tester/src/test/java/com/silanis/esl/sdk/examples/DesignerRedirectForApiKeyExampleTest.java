package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by mpoitras on 22/04/14.
 */
public class DesignerRedirectForApiKeyExampleTest {
    @Test
    public void verifyResult() {
        DesignerRedirectForApiKeyExample designerRedirectForApiKeyExample = new DesignerRedirectForApiKeyExample( Props.get() );
        designerRedirectForApiKeyExample.run();

        assertThat(designerRedirectForApiKeyExample.getGeneratedLinkToDesignerForApiKey(), is(notNullValue()));
    }

}
