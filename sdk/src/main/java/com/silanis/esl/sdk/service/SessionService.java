package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.SessionToken;
import com.silanis.esl.sdk.internal.*;

/**
 * The SessionService class provides a method to create a session token for a signer.
 */
public class SessionService extends EslComponent {

    public SessionService(RestClient client, String baseUrl) {
        super(client, baseUrl);
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
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SESSION_PATH)
                .replace( "{packageId}", packageId )
                .replace("{signerId}", signerId)
                .build();
        String stringResponse;
        try {
            stringResponse = getClient().post(path, "");
        } catch (RequestException e) {
            throw new EslServerException("Could not create a session token for signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a session token for signer.", e);
        }
        return Serialization.fromJson(stringResponse, SessionToken.class);
    }

    public SessionToken createSenderSessionToken() throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SENDER_SESSION_PATH)
                .build();
        String stringResponse;
        try {
            stringResponse = getClient().post(path, "");
        } catch (RequestException e) {
            throw new EslServerException("Could not create a session token for sender.", e);
        } catch (Exception e) {
            throw new EslException("Could not create a session token for sender.", e);
        }
        return Serialization.fromJson(stringResponse, SessionToken.class);
    }
}
