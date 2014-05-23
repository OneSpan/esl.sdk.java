package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Field;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;


public class SignatureConverterTest implements ConverterTest {
    private Signature sdkSignature1 = null;
    private Signature sdkSignature2 = null;
    private Approval apiApproval1 = null;
    private Approval apiApproval2 = null;
    private com.silanis.esl.api.model.Package apiPackage;
    private SignatureConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSignature1 = null;
        converter = new SignatureConverter(sdkSignature1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIApproval(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiApproval1 = null;
        converter = new SignatureConverter(apiApproval1, apiPackage);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSignature(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSignature1 = null;
        converter = new SignatureConverter(sdkSignature1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSignature(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiApproval1 = null;
        converter = new SignatureConverter(apiApproval1, apiPackage);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIApproval(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkSignature1 = createTypicalSDKSignature();
        sdkSignature2 = new SignatureConverter(sdkSignature1).toSDKSignature();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSignature2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSignature2, is(equalTo(sdkSignature1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiApproval1 = createTypicalAPIApproval();
        apiApproval2 = new SignatureConverter(apiApproval1, apiPackage).toAPIApproval();

        assertThat("Converter returned a null api object for a non null api object", apiApproval2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiApproval2, is(apiApproval1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkSignature1 = createTypicalSDKSignature();
        apiApproval1 = new SignatureConverter(sdkSignature1).toAPIApproval();

        assertThat("Converter returned a null api object for a non null sdk object", apiApproval1, is(notNullValue()));
        assertThat("Name was not correctly set", apiApproval1.getFields().get(0).getName(), is(equalTo(sdkSignature1.getName().toString())));
        assertThat("Height was not correctly set", apiApproval1.getFields().get(0).getHeight(), is(equalTo(sdkSignature1.getHeight())));
        assertThat("Width was not correctly set", apiApproval1.getFields().get(0).getWidth(), is(equalTo(sdkSignature1.getWidth())));
        assertThat("Page was not correctly set", apiApproval1.getFields().get(0).getPage(), is(equalTo(sdkSignature1.getPage())));
        assertThat("Left position was not correctly set", apiApproval1.getFields().get(0).getLeft(), is(equalTo(sdkSignature1.getX())));
        assertThat("Top position was not correctly set", apiApproval1.getFields().get(0).getTop(), is(equalTo(sdkSignature1.getY())));
    }

    private Signature createTypicalSDKSignature() {
        return SignatureBuilder.acceptanceFor("abc@test.com")
                .atPosition(100, 100)
                .withName("signature")
                .withSize(100, 100)
                .onPage(0)
                .build();
    }

    private Approval createTypicalAPIApproval() {
        Approval apiApproval = new Approval();
        Field apiSignature = new Field();

        apiSignature.setName("apiSignature");
        apiSignature.setTop(100.0);
        apiSignature.setLeft(200.0);
        apiSignature.setHeight(200.0);
        apiSignature.setWidth(100.0);
        apiSignature.setPage(0);

        apiApproval.addField(apiSignature);

        return apiApproval;
    }
}
