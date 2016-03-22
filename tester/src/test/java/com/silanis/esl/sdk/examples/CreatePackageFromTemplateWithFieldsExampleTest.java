package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.Signature;
import org.junit.Test;

import static com.silanis.esl.sdk.FieldStyle.*;
import static com.silanis.esl.sdk.builder.FieldBuilder.CHECKBOX_CHECKED;
import static com.silanis.esl.sdk.builder.FieldBuilder.RADIO_SELECTED;
import static com.silanis.esl.sdk.examples.CreatePackageFromTemplateWithFieldsExample.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 5/26/15.
 */
public class CreatePackageFromTemplateWithFieldsExampleTest {
    @Test
    public void verifyResult() {
        CreatePackageFromTemplateWithFieldsExample example = new CreatePackageFromTemplateWithFieldsExample();
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        for (Signature signature : documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            for (Field field : signature.getFields()) {
                if(field.getId().toString().equals(TEXTFIELD_ID)){
                    assertThat(field.getPage(), equalTo(TEXTFIELD_PAGE));
                    assertThat(field.getStyle(), equalTo(UNBOUND_TEXT_FIELD));
                }
                if (field.getId().toString().equals(CHECKBOX_1_ID)) {
                    assertThat(field.getPage(), equalTo(CHECKBOX_1_PAGE));
                    assertThat(field.getStyle(), equalTo(UNBOUND_CHECK_BOX));
                    assertThat(field.getValue(), nullValue());
                }
                if (field.getId().toString().equals(CHECKBOX_2_ID)) {
                    assertThat(field.getPage(), equalTo(CHECKBOX_1_PAGE));
                    assertThat(field.getStyle(), equalTo(UNBOUND_CHECK_BOX));
                    assertThat(field.getValue(), equalTo(CHECKBOX_CHECKED));
                }
                if (field.getId().toString().equals(RADIO_1_ID)) {
                    assertThat(field.getPage(), equalTo(RADIO_1_PAGE));
                    assertThat(field.getStyle(), equalTo(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), equalTo(RADIO_1_GROUP));
                    assertThat(field.getValue(), nullValue());
                }
                if (field.getId().toString().equals(RADIO_2_ID)) {
                    assertThat(field.getPage(), equalTo(RADIO_2_PAGE));
                    assertThat(field.getStyle(), equalTo(UNBOUND_RADIO_BUTTON));
                    assertThat(field.getFieldValidator().getOptions().get(0), equalTo(RADIO_2_GROUP));
                    assertThat(field.getValue(), equalTo(RADIO_SELECTED));
                }
                if (field.getId().toString().equals(DROP_LIST_ID)) {
                    assertThat(field.getPage(), equalTo(DROP_LIST_PAGE));
                    assertThat(field.getStyle(), equalTo(DROP_LIST));
                    assertThat(field.getFieldValidator().getOptions().get(0), equalTo(DROP_LIST_OPTION1));
                    assertThat(field.getFieldValidator().getOptions().get(1), equalTo(DROP_LIST_OPTION2));
                    assertThat(field.getFieldValidator().getOptions().get(2), equalTo(DROP_LIST_OPTION3));
                    assertThat(field.getValue(), equalTo(DROP_LIST_OPTION2));
                }
                if (field.getId().toString().equals(TEXT_AREA_ID)) {
                    assertThat(field.getPage(), equalTo(TEXT_AREA_PAGE));
                    assertThat(field.getStyle(), equalTo(TEXT_AREA));
                    assertThat(field.getValue(), equalTo(TEXT_AREA_VALUE));
                }
                if (field.getId().toString().equals(LABEL_ID)) {
                    assertThat(field.getPage(), equalTo(LABEL_PAGE));
                    assertThat(field.getStyle(), equalTo(LABEL));
                    assertThat(field.getValue(), equalTo(LABEL_VALUE));
                }
            }
        }
    }
}
