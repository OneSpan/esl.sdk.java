package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.RadioButtonOption;

/**
 * User: dave
 */
public class RadioButtonOptionBuilder {

    public static final double DEFAULT_WIDTH = 20;
    public static final double DEFAULT_HEIGHT = 20;
    private int page;
    private double x, y;
    private double width, height;

    public RadioButtonOptionBuilder( ) {
        width = DEFAULT_WIDTH;
        height = DEFAULT_HEIGHT;
    }

    public RadioButtonOptionBuilder( int page ) {
        this();
        this.page = page;
    }

    public static RadioButtonOptionBuilder newOptionOnPage( int page ) {
        return new RadioButtonOptionBuilder( page );
    }

    public RadioButtonOptionBuilder atPosition( double x, double y ) {
        this.x = x;
        this.y = y;
        return this;
    }

    public RadioButtonOptionBuilder withSize( double width, double height ) {
        this.width = width;
        this.height = height;
        return this;
    }

    public RadioButtonOption build() {
        RadioButtonOption result = new RadioButtonOption();
        result.setX( x );
        result.setY( y );
        result.setWidth( width );
        result.setHeight( height );
        result.setPage( page );
        return result;
    }
}
