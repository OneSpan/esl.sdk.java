package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Page;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by lena on 2014-05-06.
 */
public class ListTemplatesExampleTest {

    @Test
    public void verifyResult() {
        ListTemplatesExample example = new ListTemplatesExample(Props.get());
        example.run();

        Page<DocumentPackage> templateList =  example.getTemplates();

        assertThat("The returned number of templates is less than 1.", templateList.getSize(), greaterThanOrEqualTo(1));
    }
}
