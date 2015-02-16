package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.internal.HttpRequestUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 12/24/14.
 */
public class PackageViewRedirectForPackageSenderExampleTest {

    @Test
    public void verifyResult() {
        PackageViewRedirectForPackageSenderExample example = new PackageViewRedirectForPackageSenderExample( Props.get() );
        example.run();

        assertThat(example.generatedLinkToPackageViewForSender, is(notNullValue()));

        String stringResponse = HttpRequestUtil.getUrlContent(example.generatedLinkToPackageViewForSender);
        assertThat(stringResponse, containsString(example.PACKAGE_NAME));
    }
}
