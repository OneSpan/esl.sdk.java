package com.silanis.esl.sdk;

/**
 * Created by schoi on 1/8/16.
 */
public enum Connector {
    DYNAMICS("dynamics"),
    SHAREPOINT("sharepoint"),
    DYNAMICS_2011("dynamics2011"),
    DYNAMICS_2013("dynamics2013"),
    DYNAMICS_2015("dynamics2015"),
    SHAREPOINT_2010("sharepoint2010"),
    SHAREPOINT_2013("sharepoint2013");

    private final String origin;

    Connector(String origin) {
        this.origin = origin;
    }

    public String getOrigin() {
        return origin;
    }
}
