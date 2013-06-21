package com.silanis.esl.sdk.service;

import com.silanis.awsng.web.rest.model.Callback;
import com.silanis.awsng.web.rest.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.builder.EventNotificationConfigBuilder;
import com.silanis.esl.sdk.internal.HttpMethods;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import org.apache.http.entity.StringEntity;

/**
 * This class is used for registering to the e-SL notification system 
 */
public class EventNotificationService {
    private String apiToken;
    private UrlTemplate template;
    private RestClient client;

    public EventNotificationService( String apiToken, String baseUrl ) {
        this.apiToken = apiToken;
        template = new UrlTemplate( baseUrl );
        client = new RestClient(apiToken);
    }

    /**
     * Registers to receive notifications sent by e-SL that are described by the config parameter passed to this method
     * 
     * @param config	Describes the event notification of interest
     */
    public void register( EventNotificationConfig config ) {
        String path = template.urlFor( UrlTemplate.CALLBACK_PATH ).build();
        Callback callback = config.toAPICallback();
        String packageJson = Serialization.toJson(callback);

        try {
            client.post(path, packageJson );
        } catch ( Exception rootException ) {
            throw new EslException( "Unable to register for event notification.", rootException );
        }
    }

    /**
     * <p>Registers to receive notifications sent by e-SL.<p>
     * <p>The builder parameter of this method is convenient to use when you want to easily add more notification events.</p>
     * 
     * @param builder
     */
    public void register( EventNotificationConfigBuilder builder ) {
        register( builder.build() );
    }
}
