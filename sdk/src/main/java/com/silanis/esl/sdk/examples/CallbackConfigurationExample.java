package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CallbackConfiguration;
import com.silanis.esl.sdk.CallbackConfigurationRequest;
import com.silanis.esl.sdk.CallbackTestRequest;
import com.silanis.esl.sdk.CallbackTestResult;
import com.silanis.esl.sdk.service.CallbackConfigurationService;

import java.util.List;

import static com.silanis.esl.sdk.builder.CallbackConfigurationRequestBuilder.newCallbackConfigurationRequest;

/**
 * Manages the account callback with {@link CallbackConfigurationService}: creates or replaces it, then lists, tests,
 * changes, disables and deletes it.
 *
 * <p>Running this replaces, then deletes, the account's existing callback.
 */
public class CallbackConfigurationExample extends SDKSample {

    public static final String URL = "https://example.com/callback";
    public static final String KEY = "abc";

    public CallbackConfiguration saved, replaced, disabled;
    public List<CallbackConfiguration> configurations;
    public CallbackTestResult testResult;

    public static void main(String... args) {
        new CallbackConfigurationExample().run();
    }

    @Override
    public void execute() {
        CallbackConfigurationService service = eslClient.getCallbackConfigurationService();

        CallbackConfigurationRequest request = newCallbackConfigurationRequest()
                .withUrl(URL)
                .withAuthType("BASIC_AUTH")
                .withKey(KEY)
                .withEvents("PACKAGE_CREATE", "PACKAGE_COMPLETE")
                .build();
        // The account may already have its callback, e.g. from EventNotificationService; create would then fail with 409.
        CallbackConfiguration account = findAccountCallback(service.getCallbackConfigurations());
        saved = account == null
                ? service.createCallbackConfiguration(request)
                : service.replaceCallbackConfiguration(account.getId(), request);

        configurations = service.getCallbackConfigurations();

        testResult = service.testCallbackConfiguration(CallbackTestRequest.forSavedCallback(saved.getId()));

        // No key: the saved one is kept because the url does not change.
        replaced = service.replaceCallbackConfiguration(saved.getId(), newCallbackConfigurationRequest()
                .withUrl(URL)
                .withAuthType("BASIC_AUTH")
                .withEvents("PACKAGE_CREATE", "PACKAGE_COMPLETE", "PACKAGE_DECLINE")
                .build());

        disabled = service.disableCallbackConfiguration(saved.getId());

        service.deleteCallbackConfiguration(saved.getId());
    }

    private static CallbackConfiguration findAccountCallback(List<CallbackConfiguration> configurations) {
        for (CallbackConfiguration configuration : configurations) {
            if ("ACCOUNT".equals(configuration.getType())) {
                return configuration;
            }
        }
        return null;
    }
}
