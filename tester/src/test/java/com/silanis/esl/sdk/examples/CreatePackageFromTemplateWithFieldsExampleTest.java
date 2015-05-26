package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Field;
import com.silanis.esl.sdk.FieldStyle;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.builder.FieldBuilder;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.CreatePackageFromTemplateWithFieldsExample.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 5/26/15.
 */
public class CreatePackageFromTemplateWithFieldsExampleTest {
    @Test
    public void verifyResult() {
        CreatePackageFromTemplateWithFieldsExample example = new CreatePackageFromTemplateWithFieldsExample(Props.get());
        example.run();

        DocumentPackage documentPackage = example.getRetrievedPackage();

        for (Signature signature : documentPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            for (Field field : signature.getFields()) {
                if(field.getId().toString().equals(TEXTFIELD_ID)){
                    assertThat(field.getPage(), is(equalTo(TEXTFIELD_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_TEXT_FIELD)));
                }
                if (field.getId().toString().equals(CHECKBOX_1_ID)) {
                    assertThat(field.getPage(), is(equalTo(CHECKBOX_1_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_CHECK_BOX)));
                    assertThat(field.getValue(), is(nullValue()));
                }
                if (field.getId().toString().equals(CHECKBOX_2_ID)) {
                    assertThat(field.getPage(), is(equalTo(CHECKBOX_1_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_CHECK_BOX)));
                    assertThat(field.getValue(), is(equalTo(FieldBuilder.CHECKBOX_CHECKED)));
                }
                if (field.getId().toString().equals(RADIO_1_ID)) {
                    assertThat(field.getPage(), is(equalTo(RADIO_1_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_RADIO_BUTTON)));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(equalTo(RADIO_1_GROUP)));
                    assertThat(field.getValue(), is(nullValue()));
                }
                if (field.getId().toString().equals(RADIO_2_ID)) {
                    assertThat(field.getPage(), is(equalTo(RADIO_2_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.UNBOUND_RADIO_BUTTON)));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(equalTo(RADIO_2_GROUP)));
                    assertThat(field.getValue(), is(equalTo(FieldBuilder.RADIO_SELECTED)));
                }
                if (field.getId().toString().equals(DROP_LIST_ID)) {
                    assertThat(field.getPage(), is(equalTo(DROP_LIST_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.DROP_LIST)));
                    assertThat(field.getFieldValidator().getOptions().get(0), is(equalTo(DROP_LIST_OPTION1)));
                    assertThat(field.getFieldValidator().getOptions().get(1), is(equalTo(DROP_LIST_OPTION2)));
                    assertThat(field.getFieldValidator().getOptions().get(2), is(equalTo(DROP_LIST_OPTION3)));
                    assertThat(field.getValue(), is(equalTo(DROP_LIST_OPTION2)));
                }
                if (field.getId().toString().equals(TEXT_AREA_ID)) {
                    assertThat(field.getPage(), is(equalTo(TEXT_AREA_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.TEXT_AREA)));
                    assertThat(field.getValue(), is(equalTo(TEXT_AREA_VALUE)));
                }
                if (field.getId().toString().equals(LABEL_FIELD_ID)) {
                    assertThat(field.getPage(), is(equalTo(LABEL_FIELD_PAGE)));
                    assertThat(field.getStyle(), is(equalTo(FieldStyle.LABELFIELD)));
                    assertThat(field.getValue(), is(equalTo(LABEL_FIELD_VALUE)));
                }
            }
        }
    }
}
