package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Verification;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by aafrasiabian on 23/05/17.
 */
public class SignerVerificationService {

    private UrlTemplate template;
    private RestClient client;

    public SignerVerificationService(RestClient client, String baseUrl) {
        template = new UrlTemplate(baseUrl);
        this.client = client;
    }

    public Verification createSignerVerification(String packageId, String roleId, Verification verification) throws EslException {
        String path = template.urlFor(UrlTemplate.ADD_SIGNER_VERIFICATION_PATH)
                .replace("{packageId}", packageId)
                .replace("{roleId}", roleId)
                .build();

        String verificationJson = Serialization.toJson(verification);

        try {
            String response = client.post(path, verificationJson);
            return Serialization.fromJson(response, Verification.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create a signer verification", e);
        } catch (Exception e) {
            throw new EslException("Could not create a signer verification", e);
        }
    }

    public Verification updateSignerVerification(String packageId, String roleId, Verification verification) throws EslException {
        String path = template.urlFor(UrlTemplate.UPDATE_SIGNER_VERIFICATION_PATH)
                .replace("{packageId}", packageId)
                .replace("{roleId}", roleId)
                .build();

        String verificationJson = Serialization.toJson(verification);

        try {
            String response = client.put(path, verificationJson);
            return Serialization.fromJson(response, Verification.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not update the signer verification", e);
        } catch (Exception e) {
            throw new EslException("Could not update the signer verification", e);
        }
    }

    public void deleteSignerVerification(String packageId, String roleId) throws EslException {
        String path = template.urlFor(UrlTemplate.DELETE_SIGNER_VERIFICATION_PATH)
                .replace("{packageId}", packageId)
                .replace("{roleId}", roleId)
                .build();

        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete a signer verification", e);
        } catch (Exception e) {
            throw new EslException("Could not delete a signer verification", e);
        }
    }

    public Verification getSignerVerification(String packageId, String roleId) throws EslException {
        String path = template.urlFor(UrlTemplate.GET_SIGNER_VERIFICATION_PATH)
                .replace("{packageId}", packageId)
                .replace("{roleId}", roleId)
                .build();

        String stringResponse;
        try {
            stringResponse = client.get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get signer verification.", e);
        } catch (Exception e) {
            throw new EslException("Could not get signer verification.", e);
        }

        if(StringUtils.isBlank(stringResponse)) {
            return null;
        }

        return Serialization.fromJson(stringResponse, Verification.class);
    }


}
