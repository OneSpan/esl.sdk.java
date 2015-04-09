package com.silanis.esl.sdk.service;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

import java.util.Properties;

/**
 * Created by schoi on 3/31/15.
 */
public class SystemService {

    private UrlTemplate template;
    private RestClient client;

    public SystemService(RestClient client, String baseUrl) {
        this.client = client;
        template = new UrlTemplate(baseUrl);
    }

    public String getApplicationVersion() {
        String path = template.urlFor(UrlTemplate.SYSTEM_PATH)
                              .build();

        try{
            String json = client.get(path);
            Properties systemInfo = Serialization.fromJson(json, Properties.class);
            return systemInfo.getProperty("version");

        } catch (RequestException e) {
            throw new EslException("Could not get application version.", e);
        } catch (Exception e) {
            throw new EslException("Could not get application version." + " Exception: " + e.getMessage());
        }
    }
}
