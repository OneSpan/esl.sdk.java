package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.FieldClickableArea;
import com.silanis.esl.sdk.ClickableArea;
import com.silanis.esl.sdk.ClickableAreaAlignment;
import com.silanis.esl.sdk.builder.ClickableAreaBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Test ClickableAreaConverter.
 */
public class ClickableAreaConverterTest implements ConverterTest {

    private ClickableArea sdkClickableArea1 = null;
    private ClickableArea sdkClickableArea2 = null;
    private FieldClickableArea apiClickableArea1 = null;
    private FieldClickableArea apiClickableArea2 = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkClickableArea1 = null;
        ClickableAreaConverter converter = new ClickableAreaConverter(sdkClickableArea1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIFieldClickableArea(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiClickableArea1 = null;
        ClickableAreaConverter converter = new ClickableAreaConverter(apiClickableArea1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKClickableArea(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkClickableArea1 = null;
        ClickableAreaConverter converter = new ClickableAreaConverter(sdkClickableArea1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKClickableArea(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiClickableArea1 = null;
        ClickableAreaConverter converter = new ClickableAreaConverter(apiClickableArea1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIFieldClickableArea(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkClickableArea1 = createTypicalSDKClickableArea();
        sdkClickableArea2 = new ClickableAreaConverter(sdkClickableArea1).toSDKClickableArea();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkClickableArea2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkClickableArea2, is(equalTo(sdkClickableArea1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiClickableArea1 = createTypicalAPIClickableArea();
        apiClickableArea2 = new ClickableAreaConverter(apiClickableArea1).toAPIFieldClickableArea();
        assertThat("Converter returned a null api object for a non null api object", apiClickableArea2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiClickableArea2, is(equalTo(apiClickableArea1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiClickableArea1 = createTypicalAPIClickableArea();
        sdkClickableArea1 = new ClickableAreaConverter(apiClickableArea1).toSDKClickableArea();
        assertThat("Converter returned a null sdk object for a non null api object", sdkClickableArea1, is(notNullValue()));
        compareClickableAreas(sdkClickableArea1, apiClickableArea1);
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkClickableArea1 = createTypicalSDKClickableArea();
        apiClickableArea1 = new ClickableAreaConverter(sdkClickableArea1).toAPIFieldClickableArea();
        assertThat("Converter returned a null api object for a non null sdk object", apiClickableArea1, is(notNullValue()));
        compareClickableAreas(sdkClickableArea1, apiClickableArea1);
    }

    private ClickableArea createTypicalSDKClickableArea() {
        return ClickableAreaBuilder.newClickableArea()
                .withSize(20, 10)
                .withAlignment(ClickableAreaAlignment.TOP_LEFT)
                .build();
    }

    private FieldClickableArea createTypicalAPIClickableArea() {
        FieldClickableArea apiClickableArea = new FieldClickableArea();

        apiClickableArea.setWidth(20.0);
        apiClickableArea.setHeight(10.0);
        apiClickableArea.setAlignment("TOP_LEFT");

        return apiClickableArea;
    }

    private void compareClickableAreas(ClickableArea sdkClickableArea, FieldClickableArea apiClickableArea) {
        assertThat("Width was not correctly set", apiClickableArea.getWidth(), is(equalTo(sdkClickableArea.getWidth())));
        assertThat("Height was not correctly set", apiClickableArea.getHeight(), is(equalTo(sdkClickableArea.getHeight())));
        assertThat("Alignment was not correctly set", apiClickableArea.getAlignment(), is(equalTo(sdkClickableArea.getAlignment().name())));
    }
}
