package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.ExtractionType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.List;

import static com.silanis.esl.sdk.examples.ListTemplatesExample.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by lena on 2014-05-06.
 */
public class ListTemplatesExampleTest {

    @Test
    public void verifyResult() {
        ListTemplatesExample example = new ListTemplatesExample();
        example.run();

        List<DocumentPackage> templates =  example.getTemplates();

        assertThat("The returned number of templates is less than 1.", templates.size(), greaterThanOrEqualTo(1));
        for(DocumentPackage template : templates) {
            if(StringUtils.equals(template.getName(), example.packageName)) {
                assertThat(template.getDocuments(), hasSize(4));
                assertThat(template.getDocument(DOCUMENT1_NAME).getExtractionTypes(), hasSize(0));
                assertThat(template.getDocument(DOCUMENT2_NAME).getExtractionTypes(), hasSize(1));
                assertThat(template.getDocument(DOCUMENT2_NAME).getExtractionTypes(), hasItem(ExtractionType.TEXT_TAGS.name()));
                assertThat(template.getDocument(DOCUMENT3_NAME).getExtractionTypes(), hasSize(2));
                assertThat(template.getDocument(DOCUMENT3_NAME).getExtractionTypes(), hasItem(ExtractionType.TEXT_TAGS.name()));
                assertThat(template.getDocument(DOCUMENT3_NAME).getExtractionTypes(), hasItem(ExtractionType.ACROFIELDS.name()));
            }
        }
    }
}
