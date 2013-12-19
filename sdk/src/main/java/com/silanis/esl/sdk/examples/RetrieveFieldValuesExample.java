package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
/**
 * Retrieves the values found in the fields of each of the documents in a package
 */
public class RetrieveFieldValuesExample {

    private InputStream documentInputStream;
    private String apiKey;
    private String apiUrl;

    public RetrieveFieldValuesExample( Properties properties ) {
        this( properties.getProperty( "api.key" ), properties.getProperty( "api.url" ) );
    }

    public RetrieveFieldValuesExample( String apiKey, String apiUrl ) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
    }

    void execute() {
        EslClient eslClient = new EslClient( apiKey, apiUrl );
        PackageId packageId = new PackageId( "" );

        List<FieldSummary> fieldSummaries = eslClient.getFieldValues( packageId );

        System.out.println( "SignerId, DocumentId, FieldId: Value" );
        for ( FieldSummary fieldSummary : fieldSummaries ) {
            System.out.print( fieldSummary.getSignerId() + ", " + fieldSummary.getDocumentId() + ", " + fieldSummary.getFieldId() + ": " + fieldSummary.getFieldValue() + "\n" );
        }
    }
}
