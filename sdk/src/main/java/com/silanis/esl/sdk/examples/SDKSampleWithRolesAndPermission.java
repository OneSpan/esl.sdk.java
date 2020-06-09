package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.internal.Converter;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.ProxyConfiguration;
import com.silanis.esl.sdk.apitoken.ApiTokenConfig;

import java.util.Collections;
import java.util.Map;

public abstract class SDKSampleWithRolesAndPermission extends SDKSample{
    protected EslClient eslClientForRolesAndPermission;
    public String senderEmailForRolesAndPermission,
            senderSmsForRolesAndPermission, senderUIDForRolesAndPermission;

    public SDKSampleWithRolesAndPermission() {
        super();
        activateRolesAndPermissionProperties();
    }

    private void activateRolesAndPermissionProperties() {
        senderEmailForRolesAndPermission = props.getProperty("sender.email.WithRolesAndPermission");
        senderSmsForRolesAndPermission = props.getProperty("sender.sms.WithRolesAndPermission");
        senderUIDForRolesAndPermission = Converter.apiKeyToUID(props.getProperty("api.key.WithRolesAndPermission"));

        setupEslClientFromProps(Collections.<String,String>emptyMap(), null);
    }

    public EslClient setupEslClientFromProps(Map<String,String> additionalHeaders, ProxyConfiguration proxyConfiguration) {
        if (props.getProperty("api.clientId.WithRolesAndPermission") == null) {
            eslClientForRolesAndPermission = new EslClient(props.getProperty("api.key.WithRolesAndPermission"), props.getProperty("api.url"), true, proxyConfiguration).setWebpageURL(props.getProperty("webpage.url"));
        } else {
            ApiTokenConfig apiTokenConfig = ApiTokenConfig.newBuilder()
                    .clientAppId(props.getProperty("api.clientId.WithRolesAndPermission"))
                    .clientAppSecret(props.getProperty("api.secret.WithRolesAndPermission"))
                    .tokenType(ApiTokenConfig.TokenType.OWNER)
                    .baseUrl(props.getProperty("webpage.url"))
                    .build();
            eslClientForRolesAndPermission = new EslClient(apiTokenConfig,  props.getProperty("api.url"), true, proxyConfiguration, true, additionalHeaders);
        }
        return eslClientForRolesAndPermission;
    }

}
