package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;

/**
 * Created by chi-wing on 6/19/14.
 */

/**
 *
 */
public class ApprovalService {
    private UrlTemplate template;
    private RestClient client;

    public ApprovalService(RestClient client, String baseUrl) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }


    /**
     * Delete a signature from a document
     *
     * @param packageId The package Id
     * @param documentId The document Id
     * @param approvalId The approval Id
     * @throws EslException
     */
    public void deleteApproval(PackageId packageId, String documentId, String approvalId) throws EslException {
        String path = template.urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", approvalId)
                .build();
        try {
            client.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete signature from document.", e);
        }
    }


}
