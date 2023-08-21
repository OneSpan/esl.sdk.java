package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.api.model.Field;
import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;


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
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIApproval(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiApproval1 = null;
        converter = new SignatureConverter(apiApproval1, apiPackage);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSignature(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSignature1 = null;
        converter = new SignatureConverter(sdkSignature1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSignature(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiApproval1 = null;
        converter = new SignatureConverter(apiApproval1, apiPackage);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIApproval(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkSignature1 = createTypicalSDKSignature();
        sdkSignature2 = new SignatureConverter(sdkSignature1).toSDKSignature();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSignature2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSignature2, is(sdkSignature1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiApproval1 = createTypicalAPIApproval();
        apiApproval2 = new SignatureConverter(apiApproval1, apiPackage).toAPIApproval();

        assertThat("Converter returned a null api object for a non null api object", apiApproval2, notNullValue());
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

        assertThat("Converter returned a null api object for a non null sdk object", apiApproval1, notNullValue());
        assertTrue("EnforceCaptureSignature was not correctly set", apiApproval1.getEnforceCaptureSignature());
        assertThat("Name was not correctly set", apiApproval1.getFields().get(0).getName(), is(sdkSignature1.getName()));
        assertThat("Height was not correctly set", apiApproval1.getFields().get(0).getHeight(), is(sdkSignature1.getHeight()));
        assertThat("Width was not correctly set", apiApproval1.getFields().get(0).getWidth(), is(sdkSignature1.getWidth()));
        assertThat("Page was not correctly set", apiApproval1.getFields().get(0).getPage(), is(sdkSignature1.getPage()));
        assertThat("Left position was not correctly set", apiApproval1.getFields().get(0).getLeft(), is(sdkSignature1.getX()));
        assertThat("Top position was not correctly set", apiApproval1.getFields().get(0).getTop(), is(sdkSignature1.getY()));
        assertThat("Font size was not correctly set", apiApproval1.getFields().get(0).getFontSize(), is(sdkSignature1.getFontSize()));
        assertThat("Tooltip was not correctly set", apiApproval1.getFields().get(0).getTooltip(), is(sdkSignature1.getTooltip()));
    }

    private Signature createTypicalSDKSignature() {
        return SignatureBuilder.acceptanceFor("abc@test.com")
                .atPosition(100, 100)
                .withName("signature")
                .withSize(100, 100)
                .withFontSize(10)
                .enableEnforceCaptureSignature()
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
        apiSignature.setFontSize(20);

        apiApproval.addField(apiSignature);
        apiApproval.setEnforceCaptureSignature(true);

        return apiApproval;
    }
}
