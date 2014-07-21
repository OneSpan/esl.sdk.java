package com.silanis.esl.sdk;

/**
 * Created by lena on 2014-07-18.
 */
public enum Direction {
    ASCENDING("asc"), DESCENDING("desc");

    private final String dir;

    Direction(String dir) {
        this.dir = dir;
    }

    public String getDir() {
        return dir;
    }
}
