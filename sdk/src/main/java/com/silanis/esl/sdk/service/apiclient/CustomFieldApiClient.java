package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.UserCustomField;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.service.EslComponent;

import java.util.List;

/**
 * Created by lena on 2014-08-28.
 */
public class CustomFieldApiClient extends EslComponent {

    public CustomFieldApiClient(RestClient restClient, String apiUrl) {
        super(restClient, apiUrl);
    }

    public com.silanis.esl.api.model.CustomField createCustomField(com.silanis.esl.api.model.CustomField customField) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_PATH)
                .build();

        try {
            String stringResponse;
            if (doesCustomFieldExist(customField.getId())) {
                stringResponse = getClient().put(path, Serialization.toJson(customField));
            } else {
                stringResponse = getClient().post(path, Serialization.toJson(customField));
            }
            return Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not add/update the custom field to account.", e);
        } catch (Exception e) {
            throw new EslException("Could not add/update the custom field to account.", e);
        }
    }

    public boolean doesCustomFieldExist(String id) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = getClient().get(path);
            if (stringResponse == null || stringResponse.isEmpty()) {
                return false;
            }
            return true;
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom field from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom field from account.", e);
        }
    }

    public com.silanis.esl.api.model.CustomField getCustomField(String id) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = getClient().get(path);
            return Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom field from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom field from account.", e);
        }
    }

    public List<com.silanis.esl.api.model.CustomField> getCustomFields(Direction direction, PageRequest request) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_LIST_PATH)
                .replace("{dir}", direction.getDirection())
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String stringResponse = getClient().get(path);
            return Serialization.fromJsonToList(stringResponse, com.silanis.esl.api.model.CustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the list of custom fields from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the list of custom fields from account.", e);
        }
    }

    public void deleteCustomField(String id) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the custom field from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the custom field from account.", e);
        }
    }

    public List<UserCustomField> getUserCustomFields() throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();
        String response;

        try {
            response = getClient().get(path);
            return Serialization.fromJsonToList(response, UserCustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom fields for the user.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom fields for the user.", e);
        }
    }

    public UserCustomField getUserCustomField(String customFieldId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.USER_CUSTOMFIELD_ID_PATH)
                              .replace("{customFieldId}", customFieldId)
                              .build();

        String response;
        try {
            response = getClient().get(path);
            return Serialization.fromJson(response, UserCustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom field for the user.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom field for the user.", e);
        }
    }

    public UserCustomField submitCustomFieldValue(com.silanis.esl.api.model.UserCustomField userCustomField) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();
        String response;

        try {
            String payload = Serialization.toJson(userCustomField);

            if (doesCustomFieldValueExist(userCustomField.getId())) {
                response = getClient().put(path, payload);
            } else {
                response = getClient().post(path, payload);
            }
            return Serialization.fromJson(response, UserCustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not add/update the custom field to account.", e);
        } catch (Exception e) {
            throw new EslException("Could not add/update the custom field to account.", e);
        }
    }

    public void deleteUserCustomField(String id) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.USER_CUSTOMFIELD_ID_PATH)
                              .replace("{customFieldId}", id)
                              .build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the custom field from user.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the custom field from user.", e);
        }
    }

    public boolean doesCustomFieldValueExist(String id) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.USER_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = getClient().get(path);
            if (stringResponse == null || stringResponse.isEmpty()) {
                return false;
            }
            return true;
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom field from user.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom field from user.", e);
        }
    }
}
