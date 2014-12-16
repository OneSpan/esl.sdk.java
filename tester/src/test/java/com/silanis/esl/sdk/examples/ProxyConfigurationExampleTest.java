package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExampleTest {

    @Test
    public void verifyResult() {
        ProxyConfigurationExample example = new ProxyConfigurationExample(Props.get());
        example.execute();

        DocumentPackage documentPackage = example.eslClientWithProxy.getPackage(example.packageId);

        assertThat(documentPackage, is(notNullValue()));
    }
}
