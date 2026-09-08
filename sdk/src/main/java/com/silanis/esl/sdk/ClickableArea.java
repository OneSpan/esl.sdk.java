package com.silanis.esl.sdk;

/**
 * Defines the clickable (hit) area of a checkbox or radio button field,
 * expanding the region the signer can click beyond the field's own bounds.
 */
public class ClickableArea {

    private Double width;
    private Double height;
    private ClickableAreaAlignment alignment;

    public Double getWidth() {
        return width;
    }

    public void setWidth( Double width ) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight( Double height ) {
        this.height = height;
    }

    public ClickableAreaAlignment getAlignment() {
        return alignment;
    }

    public void setAlignment( ClickableAreaAlignment alignment ) {
        this.alignment = alignment;
    }
}
