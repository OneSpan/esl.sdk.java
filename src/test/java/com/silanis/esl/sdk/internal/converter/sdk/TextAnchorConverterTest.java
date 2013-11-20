package com.silanis.esl.sdk.internal.converter.sdk;

import com.silanis.esl.api.model.ExtractAnchor;
import com.silanis.esl.sdk.TextAnchorPosition;
import com.silanis.esl.sdk.builder.TextAnchorBuilder;
import com.silanis.esl.sdk.internal.converter.TextAnchorConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * User: jessica
 * Date: 19/11/13
 * Time: 4:44 PM
 *
 * Test TextAnchorConverter.
 */
public class TextAnchorConverterTest {

    public static final double DEFAULT_DOUBLE_TOLERANCE = 0.01f;

    @Test
    public void toAPIExtractAnchor() {
        int character = 65;
        int occurence = 2;
        int xOffset = 101;
        int yOffset = 102;
        int width = 201;
        int height = 202;

        com.silanis.esl.sdk.TextAnchor sdkTextAnchor = TextAnchorBuilder.newTextAnchor("Anchor Text")
                .atPosition(TextAnchorPosition.BOTTOMLEFT)
                .withCharacter(character)
                .withOccurence(occurence)
                .withOffset(xOffset, yOffset) //xOffset, yOffset
                .withSize(width, height)   // width, height
                .build();

        com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor = new TextAnchorConverter(sdkTextAnchor).toAPIExtractAnchor();

        compareTextAnchors( sdkTextAnchor,  apiExtractAnchor);
    }

    @Test
    public void toSDKTextAnchor() {
        com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor = new ExtractAnchor();

        apiExtractAnchor.setAnchorPoint("BOTTOMLEFT");
        apiExtractAnchor.setCharacterIndex(1);
        apiExtractAnchor.setHeight(10);
        apiExtractAnchor.setWidth(20);
        apiExtractAnchor.setIndex(2);
        apiExtractAnchor.setLeftOffset(101);
        apiExtractAnchor.setTopOffset(102);
        apiExtractAnchor.setText("Text field.");

        com.silanis.esl.sdk.TextAnchor sdkTextAnchor = new TextAnchorConverter(apiExtractAnchor).toSDKTextAnchor();
        compareTextAnchors( sdkTextAnchor,  apiExtractAnchor);

    }

    private void compareTextAnchors(com.silanis.esl.sdk.TextAnchor sdkTextAnchor, com.silanis.esl.api.model.ExtractAnchor apiExtractAnchor) {
        assertEquals( apiExtractAnchor.getCharacterIndex(), sdkTextAnchor.getCharacter(), DEFAULT_DOUBLE_TOLERANCE );
        assertEquals( apiExtractAnchor.getHeight(), sdkTextAnchor.getHeight(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiExtractAnchor.getTopOffset(), sdkTextAnchor.getYOffset(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiExtractAnchor.getLeftOffset(), sdkTextAnchor.getXOffset(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiExtractAnchor.getWidth(), sdkTextAnchor.getWidth(), DEFAULT_DOUBLE_TOLERANCE);
        assertEquals( apiExtractAnchor.getText(), sdkTextAnchor.getAnchorText());
        assertEquals( apiExtractAnchor.getAnchorPoint(), sdkTextAnchor.getPosition().toString());

    }

}
