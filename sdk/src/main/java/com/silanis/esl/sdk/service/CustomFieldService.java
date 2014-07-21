package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.Document;
import com.silanis.esl.api.model.UserCustomField;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.CustomFieldConverter;
import com.silanis.esl.sdk.internal.converter.CustomFieldValueConverter;

import java.util.ArrayList;
import java.util.List;

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
        CustomField sdkResponse;
        com.silanis.esl.api.model.CustomField apiResponse;
        com.silanis.esl.api.model.CustomField apiRequest;

        try {
            apiRequest = new CustomFieldConverter(customField).toAPICustomField();
            String stringResponse;
            if ( doesCustomFieldExist( customField.getId() )){
                stringResponse = client.put(path,Serialization.toJson( apiRequest ));
            }else{
                stringResponse = client.post(path,Serialization.toJson( apiRequest ));
            }
            apiResponse = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
            sdkResponse = new CustomFieldConverter(apiResponse).toSDKCustomField();
            return sdkResponse;
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not add/update the custom field to account.", e );
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
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = client.get(path);
            if (stringResponse==null || stringResponse.isEmpty()){
                return false;
            }
            Serialization.fromJson(stringResponse, UserCustomField.class);
            return true;
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not get the custom field from account.", e );
        } catch ( EslException e ) {
            return false;
        } catch ( Exception e ) {
            throw new EslException( "Could not get the custom field from account.", e );
        }
    }

    /**
     * Get an account custom field.
     *
     * @param id of custom field to get
     * @return the custom field
     */
    public CustomField getCustomField(String id) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = client.get(path);
            com.silanis.esl.api.model.CustomField apiCustomField = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
            return new CustomFieldConverter(apiCustomField).toSDKCustomField();
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not get the custom field from account.", e );
        } catch ( Exception e ) {
            throw new EslException( "Could not get the custom field from account.", e );
        }
    }

    /**
     * Get the entire list of account custom fields.
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by id
     * @return the list of custom fields
     */
    public List<CustomField> getCustomFields(Direction direction) {
        return getCustomFields(direction, 0, 0);
    }

    /**
     * Get the list of account custom fields in the index [from, to] inclusively.
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by id
     * @param from index of custom field to start from @size(min="1")
     * @param to index of custom field to end at @size(min="1")
     * @return the list of custom fields
     */
    public List<CustomField> getCustomFields(Direction direction, int from, int to) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_LIST_PATH)
                .replace("{dir}", direction.getDir())
                .replace("{from}", Integer.toString(from))
                .replace("{to}", Integer.toString(to))
                .build();

        try {
            String stringResponse = client.get(path);
            List<com.silanis.esl.api.model.CustomField> customFieldList = Serialization.fromJsonToList(stringResponse, com.silanis.esl.api.model.CustomField.class);

            List<CustomField> result = new ArrayList<CustomField>();
            for (com.silanis.esl.api.model.CustomField apiCustomField : customFieldList) {
                result.add(new CustomFieldConverter(apiCustomField).toSDKCustomField());
            }

            return result;
        }catch ( RequestException e ) {
            throw new EslServerException( "Could not get the list of custom fields from account.", e );
        } catch ( Exception e ) {
            throw new EslException( "Could not get the list of custom fields from account.", e );
        }
    }

    /**
     * Delete an account custom field.
     *
     * @param id of custom field to delete.
     */
    public void deleteCustomField(String id) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            client.delete(path);
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not delete the custom field from account.", e);
        } catch ( Exception e ) {
            throw new EslException("Could not delete the custom field from account.", e);
        }
    }

    /**
     * Create an user custom field.
     * If the custom field already existed then update it.
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
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not add/update the custom field to account.", e );
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
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = client.get(path);
            if (stringResponse==null || stringResponse.isEmpty()){
                return false;
            }
            Serialization.fromJson(stringResponse, UserCustomField.class);
            return true;
        } catch ( RequestException e ) {
            throw new EslServerException( "Could not get the custom field from user.", e );
        } catch ( EslException e ) {
            return false;
        } catch ( Exception e ) {
            throw new EslException( "Could not get the custom field from user.", e );
        }
    }

}
