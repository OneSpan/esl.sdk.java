package com.silanis.esl.sdk;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 1/27/15.
 */
public class FastTrackRole {
    private String id;
    private String name;
    private List<FastTrackSigner> signers = new ArrayList<FastTrackSigner>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FastTrackSigner> getSigners() {
        return signers;
    }

    public void setSigners(List<FastTrackSigner> signers) {
        this.signers = signers;
    }
}
