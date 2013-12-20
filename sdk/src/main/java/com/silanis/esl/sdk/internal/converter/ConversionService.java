package com.silanis.esl.sdk.internal.converter;

public class ConversionService {

    private ConversionService() {}

    public static com.silanis.esl.api.model.Field convert(com.silanis.esl.sdk.Field sdkField ) {
        return new FieldConverter( sdkField ).toAPIField();
    }

    public static com.silanis.esl.sdk.Field convert(com.silanis.esl.api.model.Field apiField) {
        return new FieldConverter(apiField).toSDKField();
    }
}