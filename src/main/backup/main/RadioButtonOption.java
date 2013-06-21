package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * User: dave
 */
public class RadioButtonOption implements Serializable {

    private static final long serialVersionUID = 1L;

    private double x, y;
    private double width, height;
    private int page;

    public RadioButtonOption() {}

    public void setX( double x ) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY( double y ) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setWidth( double width ) {
        this.width = width;
    }

    public double getWidth() {
        return width;
    }

    public void setHeight( double height ) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    public void setPage( int page ) {
        this.page = page;
    }

    public int getPage() {
        return page;
    }
}
