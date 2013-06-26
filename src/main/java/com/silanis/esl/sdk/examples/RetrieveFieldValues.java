package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;

import java.util.List;

public class RetrieveFieldValues {
    public static final String API_KEY = "YTUwOGQ5ZDktMDZmMi00MjM5LTkwNDQtYmZiZDI2MTdmNmQxOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "https://sandbox.e-signlive.com/api";
    public static final String PACKAGE_ID = "";
    public static final PackageId packageId = new PackageId( PACKAGE_ID );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );
        List<FieldSummary> fieldSummaries = eslClient.getFieldValues( packageId );

        System.out.println( "SignerId, DocumentId, FieldId: Value" );
        for ( FieldSummary fieldSummary : fieldSummaries ) {
            System.out.print( fieldSummary.getSignerId() + ", " + fieldSummary.getDocumentId() + ", " + fieldSummary.getFieldId() + ": " + fieldSummary.getFieldValue() );
        }
    }
}
