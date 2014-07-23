package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
/**
 * Retrieves the values found in the fields of each of the documents in a package
 */
public class RetrieveFieldValuesExample extends SDKSample {

    private InputStream documentInputStream;

    public static void main( String... args ) {
        new RetrieveFieldValuesExample(Props.get()).run();
    }

    public RetrieveFieldValuesExample( Properties properties ) {
        this( properties.getProperty( "api.key" ), properties.getProperty( "api.url" ) );
    }

    public RetrieveFieldValuesExample( String apiKey, String apiUrl ) {
        super( apiKey, apiUrl );
    }

    void execute() {
        PackageId packageId = new PackageId( "" );

        List<FieldSummary> fieldSummaries = eslClient.getFieldValues( packageId );

        System.out.println( "SignerId,\t DocumentId, \tFieldId \tFieldName \tValue" );
        for ( FieldSummary fieldSummary : fieldSummaries ) {
            System.out.print( fieldSummary.getSignerId() + ", \t" +
                    fieldSummary.getDocumentId() + ", \t" +
                    fieldSummary.getFieldId() + ", \t" +
                    fieldSummary.getFieldName() + ", \t" +
                    fieldSummary.getFieldValue() + "\n" );
        }
    }
}
