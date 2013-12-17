package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.UserCustomField;
import com.silanis.esl.sdk.CustomField;
import com.silanis.esl.sdk.CustomFieldValue;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;
import com.silanis.esl.sdk.internal.converter.CustomFieldConverter;
import com.silanis.esl.sdk.internal.converter.CustomFieldValueConverter;

/**
 * The CustomFieldService class provides methods to create
 * account custom fields and user custom fields.
 */
public class CustomFieldService {

    private UrlTemplate template;
    private RestClient client;

    public CustomFieldService(RestClient client, String baseUrl) {
        template = new UrlTemplate( baseUrl );
        this.client = client;
    }

    /**
     * Create an account custom field.
     * If the custom field already existed then update it.
     *
     * @param customField
     * @return the custom field added created or updated
     * @throws com.silanis.esl.sdk.EslException
     */
    public CustomField createCustomField(CustomField customField ) throws EslException {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_PATH).build();
        CustomField sdkResponse = null;
        com.silanis.esl.api.model.CustomField apiResponse;
        com.silanis.esl.api.model.CustomField apiRequest;

        try {
            if ( doesCustomFieldExist( customField.getId() )){
                apiRequest = new CustomFieldConverter(customField).toAPICustomField();
                String stringResponse = client.put(path,Serialization.toJson( apiRequest ));
                apiResponse = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
                sdkResponse = new CustomFieldConverter(apiResponse).toSDKCustomField();
            }else{
                apiRequest = new CustomFieldConverter(customField).toAPICustomField();
                String stringResponse = client.post(path,Serialization.toJson( apiRequest ));
                apiResponse = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
                sdkResponse = new CustomFieldConverter(apiResponse).toSDKCustomField();
            }
            return sdkResponse;
        } catch ( Exception e ) {
            throw new EslException( "Could not add/update the custom field to account.", e );
        }
    }

    /**
     * Determine if a custom field already existed
     *
     * @param id of custom field
     * @return true if existed otherwise false
     */
    public boolean doesCustomFieldExist( String id ){
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_PATH).build();
        path += "/" + id;

        try {
            String stringResponse = client.get(path);
            if (stringResponse==null || stringResponse.isEmpty()){
                return false;
            }
            Serialization.fromJson(stringResponse, UserCustomField.class);
            return true;
        } catch ( EslException e ) {
            return false;
        } catch ( Exception e ) {
            throw new EslException( "Could not get the custom field from account.", e );
        }
    }

    /**
     * Create an user custom field.
     * If the custom field already existed then update it.
     *
     *
     * @param customFieldValue
     * @return user custom field id
     * @throws com.silanis.esl.sdk.EslException
     */
    public CustomFieldValue submitCustomFieldValue( CustomFieldValue customFieldValue ) throws EslException {
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();
        String response;

        try {
            String payload = Serialization.toJson(new CustomFieldValueConverter(customFieldValue).toAPIUserCustomField());
            if ( doesCustomFieldValueExist( customFieldValue.getId() )){
                response = client.put(path,payload);
            }else{
                response = client.post(path, payload);
            }
            UserCustomField result = Serialization.fromJson( response, UserCustomField.class );

            return new CustomFieldValueConverter(customFieldValue).toSDKCustomFieldValue();
        } catch ( Exception e ) {
            throw new EslException( "Could not add/update the custom field to account.", e );
        }
    }

    /**
     * Determine if an user custom field already existed
     *
     * @param id of user custom field
     * @return true if existed otherwise false
     */
    public boolean doesCustomFieldValueExist( String id ){
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();
        path += "/" + id;

        try {
            String stringResponse = client.get(path);
            if (stringResponse==null || stringResponse.isEmpty()){
                return false;
            }
            Serialization.fromJson(stringResponse, UserCustomField.class);
            return true;
        } catch ( EslException e ) {
            return false;
        } catch ( Exception e ) {
            throw new EslException( "Could not get the custom field from user.", e );
        }
    }

}
