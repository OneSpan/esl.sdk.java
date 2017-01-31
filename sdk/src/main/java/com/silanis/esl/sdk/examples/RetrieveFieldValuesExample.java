package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;

import java.util.List;
/**
 * Retrieves the values found in the fields of each of the documents in a package
 */
public class RetrieveFieldValuesExample extends SDKSample {

    public static void main( String... args ) {
        new RetrieveFieldValuesExample().run();
    }

    public void execute() {
        PackageId packageId = new PackageId( "562d70a0-585c-4570-90c9-d00568fa1b43" );

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
