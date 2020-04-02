package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Link;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

/**
 * Created by schoi on 2020-04-01.
 */
public class AccountConfigClient {
    private UrlTemplate template;
    private RestClient restClient;

    public AccountConfigClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        this.template = new UrlTemplate(apiUrl);
    }

    public Link getHandoverUrl(String language) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = restClient.get(path);

            Link apiResponse = Serialization.fromJson(stringResponse, Link.class);
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Could not get handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not get handover url.", e);
        }
    }

    public Link createHandoverUrl(String language, Link link) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(link);
            String stringResponse = restClient.post(path, json);

            Link apiResponse = Serialization.fromJson(stringResponse, Link.class);
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Could not create handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not create handover url.", e);
        }
    }

    public Link updateHandoverUrl(String language, Link link) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(link);
            String stringResponse = restClient.put(path, json);

            Link apiResponse = Serialization.fromJson(stringResponse, Link.class);
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Could not update handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not update handover url.", e);
        }
    }

    public void deleteHandoverUrl(String language) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete handover url.", e);
        }
    }
}