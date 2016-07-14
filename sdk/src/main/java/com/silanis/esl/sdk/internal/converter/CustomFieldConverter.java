package com.silanis.esl.sdk.internal.converter;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.silanis.esl.sdk.Translation;
import com.silanis.esl.sdk.builder.CustomFieldBuilder;
import com.silanis.esl.sdk.builder.TranslationBuilder;

import java.util.List;

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

        result.safeSetId( sdkCustomField.getId() );
        result.safeSetValue( sdkCustomField.getValue() );
        result.safeSetRequired(sdkCustomField.getRequired());
        result.safeSetTranslations(Lists.newArrayList(Iterables.transform(sdkCustomField.getTranslations(), new Function<Translation, com.silanis.esl.api.model.Translation>() {
            @Override
            public com.silanis.esl.api.model.Translation apply(final Translation input) {
                return input.toAPITranslation();
            }
        })));

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

        List<Translation> sdkTranslations =
            Lists.newArrayList(Iterables.transform(apiCustomField.getTranslations(), new Function<com.silanis.esl.api.model.Translation, Translation>() {
                @Override
                public Translation apply(final com.silanis.esl.api.model.Translation input) {
                    return TranslationBuilder.newTranslation(input).build();
                }
            }));

        return CustomFieldBuilder.customFieldWithId(apiCustomField.getId())
                                                      .withDefaultValue(apiCustomField.getValue())
                                                      .withTranslations(sdkTranslations)
                                                      .isRequired(apiCustomField.evalRequired())
                                 .build();
    }

}
