package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.service.EslComponent;

/**
 * Created by lena on 2014-08-29.
 */
public class EventNotificationApiClient extends EslComponent {

    public EventNotificationApiClient(RestClient restClient, String apiUrl) {
        super(restClient, apiUrl);
    }

    public void register(Callback callback) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CALLBACK_PATH).build();
        String packageJson = Serialization.toJson(callback);

        try {
            getClient().post(path, packageJson);
        } catch (RequestException rootException) {
            throw new EslServerException("Unable to register for event notification.", rootException);
        } catch (Exception rootException) {
            throw new EslException("Unable to register for event notification.", rootException);
        }
    }

    public void register(String origin, Callback callback) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CONNECTORS_CALLBACK_PATH)
                              .replace("{origin}", origin)
                              .build();
        String packageJson = Serialization.toJson(callback);

        try {
            getClient().post(path, packageJson);
        } catch (RequestException rootException) {
            throw new EslServerException("Unable to register event notification for this connector.", rootException);
        } catch (Exception rootException) {
            throw new EslException("Unable to register event notification for this connector.", rootException);
        }
    }

    public Callback getEventNotificationConfig() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CALLBACK_PATH).build();

        try {
            String stringResponse = getClient().get(path);
            return JacksonUtil.deserialize(stringResponse, Callback.class);
        } catch (RequestException e) {
            throw new EslServerException( "Could not retrieve event notification.", e);
        } catch (Exception e) {
            throw new EslException("Could not retrieve event notification." + " Exception: " + e.getMessage());
        }
    }

    public Callback getEventNotificationConfig(String origin) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CONNECTORS_CALLBACK_PATH)
                              .replace("{origin}", origin)
                              .build();

        try {
            String stringResponse = getClient().get(path);
            return JacksonUtil.deserialize(stringResponse, Callback.class);
        } catch (RequestException e) {
            throw new EslServerException( "Could not retrieve event notification for this connector.", e);
        } catch (Exception e) {
            throw new EslException("Could not retrieve event notification for this connector." + " Exception: " + e.getMessage());
        }
    }
}
