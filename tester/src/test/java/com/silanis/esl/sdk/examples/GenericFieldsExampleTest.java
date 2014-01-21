package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * User: jessica
 * Date: 21/01/14
 * Time: 3:26 PM
 * <p/>
 *
 * Test GenericFieldsExample.
 */
public class GenericFieldsExampleTest {
    @Test
    public void verifyResult() {
        GenericFieldsExample genericFieldsExample = new GenericFieldsExample(Props.get());
        genericFieldsExample.run();

        DocumentPackage documentPackage = genericFieldsExample.getEslClient().getPackage(genericFieldsExample.getPackageId());

        for (Signature signature : documentPackage.getDocument(GenericFieldsExample.DOCUMENT_NAME).getSignatures()) {
            for (Field field : signature.getFields()) {
                if (field.getX() == GenericFieldsExample.CHECKBOX_1_POSITION_X && field.getY() == GenericFieldsExample.CHECKBOX_1_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.CHECKBOX_1_PAGE)));
                    assertThat(field.getWidth(), is(equalTo(GenericFieldsExample.CHECKBOX_1_WIDTH)));
                    assertThat(field.getHeight(), is(equalTo(GenericFieldsExample.CHECKBOX_1_HEIGHT)));
                }
                if (field.getX() == GenericFieldsExample.CHECKBOX_2_POSITION_X && field.getY() == GenericFieldsExample.CHECKBOX_2_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.CHECKBOX_2_PAGE)));
                    assertThat(field.getWidth(), is(equalTo(GenericFieldsExample.CHECKBOX_2_WIDTH)));
                    assertThat(field.getHeight(), is(equalTo(GenericFieldsExample.CHECKBOX_2_HEIGHT)));
                    assertThat(field.getValue(), is(equalTo(String.valueOf(GenericFieldsExample.CHECKBOX_2_VALUE))));
                }
                if (field.getX() == GenericFieldsExample.TEXTFIELD_POSITION_X && field.getY() == GenericFieldsExample.TEXTFIELD_POSITION_Y) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.TEXTFIELD_PAGE)));
                }
            }
        }

    }
}
