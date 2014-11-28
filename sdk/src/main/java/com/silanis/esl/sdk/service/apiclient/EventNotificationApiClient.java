package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;

/**
 * Created by lena on 2014-08-29.
 */
public class EventNotificationApiClient {

    private UrlTemplate template;
    private RestClient restClient;

    public EventNotificationApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public void register(Callback callback) {
        String path = template.urlFor(UrlTemplate.CALLBACK_PATH).build();
        String packageJson = Serialization.toJson(callback);

        try {
            restClient.post(path, packageJson);
        } catch (RequestException rootException) {
            throw new EslServerException("Unable to register for event notification.", rootException);
        } catch (Exception rootException) {
            throw new EslException("Unable to register for event notification.", rootException);
        }
    }

    public Callback getEventNotificationConfig() {
        String path = template.urlFor(UrlTemplate.CALLBACK_PATH).build();

        try {
            String stringResponse = restClient.get(path);
            return JacksonUtil.deserialize(stringResponse, Callback.class);
        } catch (RequestException e) {
            throw new EslServerException( "Could not retrieve event notification.", e);
        } catch (Exception e) {
            throw new EslException("Could not retrieve event notification." + " Exception: " + e.getMessage());
        }
    }
}
