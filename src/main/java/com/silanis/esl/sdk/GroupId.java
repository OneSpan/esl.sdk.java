package com.silanis.esl.sdk;

import java.io.Serializable;

public class GroupId implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    public GroupId( String id ) {
        if ( id == null || id.trim().isEmpty() ) {
            throw new IllegalArgumentException( ( "id parameter cannot be null or empty" ) );
        }

        this.id = id;
    }

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

        if ( !( other instanceof GroupId ) ) {
            return false;
        }

        GroupId groupId = (GroupId) other;

        return groupId.id.equals( id );
    }

}
