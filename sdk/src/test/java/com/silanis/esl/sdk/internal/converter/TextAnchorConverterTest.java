package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.ExtractAnchor;
import com.silanis.esl.sdk.TextAnchorPosition;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * User: jessica
 * Date: 19/11/13
 * Time: 4:44 PM
 * <p>
 * Test TextAnchorConverter.
 */
public class TextAnchorConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.TextAnchor sdkTextAnchor1 = null;
    private com.silanis.esl.sdk.TextAnchor sdkTextAnchor2 = null;
    private com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor1 = null;
    private com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor2 = null;


    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkTextAnchor1 = null;
        TextAnchorConverter converter = new TextAnchorConverter(sdkTextAnchor1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIExtractAnchor(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiExtractAnchor1 = null;
        TextAnchorConverter converter = new TextAnchorConverter(apiExtractAnchor1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKTextAnchor(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkTextAnchor1 = null;
        TextAnchorConverter converter = new TextAnchorConverter(sdkTextAnchor1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKTextAnchor(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiExtractAnchor1 = null;
        TextAnchorConverter converter = new TextAnchorConverter(apiExtractAnchor1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIExtractAnchor(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkTextAnchor1 = createTypicalSDKTextAnchor();
        sdkTextAnchor2 = new TextAnchorConverter(sdkTextAnchor1).toSDKTextAnchor();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkTextAnchor2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkTextAnchor2, is(equalTo(sdkTextAnchor1)));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiExtractAnchor1 = createTypicalAPITextAnchor();
        apiExtractAnchor2 = new TextAnchorConverter(apiExtractAnchor1).toAPIExtractAnchor();
        assertThat("Converter returned a null api object for a non null api object", apiExtractAnchor2, is(notNullValue()));
        assertThat("Converter didn't return the same non-null api object it was given", apiExtractAnchor2, is(equalTo(apiExtractAnchor1)));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiExtractAnchor1 = createTypicalAPITextAnchor();
        sdkTextAnchor1 = new TextAnchorConverter(apiExtractAnchor1).toSDKTextAnchor();
        assertThat("Converter returned a null sdk object for a non null api object", sdkTextAnchor1, is(notNullValue()));
        compareTextAnchors(sdkTextAnchor1, apiExtractAnchor1);
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkTextAnchor1 = createTypicalSDKTextAnchor();
        apiExtractAnchor1 = new TextAnchorConverter(sdkTextAnchor1).toAPIExtractAnchor();
        assertThat("Converter returned a null api object for a non null sdk object", apiExtractAnchor1, is(notNullValue()));

        compareTextAnchors(sdkTextAnchor1, apiExtractAnchor1);
    }

    /**
     * Create an SDK text anchor.
     *
     * @return
     */
    private com.silanis.esl.sdk.TextAnchor createTypicalSDKTextAnchor() {

        com.silanis.esl.sdk.TextAnchor sdkTextAnchor;
        int character = 65;
        int occurrence = 2;
        int xOffset = 101;
        int yOffset = 102;
        int width = 201;
        int height = 202;

        sdkTextAnchor = TextAnchorBuilder.newTextAnchor("Anchor Text")
                .atPosition(TextAnchorPosition.BOTTOMLEFT)
                .withCharacter(character)
                .withOccurence(occurrence)
                .withOffset(xOffset, yOffset) //xOffset, yOffset
                .withSize(width, height)   // width, height
                .build();

        return sdkTextAnchor;
    }

    /**
     * Create a typical API text anchor.
     *
     * @return
     */
    private com.silanis.esl.api.model.ExtractAnchor createTypicalAPITextAnchor() {
        com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor = new ExtractAnchor();

        apiExtractAnchor.setAnchorPoint("BOTTOMLEFT");
        apiExtractAnchor.setCharacterIndex(1);
        apiExtractAnchor.setHeight(10);
        apiExtractAnchor.setWidth(20);
        apiExtractAnchor.setIndex(2);
        apiExtractAnchor.setLeftOffset(101);
        apiExtractAnchor.setTopOffset(102);
        apiExtractAnchor.setText("Text field.");

        return apiExtractAnchor;
    }

    private void compareTextAnchors(com.silanis.esl.sdk.TextAnchor sdkTextAnchor, com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor) {
        assertThat("Character index was not set correctly", apiExtractAnchor.getCharacterIndex(), is(equalTo(sdkTextAnchor.getCharacter())));
        assertThat(" Height was not set correctly.", apiExtractAnchor.getHeight(), is(equalTo(sdkTextAnchor.getHeight())));
        assertThat(" Top offset was not set correctly.", apiExtractAnchor.getTopOffset(), is(sdkTextAnchor.getYOffset()));
        assertThat(" Left offset was not set correctly.", apiExtractAnchor.getLeftOffset(), is(equalTo(sdkTextAnchor.getXOffset())));
        assertThat(" Width was not set correctly.", apiExtractAnchor.getWidth(), is(sdkTextAnchor.getWidth()));
        assertThat(" Text was not set correctly.", apiExtractAnchor.getText(), is(equalTo(sdkTextAnchor.getAnchorText())));
        assertThat(" Anchor point was not set correctly.", apiExtractAnchor.getAnchorPoint(), is(equalTo(sdkTextAnchor.getPosition().toString())));
    }

}
