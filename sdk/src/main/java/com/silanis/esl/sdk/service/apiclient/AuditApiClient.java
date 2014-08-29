package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;

/**
 * Created by lena on 2014-08-28.
 */
public class AuditApiClient {

    private UrlTemplate template;
    private RestClient restClient;

    public AuditApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public String getAudit(String packageId) throws EslException {
        String path = template.urlFor(UrlTemplate.AUDIT_PATH)
                .replace("{packageId}", packageId)
                .build();
        try {
            return restClient.get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get audit.", e);
        } catch (Exception e) {
            throw new EslException("Could not get audit.", e);
        }
    }
}
