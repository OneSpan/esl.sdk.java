package com.silanis.esl.sdk;

import java.io.Serializable;

public class DocumentId implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    DocumentId() {
    }

    /**
     * <p>Constructor of the PackageId class
     *
     * @param id the ID of the package. It cannot be null or empty
     */
    public DocumentId( String id ) {
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

        if ( !( other instanceof DocumentId ) ) {
            return false;
        }

        DocumentId documentId = (DocumentId) other;

        return documentId.id.equals( id );
    }
}
