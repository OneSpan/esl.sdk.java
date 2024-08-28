package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.service.EslComponent;

/**
 * Created by lena on 2014-08-28.
 */
public class AuditApiClient extends EslComponent {

    public AuditApiClient(RestClient restClient, String apiUrl) {
        super(restClient, apiUrl);
    }

    public String getAudit(String packageId) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.AUDIT_PATH)
                .replace("{packageId}", packageId)
                .build();
        try {
            return getClient().get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get audit.", e);
        } catch (Exception e) {
            throw new EslException("Could not get audit.", e);
        }
    }
}
