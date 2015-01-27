package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.FastTrackRole;
import com.silanis.esl.sdk.FastTrackSigner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by schoi on 1/27/15.
 */
public class FastTrackRoleBuilder {

    private String id;
    private String name;
    private List<FastTrackSigner> signers = new ArrayList<FastTrackSigner>();

    /**
     * <p>The constructor of the FastTrackRoleBuilder Class.</p>
     *
     * @param id the role's id
     */
    private FastTrackRoleBuilder(String id) {
        this.id = id;
    }

    /**
     * <p>Creates a FastTrackRoleBuilder object.</p>
     *
     * @param id the role's id
     * @return the FastTrackRoleBuilder itself
     */
    public static FastTrackRoleBuilder newRoleWithId(String id) {
        return new FastTrackRoleBuilder(id);
    }

    /**
     * Sets the role's name.
     *
     * @param name the role's name
     * @return the FastTrackRoleBuilder itself
     */
    public FastTrackRoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * <p>Adds a FastTrackSigner to the FastTrackRole.</p>
     *
     * @param signer a signer belonging to the role
     * @return the FastTrackRoleBuilder itself
     */
    public FastTrackRoleBuilder withSigner( FastTrackSigner signer ) {
        this.signers.add(signer);
        return this;
    }

    /**
     * Builds the actual FastTrackRole object.
     *
     * @return the FastTrackRole object
     */
    public FastTrackRole build() {
        FastTrackRole role = new FastTrackRole();
        role.setId(id);
        role.setName(name);
        role.setSigners(signers);
        return role;
    }
}
