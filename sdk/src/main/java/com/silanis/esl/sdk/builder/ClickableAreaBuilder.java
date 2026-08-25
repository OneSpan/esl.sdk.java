package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ClickableArea;
import com.silanis.esl.sdk.ClickableAreaAlignment;

/**
 * ClickableAreaBuilder is a convenient class used to define a field's clickable area.
 */
public class ClickableAreaBuilder {

    private Double width;
    private Double height;
    private ClickableAreaAlignment alignment;

    private ClickableAreaBuilder() {}

    /**
     * Creates a clickable area builder.
     *
     * @return a clickable area builder
     */
    public static ClickableAreaBuilder newClickableArea() {
        return new ClickableAreaBuilder();
    }

    /**
     * Sets the size, in pixel, of the clickable area.
     *
     * @param width  the width of the clickable area min="0"
     * @param height the height of the clickable area min="0"
     * @return the clickable area builder itself
     */
    public ClickableAreaBuilder withSize(double width, double height) {
        if (width < 0 || height < 0) {
            throw new BuilderException("Clickable area width and height must not be negative.");
        }
        this.width = width;
        this.height = height;
        return this;
    }

    /**
     * Sets the anchor position of the field's mark within the clickable area.
     *
     * @param alignment the alignment
     * @return the clickable area builder itself
     */
    public ClickableAreaBuilder withAlignment(ClickableAreaAlignment alignment) {
        this.alignment = alignment;
        return this;
    }

    /**
     * Builds the actual ClickableArea with the values specified.
     *
     * @return the built ClickableArea
     */
    public ClickableArea build() {
        ClickableArea result = new ClickableArea();
        result.setWidth(width);
        result.setHeight(height);
        result.setAlignment(alignment);
        return result;
    }
}
