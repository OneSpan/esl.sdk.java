package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;

import java.util.List;
import java.util.Properties;
/*
 * Retrieves the values found in the fields of each of the documents in a package
 */
public class RetrieveFieldValues {

    private static final Properties props = Props.get();
    public static final String API_KEY = props.getProperty( "api.key" );
    public static final String API_URL = props.getProperty( "api.url" );

    public static final String PACKAGE_ID = "";
    public static final PackageId packageId = new PackageId( PACKAGE_ID );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        List<FieldSummary> fieldSummaries = eslClient.getFieldValues( packageId );

        System.out.println( "SignerId, DocumentId, FieldId: Value" );
        for ( FieldSummary fieldSummary : fieldSummaries ) {
            System.out.print( fieldSummary.getSignerId() + ", " + fieldSummary.getDocumentId() + ", " + fieldSummary.getFieldId() + ": " + fieldSummary.getFieldValue() + "\n" );
        }
    }
}
