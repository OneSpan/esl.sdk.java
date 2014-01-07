package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.Translation;
import com.silanis.esl.sdk.builder.CustomFieldBuilder;
import com.silanis.esl.sdk.builder.TranslationBuilder;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 1:15 PM
 *
 * Converter for SDK and API Custom Field.
 */
public class CustomFieldConverter {

    private com.silanis.esl.sdk.CustomField sdkCustomField = null;
    private com.silanis.esl.api.model.CustomField apiCustomField = null;

    /**
     * Construct with SDK object involved in conversion.
     */
    public CustomFieldConverter(com.silanis.esl.sdk.CustomField sdkCustomField) {
        this.sdkCustomField = sdkCustomField;
    }


    /**
     * Construct with API object involved in conversion.
     *
     * @param apiCustomField
     */
    public CustomFieldConverter(com.silanis.esl.api.model.CustomField apiCustomField) {
       this.apiCustomField = apiCustomField;
    }

    /**
     * Convert from SDK to API custom field.
     *
     * @return API CustomField.
     */
    public com.silanis.esl.api.model.CustomField toAPICustomField() {

        if (sdkCustomField == null) {
            return apiCustomField;
        }
        com.silanis.esl.api.model.CustomField result = new com.silanis.esl.api.model.CustomField();

        result.setId( sdkCustomField.getId() );
        result.setValue( sdkCustomField.getValue() );
        result.setRequired(sdkCustomField.getRequired());

        for ( Translation translation : sdkCustomField.getTranslations() ) {
            result.addTranslation( translation.toAPITranslation() );
        }

        return result;
    }

    /**
     * Convert from API to SDK custom field.
     *
     * @return SDK CustomField.
     */
    public com.silanis.esl.sdk.CustomField toSDKCustomField() {
        if (apiCustomField == null) {
            return sdkCustomField;
        }
        CustomFieldBuilder result = new CustomFieldBuilder();
        result.withId( apiCustomField.getId() )
              .withDefaultValue(apiCustomField.getValue())
              .isRequired( apiCustomField.evalRequired() );

        for ( com.silanis.esl.api.model.Translation tran : apiCustomField.getTranslations() ) {
            result.withTranslation( TranslationBuilder.newTranslation(tran) );
        }
        return result.build();
    }

}
