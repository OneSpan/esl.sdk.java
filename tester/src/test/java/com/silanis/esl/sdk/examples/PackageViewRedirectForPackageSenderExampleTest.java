package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.internal.HttpRequestUtil;
import com.silanis.esl.sdk.internal.Serialization;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
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

        assertThat(example.generatedLinkToPackageViewForSender, is(notNullValue()));
        String stringResponse = HttpRequestUtil.getUrlContent(example.generatedLinkToPackageViewForSender);
        com.silanis.esl.api.model.Package aPackage = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.Package.class);
        assertThat(aPackage.getName(), is(example.PACKAGE_NAME));
    }
}
