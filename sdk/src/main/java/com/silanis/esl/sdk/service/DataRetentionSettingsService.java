package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.DataManagementPolicy;
import com.silanis.esl.api.model.ExpiryTimeConfiguration;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.DataManagementPolicyConverter;
import com.silanis.esl.sdk.internal.converter.ExpiryTimeConfigurationConverter;

public class DataRetentionSettingsService extends EslComponent {

    public DataRetentionSettingsService(RestClient client, String baseUrl) {
        super(client, baseUrl);
    }

    /**
     * Gets the expiry time configuration.
     *
     * @return ExpiryTimeConfiguration
     */
    public com.silanis.esl.sdk.ExpiryTimeConfiguration getExpiryTimeConfiguration() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.EXPIRY_TIME_CONFIGURATION_PATH)
                .build();
        String stringResponse;
        try {
            stringResponse = getClient().get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get expiryTimeConfiguration.", e);
        } catch (Exception e) {
            throw new EslException("Could not get expiryTimeConfiguration.", e);
        }

        ExpiryTimeConfiguration expiryTimeConfiguration =  Serialization.fromJson(stringResponse, ExpiryTimeConfiguration.class);
        ExpiryTimeConfigurationConverter converter = new ExpiryTimeConfigurationConverter(expiryTimeConfiguration);
        return converter.toSDKExpiryTimeConfiguration();
    }

    /**
     * Update expiry time configuration for account.
     *
     * @param expiryTimeConfiguration
     */
    public void setExpiryTimeConfiguration(com.silanis.esl.sdk.ExpiryTimeConfiguration expiryTimeConfiguration) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.EXPIRY_TIME_CONFIGURATION_PATH)
                .build();
        ExpiryTimeConfigurationConverter converter = new ExpiryTimeConfigurationConverter(expiryTimeConfiguration);
        String expiryTimeConfigurationJson = Serialization.toJson(converter.toAPIExpiryTimeConfiguration());

        try {
            getClient().put(path, expiryTimeConfigurationJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update expiryTimeConfiguration", e);
        } catch (Exception e) {
            throw new EslException("Could not update expiryTimeConfiguration", e);
        }
    }

    /**
     * Gets the data management policy.
     *
     * @return DataManagementPolicy
     */
    public com.silanis.esl.sdk.DataManagementPolicy getDataManagementPolicy() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.DATA_MANAGEMENT_POLICY_PATH)
                .build();
        String stringResponse;
        try {
            stringResponse = getClient().get(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not get dataManagementPolicy.", e);
        } catch (Exception e) {
            throw new EslException("Could not get dataManagementPolicy.", e);
        }

        DataManagementPolicy dataManagementPolicy =  Serialization.fromJson(stringResponse, DataManagementPolicy.class);
        DataManagementPolicyConverter converter = new DataManagementPolicyConverter(dataManagementPolicy);
        return converter.toSDKDataManagementPolicy();
    }

    /**
     * Update data management policy for account.
     *
     * @param dataManagementPolicy
     */
    public void setDataManagementPolicy(com.silanis.esl.sdk.DataManagementPolicy dataManagementPolicy) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.DATA_MANAGEMENT_POLICY_PATH)
                .build();
        DataManagementPolicyConverter converter = new DataManagementPolicyConverter(dataManagementPolicy);
        String dataManagementPolicyJson = Serialization.toJson(converter.toAPIDataManagementPolicy());

        try {
            getClient().put(path, dataManagementPolicyJson);
        } catch (RequestException e) {
            throw new EslServerException("Could not update dataManagementPolicy", e);
        } catch (Exception e) {
            throw new EslException("Could not update dataManagementPolicy", e);
        }
    }
}
