package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.EventNotificationConfig;
import com.silanis.esl.sdk.builder.EventNotificationConfigBuilder;
import com.silanis.esl.sdk.internal.*;

/**
 * This class is used for registering to the e-SL notification system 
 */
public class EventNotificationService {

    private final UrlTemplate template;
    private final RestClient client;

    public EventNotificationService( RestClient client, String baseUrl ) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
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
        } catch ( RequestException rootException ) {
            throw new EslServerException( "Unable to register for event notification.", rootException );
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
