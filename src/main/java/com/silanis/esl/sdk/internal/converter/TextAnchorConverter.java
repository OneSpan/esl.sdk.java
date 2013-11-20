package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.ExtractAnchor;
import com.silanis.esl.sdk.TextAnchor;
import com.silanis.esl.sdk.TextAnchorPosition;

public class TextAnchorConverter {

    private TextAnchor sdkTextAnchor;
    private ExtractAnchor extractAnchor;

    public TextAnchorConverter( TextAnchor textAnchor ) {
        this.sdkTextAnchor = textAnchor;
        this.extractAnchor = null;
    }

    public TextAnchorConverter( ExtractAnchor extractAnchor ) {
        this.extractAnchor = extractAnchor;
        this.sdkTextAnchor = null;
    }

    public ExtractAnchor toAPIExtractAnchor() {
        ExtractAnchor result = new ExtractAnchor();

        result.setLeftOffset( sdkTextAnchor.getXOffset() );
        result.setTopOffset( sdkTextAnchor.getYOffset() );
        result.setText( sdkTextAnchor.getAnchorText() );
        result.setIndex( sdkTextAnchor.getOccurrence() );
        result.setCharacterIndex( sdkTextAnchor.getCharacter() );
        result.setAnchorPoint( sdkTextAnchor.getPosition().toString() );
        result.setWidth( sdkTextAnchor.getWidth() );
        result.setHeight( sdkTextAnchor.getHeight() );

        return result;
    }

    public TextAnchor toSDKTextAnchor() {
        TextAnchor result = new TextAnchor();

        result.setPosition( TextAnchorPosition.valueOf( extractAnchor.getAnchorPoint() ) );
        result.setOccurrence( extractAnchor.getIndex() );
        result.setAnchorText( extractAnchor.getText() );
        result.setCharacter( extractAnchor.getCharacterIndex() );
        result.setXOffset( extractAnchor.getLeftOffset() );
        result.setYOffset( extractAnchor.getTopOffset() );
        result.setWidth( extractAnchor.getWidth() );
        result.setHeight( extractAnchor.getHeight() );

        return result;
    }
}
