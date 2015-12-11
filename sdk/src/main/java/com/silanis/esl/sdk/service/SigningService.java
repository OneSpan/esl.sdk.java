package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.SignedDocuments;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.*;

/**
 * Created by schoi on 12/7/15.
 */
public class SigningService {
    private final UrlTemplate template;
    private final RestClient client;

    public SigningService( RestClient client, String baseUrl ) {
        this.template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    public void signDocument( PackageId packageId, com.silanis.esl.api.model.Document document ) {
        String path = template.urlFor( UrlTemplate.SIGN_DOCUMENT_PATH )
                              .replace("{packageId}", packageId.getId() )
                              .build();

        try {
            client.post( path, Serialization.toJson(document) );
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to sign a document.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to sign a document.", e );
        }
    }

    public void signDocuments( PackageId packageId, SignedDocuments signedDocumentses ) {
        String path = template.urlFor( UrlTemplate.SIGN_DOCUMENTS_PATH ).replace( "{packageId}", packageId.getId() ).build();
        try {
            client.post( path, Serialization.toJson(signedDocumentses) );
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to sign documents.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to sign documents.", e );
        }
    }
}
