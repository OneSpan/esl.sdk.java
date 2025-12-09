package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Handover;
import com.silanis.esl.api.model.IntegrationFrameworkWorkflow;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.EslServerException;
import com.silanis.esl.sdk.internal.RequestException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.AccountDesignerSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountLimitSupportedLanguagesSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountPackageSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountFeatureSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountEmailReminderSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountUploadSettingsConverter;
import com.silanis.esl.sdk.internal.converter.AccountSystemSettingPropertiesConverter;
import com.silanis.esl.sdk.internal.converter.IntegrationFrameworkWorkflowConverter;
import com.silanis.esl.sdk.internal.converter.SignatureLayoutConverter;
import org.apache.commons.lang3.StringUtils;
import com.silanis.esl.sdk.service.EslComponent;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * Created by schoi on 2020-04-01.
 */
public class AccountConfigClient extends EslComponent {

    public AccountConfigClient(RestClient restClient, String apiUrl) {
        super(restClient, apiUrl);
    }

    public Handover getHandoverUrl(String language) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = getClient().get(path);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not get handover url.", e);
        }
    }

    public Handover createHandoverUrl(String language, Handover handover) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(handover);
            String stringResponse = getClient().post(path, json);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not create handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not create handover url.", e);
        }
    }

    public Handover updateHandoverUrl(String language, Handover handover) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String json = Serialization.toJson(handover);
            String stringResponse = getClient().put(path, json);

            return Serialization.fromJson(stringResponse, Handover.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not update handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not update handover url.", e);
        }
    }

    public void deleteHandoverUrl(String language) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.HANDOVER_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete handover url.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete handover url.", e);
        }
    }

    public List<String> createDeclineReasons(String language, List<String> declineReasons) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        String json = Serialization.toJson(declineReasons);

        try {
            String stringResponse = getClient().post(path, json);
            return Serialization.fromJsonToList(stringResponse, String.class);

        } catch (RequestException e) {
            throw new EslServerException("Could not create decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not create decline reasons.", e);
        }
    }

    public List<String> updateDeclineReasons(String language, List<String> declineReasons) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        String json = Serialization.toJson(declineReasons);

        try {
            String stringResponse = getClient().put(path, json);
            return Serialization.fromJsonToList(stringResponse, String.class);

        } catch (RequestException e) {
            throw new EslServerException("Could not update decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not update decline reasons.", e);
        }
    }

    public List<String> getDeclineReasons(String language) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            String stringResponse = getClient().get(path);
            return Serialization.fromJsonToList(stringResponse, String.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not get decline reasons.", e);
        }
    }

    public void deleteDeclineReasons(String language) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.DECLINE_REASONS_URL_PATH)
                .replace("{language}", language)
                .build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete decline reasons.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete decline reasons.", e);
        }
    }

    /**
     * Get account settings.
     *
     */
    public AccountSettings getAccountSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountSettings.class)).toSDKAccountSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account settings.", e);
        }
    }

    /**
     * Save account settings.
     *
     */
    public void saveAccountSettings(AccountSettings accountSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountSettings);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account settings.", e);
        }
    }

    /**
     * Delete account settings.
     *
     */
    public void deleteAccountSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account settings.", e);
        }
    }

    /**
     * Get account package settings.
     *
     */
    public AccountPackageSettings getAccountPackageSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_PACKAGE_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountPackageSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountPackageSettings.class)).toSDKAccountPackageSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account package settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account package settings.", e);
        }
    }

    /**
     * Save account package settings.
     *
     */
    public void saveAccountPackageSettings(AccountPackageSettings accountPackageSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_PACKAGE_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountPackageSettings);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account package settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account package settings.", e);
        }
    }

    /**
     * Delete account package settings.
     *
     */
    public void deleteAccountPackageSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_PACKAGE_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account package settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account package settings.", e);
        }
    }

    /**
     * Get account feature settings.
     *
     */
    public AccountFeatureSettings getAccountFeatureSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_FEATURE_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountFeatureSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountFeatureSettings.class)).toSDKAccountFeatureSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account feature settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account feature settings.", e);
        }
    }

    /**
     * Save account feature settings.
     *
     */
    public void saveAccountFeatureSettings(AccountFeatureSettings accountFeatureSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_FEATURE_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountFeatureSettings);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account feature settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account feature settings.", e);
        }
    }

    /**
     * Delete account feature settings.
     *
     */
    public void deleteAccountFeatureSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_FEATURE_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account feature settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account feature settings.", e);
        }
    }

    /**
     * Get account designer settings.
     *
     */
    public com.silanis.esl.sdk.AccountDesignerSettings getAccountDesignerSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_DESIGNER_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountDesignerSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountDesignerSettings.class)).tosdkAccountDesignerSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account designer settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account designer settings.", e);
        }
    }

    /**
     * Save account designer settings.
     *
     */
    public void saveAccountDesignerSettings(com.silanis.esl.sdk.AccountDesignerSettings accountDesignerSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_DESIGNER_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountDesignerSettings);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account designer settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account designer settings.", e);
        }
    }

    /**
     * Delete account designer settings.
     *
     */
    public void deleteAccountDesignerSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_DESIGNER_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account designer settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account designer settings.", e);
        }
    }

    /**
     * Get account email reminder settings.
     *
     */
    public com.silanis.esl.sdk.AccountEmailReminderSettings getAccountEmailReminderSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_EMAIL_REMINDER_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountEmailReminderSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountEmailReminderSettings.class)).tosdkAccountEmailReminderSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account email reminder settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account email reminder settings.", e);
        }
    }

    /**
     * Save account email reminder settings.
     *
     */
    public void saveAccountEmailReminderSettings(com.silanis.esl.sdk.AccountEmailReminderSettings accountEmailReminderSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_EMAIL_REMINDER_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountEmailReminderSettings);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account email reminder settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account email reminder settings.", e);
        }
    }

    /**
     * Delete account email reminder settings.
     *
     */
    public void deleteAccountEmailReminderSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_EMAIL_REMINDER_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account email reminder settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account email reminder settings.", e);
        }
    }

    /**
     * Get account upload settings.
     *
     */
    public com.silanis.esl.sdk.AccountUploadSettings getAccountUploadSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_UPLOAD_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountUploadSettingsConverter(com.silanis.esl.api.model.AccountUploadSettings.class.newInstance().setAllowedFileTypes( Arrays.asList(stringResponse.replaceAll("[\\[\\]]", "").split(",")))).
                    tosdkAccountUploadSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account upload settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account upload settings.", e);
        }
    }

    /**
     * Save account upload settings.
     *
     */
    public void saveAccountUploadSettings(com.silanis.esl.sdk.AccountUploadSettings accountUploadSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_UPLOAD_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountUploadSettings.getAllowedFileTypes());
        try {
            getClient().put(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account upload settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account upload settings.", e);
        }
    }

    /**
     * Delete account upload settings.
     *
     */
    public void deleteAccountUploadSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_UPLOAD_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account upload settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account upload settings.", e);
        }
    }

    /**
     * Get account system settings.
     *
     */
    public com.silanis.esl.sdk.AccountSystemSettingProperties getAccountSystemSettingProperties() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SYSTEM_SETTING_PROPERTIES_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new AccountSystemSettingPropertiesConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.AccountSystemSettingProperties.class)).tosdkAccountSystemSettingProperties();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account system settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account system settings.", e);
        }
    }

    /**
     * Save account system settings.
     *
     */
    public void saveAccountSystemSettingProperties(com.silanis.esl.sdk.AccountSystemSettingProperties accountSystemSettingProperties) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SYSTEM_SETTING_PROPERTIES_PATH).build();
        String payload = JacksonUtil.serialize(accountSystemSettingProperties);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account system settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account system settings.", e);
        }
    }

    /**
     * Delete account system settings.
     *
     */
    public void deleteAccountSystemSettingProperties() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SYSTEM_SETTING_PROPERTIES_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account system settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account system settings.", e);
        }
    }

    /**
     * Get account signature layout.
     *
     */
    public com.silanis.esl.sdk.SignatureLayout getAccountSignatureLayout() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SIGNATURE_LAYOUT_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            return new SignatureLayoutConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.SignatureLayout.class)).toSDKSignatureLayout();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account signature layout.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account signature layout.", e);
        }
    }

    /**
     * Save account signature layout.
     *
     */
    public void saveAccountSignatureLayout(com.silanis.esl.sdk.SignatureLayout signatureLayout) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_SIGNATURE_LAYOUT_PATH).build();
        String payload = JacksonUtil.serialize(signatureLayout);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account signature layout.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account signature layout.", e);
        }
    }

    /**
     * Get account IfWorkflows configs.
     *
     */
    public List<com.silanis.esl.sdk.IntegrationFrameworkWorkflow> getIfWorkflowsConfigs() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.IF_WORKFLOW_CONFIGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);

            return IntegrationFrameworkWorkflowConverter.toSDKList(Serialization.fromJsonToList(stringResponse, IntegrationFrameworkWorkflow.class));
        } catch (RequestException e) {
            throw new EslServerException("Could not get IfWorkflows Configs.", e);
        } catch (Exception e) {
            throw new EslException("Could not get IfWorkflows Configs.", e);
        }
    }

    /**
     * Get account limit supported languages settings.
     *
     */
    public com.silanis.esl.sdk.SupportedLanguages getAccountLimitSupportedLanguagesSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_LIMIT_SUPPORTED_LANGUAGES_SETTINGS_PATH).build();
        try {
            String stringResponse = getClient().get(path);
            if(StringUtils.isEmpty(stringResponse)){
                return new SupportedLanguages();
            }
            return new AccountLimitSupportedLanguagesSettingsConverter(Serialization.fromJson(stringResponse, com.silanis.esl.api.model.SupportedLanguages.class)).tosdkAccountLimitSupportedLanguagesSettings();
        } catch (RequestException e) {
            throw new EslServerException("Could not get the account limit supported languages settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the account limit supported languages settings.", e);
        }
    }

    /**
     * Save account limit supported languages settings.
     *
     */
    public void saveAccountLimitSupportedLanguagesSettings(com.silanis.esl.sdk.SupportedLanguages accountLimitSupportedLanguagesSettings) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_LIMIT_SUPPORTED_LANGUAGES_SETTINGS_PATH).build();
        String payload = JacksonUtil.serialize(accountLimitSupportedLanguagesSettings);
        try {
            getClient().patch(path, payload);
        } catch (RequestException e) {
            throw new EslServerException("Could not save the account limit supported languages settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not save the account limit supported languages settings.", e);
        }
    }

    /**
     * Delete limit supported languages settings.
     *
     */
    public void deleteAccountLimitSupportedLanguagesSettings() {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_LIMIT_SUPPORTED_LANGUAGES_SETTINGS_PATH).build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the account limit supported languages settings.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the account limit supported languages settings.", e);
        }
    }
}