package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.CustomField;
import com.silanis.esl.api.model.UserCustomField;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.internal.RestClient;
import com.silanis.esl.sdk.internal.Serialization;
import com.silanis.esl.sdk.internal.UrlTemplate;

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
    public CustomField createCustomField(CustomField customField) throws EslException {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_PATH).build();

        try {
            if (isCustomFieldExisted(customField.getId())){
                String stringResponse = client.put(path,Serialization.toJson(customField));
                customField = Serialization.fromJson(stringResponse, CustomField.class);
            }else{
                String stringResponse = client.post(path,Serialization.toJson(customField));
                customField = Serialization.fromJson(stringResponse, CustomField.class);
            }
            return customField;
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
    public boolean isCustomFieldExisted(String id){
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_PATH).build();
        path += "/" + id;

        try {
            String stringResponse = client.get(path);
            if (stringResponse==null || stringResponse.isEmpty()){
                return false;
            }
            Serialization.fromJson(stringResponse, CustomField.class);
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
     * @param userCustomField
     * @return user custom field id
     * @throws com.silanis.esl.sdk.EslException
     */
    public String createUserCustomField(UserCustomField userCustomField) throws EslException {
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();

        try {
            if (isUserCustomFieldExisted(userCustomField.getId())){
                String stringResponse = client.put(path,Serialization.toJson(userCustomField));
                userCustomField = Serialization.fromJson(stringResponse, UserCustomField.class);
            }else{
                String stringResponse = client.post(path, Serialization.toJson(userCustomField));
                userCustomField = Serialization.fromJson(stringResponse, UserCustomField.class);
            }
            return userCustomField.getId();
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
    public boolean isUserCustomFieldExisted(String id){
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
