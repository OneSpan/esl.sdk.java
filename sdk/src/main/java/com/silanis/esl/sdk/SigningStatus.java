package com.silanis.esl.sdk;

public enum SigningStatus {
    INACTIVE( "DRAFT" ),
    COMPLETE( "COMPLETED" ),
    ARCHIVED( "ARCHIVED" ),
    DECLINED( "DECLINED" ),
    OPTED_OUT( "OPTED_OUT" ),
    EXPIRED( "EXPIRED" ),
    CANCELED( "CANCELED" ),
    SIGNING_PENDING( "SIGNING_PENDING" ),
    SIGNING_COMPLETE( "SIGNING_COMPLETED" );

    private final String token;

    private SigningStatus( String token ) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public static SigningStatus statusForToken( String token ) {
        for ( SigningStatus status : SigningStatus.values() ) {
            if ( status.getToken().equals( token ) ) {
                return status;
            }
        }
        return null;
    }
}
