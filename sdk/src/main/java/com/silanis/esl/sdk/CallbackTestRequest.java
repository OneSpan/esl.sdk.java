package com.silanis.esl.sdk;

/**
 * A callback configuration to test without saving it.
 *
 * <p>For a saved callback, set only what you want to change: the rest comes from the saved callback, and its secrets
 * are reused under the conditions on
 * {@link com.silanis.esl.sdk.service.CallbackConfigurationService#replaceCallbackConfiguration}. For a new
 * configuration, set everything it needs, as for create.
 */
public class CallbackTestRequest {

    private final Long callbackId;
    private final CallbackConfigurationRequest configuration;

    private CallbackTestRequest(Long callbackId, CallbackConfigurationRequest configuration) {
        this.callbackId = callbackId;
        this.configuration = configuration;
    }

    /**
     * Tests the saved callback {@code callbackId} as it is.
     */
    public static CallbackTestRequest forSavedCallback(long callbackId) {
        return new CallbackTestRequest(callbackId, new CallbackConfigurationRequest());
    }

    /**
     * Tests the saved callback {@code callbackId} with {@code changes} applied.
     */
    public static CallbackTestRequest forSavedCallback(long callbackId, CallbackConfigurationRequest changes) {
        return new CallbackTestRequest(callbackId, changes);
    }

    /**
     * Tests a new configuration that is not saved.
     */
    public static CallbackTestRequest forConfiguration(CallbackConfigurationRequest configuration) {
        return new CallbackTestRequest(null, configuration);
    }

    public Long getCallbackId() {
        return callbackId;
    }

    public CallbackConfigurationRequest getConfiguration() {
        return configuration;
    }
}
