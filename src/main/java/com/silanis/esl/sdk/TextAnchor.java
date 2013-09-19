package com.silanis.esl.sdk;

/**
 * User: dave
 */
public class TextAnchor {
    private String anchorText;
    private int occurrence;
    private int character;
    private TextAnchorPosition position;
    private int xOffset, yOffset;
    private int width, height;

    public String getAnchorText() {
        return anchorText;
    }

    public void setAnchorText( String anchorText ) {
        this.anchorText = anchorText;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence( int occurrence ) {
        this.occurrence = occurrence;
    }

    public int getCharacter() {
        return character;
    }

    public void setCharacter( int character ) {
        this.character = character;
    }

    public TextAnchorPosition getPosition() {
        return position;
    }

    public void setPosition( TextAnchorPosition position ) {
        this.position = position;
    }

    public int getXOffset() {
        return xOffset;
    }

    public void setXOffset( int xOffset ) {
        this.xOffset = xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public void setYOffset( int yOffset ) {
        this.yOffset = yOffset;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight( int height ) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth( int width ) {
        this.width = width;
    }
}
