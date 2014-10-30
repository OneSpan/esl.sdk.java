package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.builder.FieldBuilder;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
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
                if(field.getId().toString().equals(GenericFieldsExample.TEXTFIELD_ID)){
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.TEXTFIELD_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_TEXT_FIELD)));
                }
                if (field.getId().toString().equals(GenericFieldsExample.CHECKBOX_1_ID)) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.CHECKBOX_1_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_CHECK_BOX)));
                    assertThat(field.getValue(), is(nullValue()));
                }
                if (field.getId().toString().equals(GenericFieldsExample.CHECKBOX_2_ID)) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.CHECKBOX_1_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_CHECK_BOX)));
                    assertThat(field.getValue(), is(equalTo(FieldBuilder.CHECKBOX_CHECKED)));
                }
                if (field.getId().toString().equals(GenericFieldsExample.RADIO_1_ID)) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.RADIO_1_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_RADIO_BUTTON)));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(equalTo(GenericFieldsExample.RADIO_1_GROUP)));
                    assertThat(field.getValue(), is(nullValue()));
                }
                if (field.getId().toString().equals(GenericFieldsExample.RADIO_2_ID)) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.RADIO_2_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_RADIO_BUTTON)));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(equalTo(GenericFieldsExample.RADIO_2_GROUP)));
                    assertThat(field.getValue(), is(equalTo(FieldBuilder.RADIO_SELECTED)));
                }
                if (field.getId().toString().equals(GenericFieldsExample.DROP_LIST_ID)) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.DROP_LIST_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.DROP_LIST)));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(equalTo(GenericFieldsExample.DROP_LIST_OPTION1)));
                    assertThat(field.getFieldValidator().getOptions().get(1), is(equalTo(GenericFieldsExample.DROP_LIST_OPTION2)));
                    assertThat(field.getFieldValidator().getOptions().get(2), is(equalTo(GenericFieldsExample.DROP_LIST_OPTION3)));
                    assertThat(field.getValue(), is(equalTo(GenericFieldsExample.DROP_LIST_OPTION2)));
                }
                if (field.getId().toString().equals(GenericFieldsExample.TEXT_AREA_ID)) {
                    assertThat(field.getPage(), is(equalTo(GenericFieldsExample.TEXT_AREA_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.TEXT_AREA)));
                    assertThat(field.getValue(), is(equalTo(GenericFieldsExample.TEXT_AREA_VALUE)));
                }
            }
        }

    }
}
