package com.silanis.esl.sdk.builder;

import static com.silanis.esl.sdk.ChooseSignatureStyleType.STYLE;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.silanis.esl.sdk.ChooseSignatureOptions;

/**
 * Builder object used to customize the Choose Signature Options.
 * <p>
 * This object configures certain actions are allowed on the Signing UI.
 *
 */
public class ChooseSignatureOptionsBuilder {
    private Boolean allowStyling = true;
    private Boolean allowDrawing = true;
    private Boolean allowUploading = true;
    private Boolean allowMobileSigning = true;
    private String defaultSignatureType = STYLE.name();
    private Map<String, List<String>> fontsPerWritingSystem = new HashMap<>();

    /**
     * Creates a new Choose Signature Options builder.
     *
     * @return This
     */
    public static ChooseSignatureOptionsBuilder newChooseSignatureOptions() {
        return new ChooseSignatureOptionsBuilder();
    }

    private ChooseSignatureOptionsBuilder() {
    }

    public ChooseSignatureOptionsBuilder allowStyling() {
        allowStyling = true;
        return this;
    }

    public ChooseSignatureOptionsBuilder disableStyling() {
        allowStyling = false;
        return this;
    }

    public ChooseSignatureOptionsBuilder allowDrawing() {
        allowDrawing = true;
        return this;
    }

    public ChooseSignatureOptionsBuilder disableDrawing() {
        allowDrawing = false;
        return this;
    }

    public ChooseSignatureOptionsBuilder allowUploading() {
        allowUploading = true;
        return this;
    }

    public ChooseSignatureOptionsBuilder disableUploading() {
        allowUploading = false;
        return this;
    }

    public ChooseSignatureOptionsBuilder allowMobileSigning() {
        allowMobileSigning = true;
        return this;
    }

    public ChooseSignatureOptionsBuilder disableMobileSigning() {
        allowMobileSigning = false;
        return this;
    }

    public ChooseSignatureOptionsBuilder withDefaultSignatureType(String chooseSignatureStyleType) {
        this.defaultSignatureType = chooseSignatureStyleType;
        return this;
    }

    public ChooseSignatureOptionsBuilder withFontsPerWritingSystem(Map<String, List<String>> fontsPerWritingSystem) {
        if(fontsPerWritingSystem != null) {
            this.fontsPerWritingSystem = fontsPerWritingSystem;
        }
        return this;
    }

    /**
     * Builds the actual Choose Signature options.
     *
     * @return the Choose Signature options
     */
    public ChooseSignatureOptions build() {
        ChooseSignatureOptions result = new ChooseSignatureOptions();
        result.setAllowStyling(allowStyling);
        result.setAllowDrawing(allowDrawing);
        result.setAllowUploading(allowUploading);
        result.setAllowMobileSigning(allowMobileSigning);
        result.setDefaultSignatureType(defaultSignatureType);
        result.setFontsPerWritingSystem(fontsPerWritingSystem);

        return result;
    }
}
