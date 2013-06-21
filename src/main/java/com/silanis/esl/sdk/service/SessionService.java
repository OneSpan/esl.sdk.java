package com.silanis.esl.sdk.service;

import com.silanis.awsng.web.rest.model.Signer;
import com.silanis.awsng.web.rest.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.internal.HttpMethods;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.net.URISyntaxException;

/**
 * The SessionService class provides a method to create a session token for a signer.
 */
public class SessionService {

    private String apiToken;
    private UrlTemplate template;
    private RestClient client;

    public SessionService(String apiToken, String baseUrl) {
        this.apiToken = apiToken;
        template = new UrlTemplate(baseUrl);
        client = new RestClient(apiToken);
    }

    /**
     * Creates a session token for a signer and returns the session token.
     *
     *
     *
     * @param packageId     The id of the package to which the newly created session shall have access.
     * @param signerId      The id of the signer to whom the newly created session shall belong.
     * @return The session token for signer
     * @throws com.silanis.esl.sdk.EslException
     */
    public SessionToken createSessionToken( String packageId, String signerId ) throws EslException {
        String path = template.urlFor(UrlTemplate.SESSION_PATH)
                .replace( "{packageId}", packageId )
                .replace("{signerId}", signerId)
                .build();
        String stringResponse;
        try {
            stringResponse = client.post(path, "");
        } catch (Exception e) {
            throw new EslException("Could not create a session token for signer.", e);
        }
        return Serialization.fromJson(stringResponse, SessionToken.class);
    }
}
