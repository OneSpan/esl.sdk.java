package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Role;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;

/**
 * Created by dave on 11/08/14.
 */
/**
 * @deprecated Please use the same methods in AttachmentRequirementService instead.
 */
@Deprecated
public class AttachmentRequirementApiClient {
    private UrlTemplate template;
    private RestClient restClient;

    public AttachmentRequirementApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public void acceptAttachment(String packageId, String roleId, Role apiRole) {
        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId)
                .replace("{roleId}", roleId)
                .build();

        try {
            String json = Serialization.toJson(apiRole);
            restClient.put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not accept attachment for signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not accept attachment for signer." + " Exception: " + e.getMessage());
        }
    }

    public void rejectAttachment(String packageId, Role apiRole) {
        String path = template.urlFor(UrlTemplate.SIGNER_PATH)
                .replace("{packageId}", packageId)
                .replace("{roleId}", apiRole.getId())
                .build();

        try {
            String json = Serialization.toJson(apiRole);
            restClient.put(path, json);
        } catch (RequestException e){
            throw new EslServerException( "Could not reject attachment for signer.", e);
        } catch (Exception e) {
            throw new EslException("Could not reject attachment for signer." + " Exception: " + e.getMessage());
        }
    }
}
