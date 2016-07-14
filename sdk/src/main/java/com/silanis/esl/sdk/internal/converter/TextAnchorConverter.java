package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.ExtractAnchor;
import com.silanis.esl.sdk.TextAnchor;
import com.silanis.esl.sdk.TextAnchorPosition;

/**
 * Converter between SDK text anchor and API Extract Anchor.
 */
public class TextAnchorConverter {

    private TextAnchor sdkTextAnchor = null;
    private ExtractAnchor extractAnchor = null;

    /**
     * Construct with SDK object involved in conversion.
     *
     * @param textAnchor
     */
    public TextAnchorConverter( TextAnchor textAnchor ) {
        this.sdkTextAnchor = textAnchor;
    }

    /**
     * Construct with API object involved in conversion.
     *
     * @param extractAnchor
     */
    public TextAnchorConverter( ExtractAnchor extractAnchor ) {
        this.extractAnchor = extractAnchor;
    }

    /**
     * Convert from SDK to API object.
     *
     * @return an ExtractAnchor object.
     */
    public ExtractAnchor toAPIExtractAnchor() {

        if (sdkTextAnchor == null) {
            return extractAnchor;
        }

        ExtractAnchor result = new ExtractAnchor();

        result.safeSetLeftOffset( sdkTextAnchor.getXOffset() );
        result.safeSetTopOffset( sdkTextAnchor.getYOffset() );
        result.safeSetText(sdkTextAnchor.getAnchorText());
        result.safeSetIndex(sdkTextAnchor.getOccurrence());
        result.safeSetCharacterIndex(sdkTextAnchor.getCharacter());
        if(null != sdkTextAnchor.getPosition())
            result.setAnchorPoint(sdkTextAnchor.getPosition().toString() );
        result.safeSetWidth( sdkTextAnchor.getWidth() );
        result.safeSetHeight( sdkTextAnchor.getHeight() );

        return result;
    }

    /**
     * Convert from API to SDK object.
     *
     * @return a TextAnchor object.
     */
    public TextAnchor toSDKTextAnchor() {

        if (extractAnchor == null) {
            return sdkTextAnchor;
        }

        TextAnchor result = new TextAnchor();

        result.setPosition( TextAnchorPosition.valueOf( extractAnchor.getAnchorPoint() ) );
        if ( extractAnchor.getIndex() != null )
            result.setOccurrence( extractAnchor.getIndex() );

        result.setAnchorText( extractAnchor.getText() );

        if ( extractAnchor.getCharacterIndex() != null )
            result.setCharacter( extractAnchor.getCharacterIndex() );

        if ( extractAnchor.getLeftOffset() != null )
            result.setXOffset( extractAnchor.getLeftOffset() );

        if ( extractAnchor.getTopOffset() != null )
            result.setYOffset( extractAnchor.getTopOffset() );

        if ( extractAnchor.getWidth() != null )
            result.setWidth( extractAnchor.getWidth() );

        if ( extractAnchor.getHeight() != null )
            result.setHeight( extractAnchor.getHeight() );

        return result;
    }
}
