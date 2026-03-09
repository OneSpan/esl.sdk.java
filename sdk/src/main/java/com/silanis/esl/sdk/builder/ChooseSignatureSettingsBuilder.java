package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.ChooseSignatureOptions;
import com.silanis.esl.sdk.ChooseSignatureSettings;

/**
 * Builder object used to customize the Choose Signature Settings.
 * <p>
 * This object configures Choose Signature Settings for the Signing UI.
 *
 */
public class ChooseSignatureSettingsBuilder {
    private ChooseSignatureOptions signatureOptions;

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
        this.signatureOptions = chooseSignatureOptions;
        return this;
    }

    /**
     * Builds the actual Choose Signature Settings.
     *
     * @return the Choose Signature Settings
     */
    public ChooseSignatureSettings build() {
        ChooseSignatureSettings result = new ChooseSignatureSettings();
        result.setSignature(signatureOptions);

        return result;
    }
}
