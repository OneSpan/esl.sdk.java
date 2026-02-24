package com.silanis.esl.sdk.builder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silanis.esl.sdk.ChooseSignatureOptions;
import com.silanis.esl.sdk.ChooseSignatureSettings;
import com.silanis.esl.sdk.ChooseSignatureStyleType;

/**
 * Builder object used to customize the Choose Signature Settings.
 * <p>
 * This object configures Choose Signature Settings for the Signing UI.
 *
 */
public class ChooseSignatureSettingsBuilder {
    private ChooseSignatureOptions chooseSignatureOptions;

    /**
     * Creates a new Choose Signature Settings builder.
     *
     * @return This
     */
    public static ChooseSignatureSettingsBuilder newChooseSignatureSettings() {
        return new ChooseSignatureSettingsBuilder();
    }

    private ChooseSignatureSettingsBuilder() {
    }

    public ChooseSignatureSettingsBuilder withChooseSignatureOptions(ChooseSignatureOptions chooseSignatureOptions) {
        this.chooseSignatureOptions = chooseSignatureOptions;
        return this;
    }

    /**
     * Builds the actual Choose Signature Settings.
     *
     * @return the Choose Signature Settings
     */
    public ChooseSignatureSettings build() {
        ChooseSignatureSettings result = new ChooseSignatureSettings();
        result.setChooseSignatureOptions(chooseSignatureOptions);

        return result;
    }
}
