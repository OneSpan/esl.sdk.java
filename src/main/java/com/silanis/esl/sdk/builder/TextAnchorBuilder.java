package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.TextAnchor;
import com.silanis.esl.sdk.TextAnchorPosition;

/**
 * User: dave
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

    public static TextAnchorBuilder newTextAnchor( String anchorText ) {
        return new TextAnchorBuilder( anchorText );
    }

    public TextAnchorBuilder withOccurence( int occurence ) {
        this.occurrence = occurence;
        return this;
    }

    public TextAnchorBuilder withCharacter( int character ) {
        this.character = character;
        return this;
    }

    public TextAnchorBuilder atPosition( TextAnchorPosition position ) {
        this.position = position;
        return this;
    }

    public TextAnchorBuilder withOffset( int x, int y ) {
        this.xOffset = x;
        this.yOffset = y;
        return this;
    }

    public TextAnchorBuilder withSize( int width, int height ) {
        this.width = width;
        this.height = height;
        return this;
    }

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
