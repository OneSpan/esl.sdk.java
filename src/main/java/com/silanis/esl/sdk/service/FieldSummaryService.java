package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.FieldSummary;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

import java.util.List;

/**
 * The FieldSummaryService class provides a method to get the field summary for a package.
 */
public class FieldSummaryService {

    private UrlTemplate template;
    private RestClient client;

    public FieldSummaryService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    /**
     * Gets the field summary for the package and returns a list of field summaries.
     *
     * @param packageId
     * @return A list of field summaries
     * @throws com.silanis.esl.sdk.EslException
     */
    public List<FieldSummary> getFieldSummary( PackageId packageId ) throws EslException {
        String path = template.urlFor( UrlTemplate.FIELD_SUMMARY_PATH )
                .replace( "{packageId}", packageId.getId() )
                .build();

        List<FieldSummary> fieldSummary;
        try {
            String stringResponse = client.get(path);


            fieldSummary = Serialization.fromJsonToList(stringResponse, FieldSummary.class);
        } catch ( Exception e ) {
            throw new EslException( "Could not get the field summary.", e );
        }
        return fieldSummary;
    }

}
