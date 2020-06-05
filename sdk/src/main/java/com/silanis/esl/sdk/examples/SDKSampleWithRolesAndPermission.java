package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.internal.Converter;

import java.util.Collections;

public abstract class SDKSampleWithRolesAndPermission extends SDKSample{
    public SDKSampleWithRolesAndPermission() {
        super();
        activateRolesAndPermissionProperties();
    }

    private void activateRolesAndPermissionProperties() {
        props.setProperty("sender.email", props.getProperty("sender.email.WithRolesAndPermission"));
        props.setProperty("api.key", props.getProperty("api.key.WithRolesAndPermission"));
        senderEmail = props.getProperty("sender.email");
        senderUID = Converter.apiKeyToUID(props.getProperty("api.key"));
        setupEslClientFromProps(Collections.<String,String>emptyMap(), null);
    }

}
