package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static com.silanis.esl.sdk.examples.PackageViewRedirectForPackageSenderExample.PACKAGE_NAME;
import static com.silanis.esl.sdk.internal.HttpRequestUtil.getUrlContent;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 12/24/14.
 */
public class PackageViewRedirectForPackageSenderExampleTest {

    @Test
    public void verifyResult() {
        PackageViewRedirectForPackageSenderExample example = new PackageViewRedirectForPackageSenderExample( Props.get() );
        example.run();

        assertThat(example.generatedLinkToPackageViewForSender, notNullValue());

        String stringResponse = getUrlContent(example.generatedLinkToPackageViewForSender);
        assertThat(stringResponse, containsString(PACKAGE_NAME));
    }
}
