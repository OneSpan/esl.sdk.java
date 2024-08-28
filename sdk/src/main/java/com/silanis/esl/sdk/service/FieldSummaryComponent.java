package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.*;

import java.util.List;

/**
 * The FieldSummaryService class provides a method to get the field summary for a package.
 */
public class FieldSummaryComponent extends EslComponent {

    public FieldSummaryComponent(RestClient client, String baseUrl ) {
        super( client, baseUrl);
    }

    /**
     * Gets the field summary for the package and returns a list of field summaries.
     *
     * @param packageId
     * @return A list of field summaries
     * @throws com.silanis.esl.sdk.EslException
     */
    public List<FieldSummary> getFieldSummary( PackageId packageId ) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.FIELD_SUMMARY_PATH )
                .replace( "{packageId}", packageId.getId() )
                .build();

        List<FieldSummary> fieldSummary;
        try {
            String stringResponse = getClient().get(path);


            fieldSummary = Serialization.fromJsonToList(stringResponse, FieldSummary.class);
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not get the field summary.", e );
        } catch ( Exception e ) {
            throw new EslException( "Could not get the field summary.", e );
        }
        return fieldSummary;
    }

}
