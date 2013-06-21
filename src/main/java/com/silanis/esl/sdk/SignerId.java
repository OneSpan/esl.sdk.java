package com.silanis.esl.sdk;

import java.io.Serializable;

/**
 * User: dave
 */
public class SignerId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    SignerId() {
    }

    /**
     * <p>Constructor of the PackageId class
     *
     * @param id the ID of the package. It cannot be null or empty
     */
    public SignerId( String id ) {
        if ( id == null || id.trim().isEmpty() ) {
            throw new IllegalArgumentException( ( "id parameter cannot be null or empty" ) );
        }

        this.id = id;
    }

    /**
     * <p>Accessor method used to get the package ID</p>
     *
     * @return the ID of the package
     */
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals( Object other ) {
        if ( this == other ) {
            return true;
        }

        if ( !( other instanceof SignerId ) ) {
            return false;
        }

        SignerId signerId = (SignerId) other;

        return signerId.id.equals( id );
    }
}
