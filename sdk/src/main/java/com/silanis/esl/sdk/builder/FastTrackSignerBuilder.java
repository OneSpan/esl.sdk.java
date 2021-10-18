package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.FastTrackSigner;

/**
 * Created by schoi on 1/27/15.
 */
public class FastTrackSignerBuilder {

    private String id;
    private String email;
    private String firstName;
    private String lastName;

    /**
     * <p>The constructor of the FastTrackSignerBuilder Class.</p>
     *
     * @param id the signer's id
     */
    private FastTrackSignerBuilder(String id) {
        this.id = id;
    }

    /**
     * <p>Creates a FastTrackSignerBuilder object.</p>
     *
     * @param id the signer's id
     * @return the FastTrackSignerBuilder itself
     */
    public static FastTrackSignerBuilder newSignerWithId(String id) {
        return new FastTrackSignerBuilder(id);
    }

    /**
     * Sets the signer's email.
     *
     * @param email the signer's email
     * @return the FastTrackSignerBuilder itself
     */
    public FastTrackSignerBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * Sets the signer's first name.
     *
     * @param firstName the signer's first name @size(min="1", max="255")
     * @return the FastTrackSignerBuilder itself
     */
    public FastTrackSignerBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    /**
     * Sets the signer's last name.
     *
     * @param lastName the signer's last name @size(min="1", max="255")
     * @return the FastTrackSignerBuilder itself
     */
    public FastTrackSignerBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    /**
     * Builds the actual FastTrackSigner object.
     *
     * @return the FastTrackSigner object
     */
    public FastTrackSigner build() {
        FastTrackSigner signer = new FastTrackSigner();
        signer.setId(id);
        signer.setEmail(email);
        signer.setFirstName(firstName);
        signer.setLastName(lastName);
        return signer;
    }
}
