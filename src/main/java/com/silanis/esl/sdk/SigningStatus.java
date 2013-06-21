package com.silanis.esl.sdk;

/**
 * User: dave
 */
public enum SigningStatus {
    INACTIVE( "DRAFT" ),
    COMPLETE( "COMPLETED" ),
    ARCHIVED( "ARCHIVED" ),
    DECLINED( "DECLINED" ),
    OPTED_OUT( "OPTED_OUT" ),
    EXPIRED( "EXPIRED" ),
    CANCELED( "CANCELED" ),
    SIGNING_PENDING( "SIGNING-PENDING" ),
    SIGNING_COMPLETE( "SIGNING-COMPLETE" );

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
