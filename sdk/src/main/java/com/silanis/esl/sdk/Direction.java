package com.silanis.esl.sdk;

/**
 * Created by lena on 2014-07-18.
 */
public enum Direction {
    ASCENDING("asc"), DESCENDING("desc");

    private final String direction;

    Direction(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
