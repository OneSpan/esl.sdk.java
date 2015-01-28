package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.UserCustomField;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.internal.*;

import java.util.List;

/**
 * Created by lena on 2014-08-28.
 */
public class CustomFieldApiClient {

    private UrlTemplate template;
    private RestClient restClient;

    public CustomFieldApiClient(RestClient restClient, String apiUrl) {
        this.restClient = restClient;
        template = new UrlTemplate(apiUrl);
    }

    public com.silanis.esl.api.model.CustomField createCustomField(com.silanis.esl.api.model.CustomField customField) throws EslException {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_PATH)
                .build();

        try {
            String stringResponse;
            if (doesCustomFieldExist(customField.getId())) {
                stringResponse = restClient.put(path, Serialization.toJson(customField));
            } else {
                stringResponse = restClient.post(path, Serialization.toJson(customField));
            }
            return Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not add/update the custom field to account.", e);
        } catch (Exception e) {
            throw new EslException("Could not add/update the custom field to account.", e);
        }
    }

    public boolean doesCustomFieldExist(String id) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = restClient.get(path);
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
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJson(stringResponse, com.silanis.esl.api.model.CustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom field from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom field from account.", e);
        }
    }

    public List<com.silanis.esl.api.model.CustomField> getCustomFields(Direction direction, PageRequest request) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_LIST_PATH)
                .replace("{dir}", direction.getDirection())
                .replace("{from}", Integer.toString(request.getFrom()))
                .replace("{to}", Integer.toString(request.to()))
                .build();

        try {
            String stringResponse = restClient.get(path);
            return Serialization.fromJsonToList(stringResponse, com.silanis.esl.api.model.CustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the list of custom fields from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the list of custom fields from account.", e);
        }
    }

    public void deleteCustomField(String id) {
        String path = template.urlFor(UrlTemplate.ACCOUNT_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the custom field from account.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the custom field from account.", e);
        }
    }

    public List<UserCustomField> getUserCustomFields() throws EslException {
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();
        String response;

        try {
            response = restClient.get(path);
            return Serialization.fromJsonToList(response, UserCustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get the custom fields for the user.", e);
        } catch (Exception e) {
            throw new EslException("Could not get the custom fields for the user.", e);
        }
    }

    public UserCustomField submitCustomFieldValue(com.silanis.esl.api.model.UserCustomField userCustomField) throws EslException {
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_PATH).build();
        String response;

        try {
            String payload = Serialization.toJson(userCustomField);

            if (doesCustomFieldValueExist(userCustomField.getId())) {
                response = restClient.put(path, payload);
            } else {
                response = restClient.post(path, payload);
            }
            return Serialization.fromJson(response, UserCustomField.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not add/update the custom field to account.", e);
        } catch (Exception e) {
            throw new EslException("Could not add/update the custom field to account.", e);
        }
    }

    public void deleteUserCustomField(String id) throws EslException {
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_ID_PATH)
                              .replace("{customFieldId}", id)
                              .build();
        try {
            restClient.delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete the custom field from user.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete the custom field from user.", e);
        }
    }

    public boolean doesCustomFieldValueExist(String id) {
        String path = template.urlFor(UrlTemplate.USER_CUSTOMFIELD_ID_PATH)
                .replace("{customFieldId}", id)
                .build();

        try {
            String stringResponse = restClient.get(path);
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
