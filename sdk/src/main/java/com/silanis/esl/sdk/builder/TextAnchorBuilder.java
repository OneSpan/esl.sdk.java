package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.TextAnchor;
import com.silanis.esl.sdk.TextAnchorPosition;

/**
 * The TextAnchorBuilder is a convenient class to define the position of
 * signatures or fields relative to a text string present in the original
 * document.
 */
public class TextAnchorBuilder {
    private String anchorText;
    private int occurrence;
    private int character;
    private TextAnchorPosition position;
    private int xOffset, yOffset;
    private int width, height;

    private TextAnchorBuilder( String anchorText ) {
        this.anchorText = anchorText;
    }

    /**
     * Creates a new textanchor for a given text string
     *
     * @param anchorText the text string that MUST be present in the document. @size(max="255")
     * @return This
     */
    public static TextAnchorBuilder newTextAnchor( String anchorText ) {
        return new TextAnchorBuilder( anchorText );
    }

    /**
     * Set the index of the occurrence of the text string we want to use for
     * text anchor.
     * <p>
     * E.g.: If there are multiple occurrence of the string
     * "signature:" in a document, this allows to indicate which occurrence we
     * are actually looking for.
     *
     * @param occurence @min="0"
     * @return This
     */
    public TextAnchorBuilder withOccurence( int occurence ) {
        this.occurrence = occurence;
        return this;
    }

    /**
     * Set character index of the anchor text for which to position the signature relative to.
     *
     * @param character the index of the anchor text character to position the signature @min="0"
     * @return This
     */
    public TextAnchorBuilder withCharacter( int character ) {
        this.character = character;
        return this;
    }

    /**
     * Set the position relative to the bounding box of the searched string the
     * position offset is calculated from.
     * <p>
     * E.g.: We want to position a signature relative to the TOP_LEFT corner
     * of the string "signature:"
     *
     * @param position
     * @return This
     */
    public TextAnchorBuilder atPosition( TextAnchorPosition position ) {
        this.position = position;
        return this;
    }

    /**
     * Offset in pixel, relative to the TextAnchorPosition
     * {@link #atPosition(TextAnchorPosition)} where the signature or field will
     * be positioned.
     *
     * @param x pixel offset @min="0"
     * @param y pixel offset @min="0"
     * @return This
     */
    public TextAnchorBuilder withOffset( int x, int y ) {
        this.xOffset = x;
        this.yOffset = y;
        return this;
    }

    /**
     * Size, in pixel, of the signature of form field.
     * @param width pixel width @min="0"
     * @param height pixel height @min="0"
     * @return This
     */
    public TextAnchorBuilder withSize( int width, int height ) {
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Builds the text anchor.
     * @return the text anchor
     */
    public TextAnchor build() {
        TextAnchor result = new TextAnchor();

        result.setAnchorText( anchorText );
        result.setOccurrence( occurrence );
        result.setCharacter( character );
        result.setPosition( position );
        result.setXOffset( xOffset );
        result.setYOffset( yOffset );
        result.setWidth( width );
        result.setHeight( height );

        return result;
    }
}
