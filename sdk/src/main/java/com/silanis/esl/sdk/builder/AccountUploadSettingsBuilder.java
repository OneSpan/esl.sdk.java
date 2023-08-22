package com.silanis.esl.sdk.builder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.silanis.esl.api.model.Model;
import com.silanis.esl.sdk.AccountUploadSettings;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountUploadSettingsBuilder extends Model
                    implements java.io.Serializable{
    protected List<String> allowedFileTypes = null;

    public AccountUploadSettingsBuilder() {
    }

    /**
     * Creates a new Account upload Settings builder.
     *
     * @return This
     */
    public static AccountUploadSettingsBuilder newUploadSettings() {
        return new AccountUploadSettingsBuilder();
    }

    /**
     * Set allowedFileTypes in AccountUploadSettings.
     * <p>
     * DEFAULT: []
     * <p>
     *
     * @return This
     */
    public AccountUploadSettingsBuilder withAllowedFileTypes(List<String> value) {
        allowedFileTypes = value;
        return this;
    }

    public AccountUploadSettings build() {
        AccountUploadSettings result = new AccountUploadSettings();
        result.setAllowedFileTypes(allowedFileTypes);
        return result;
    }

}
