package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Handover;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

import java.util.List;

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

    public Handover getHandoverUrl(String language) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = restClient.get(path);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not get handover url.", e);
        }
    }

    public Handover createHandoverUrl(String language, Handover handover) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(handover);
            String stringResponse = restClient.post(path, json);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not create handover url.", e);
        }
    }

    public Handover updateHandoverUrl(String language, Handover handover) {
        String path = template.urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(handover);
            String stringResponse = restClient.put(path, json);

            return Serialization.fromJson(stringResponse, Handover.class);
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

    public List<String> createDeclineReasons(String language, List<String> declineReasons) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        String json = Serialization.toJson(declineReasons);

        try {
            String stringResponse = restClient.post(path, json);
            return Serialization.fromJsonToList(stringResponse, String.class);

        } catch (RequestException e) {
            throw new EslServerException("Could not create decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not create decline reasons.", e);
        }
    }

    public List<String> updateDeclineReasons(String language, List<String> declineReasons) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        String json = Serialization.toJson(declineReasons);

        try {
            String stringResponse = restClient.put(path, json);
            return Serialization.fromJsonToList(stringResponse, String.class);

        } catch (RequestException e) {
            throw new EslServerException("Could not update decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not update decline reasons.", e);
        }
    }

    public List<String> getDeclineReasons(String language) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, String.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not get decline reasons.", e);
        }
    }

    public void deleteDeclineReasons(String language) {
        String path = template.urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete decline reasons.", e);
        }
    }
}