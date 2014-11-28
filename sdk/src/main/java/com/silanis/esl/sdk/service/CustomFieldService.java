package com.silanis.esl.sdk.service;

import com.silanis.esl.api.model.UserCustomField;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.CustomField;
import com.silanis.esl.sdk.CustomFieldValue;
import com.silanis.esl.sdk.internal.converter.CustomFieldConverter;
import com.silanis.esl.sdk.internal.converter.CustomFieldValueConverter;
import com.silanis.esl.sdk.service.apiclient.CustomFieldApiClient;

import java.util.ArrayList;
import java.util.List;

/**
 * The CustomFieldService class provides methods to create
 * account custom fields and user custom fields.
 */
public class CustomFieldService {

    private CustomFieldApiClient apiClient;

    public CustomFieldService(CustomFieldApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Create an account custom field.
     * If the custom field already existed then update it.
     *
     * @param customField
     * @return the custom field added created or updated
     */
    public CustomField createCustomField(CustomField customField) {
        com.silanis.esl.api.model.CustomField apiRequest = new CustomFieldConverter(customField).toAPICustomField();
        com.silanis.esl.api.model.CustomField apiResponse = apiClient.createCustomField(apiRequest);
        return new CustomFieldConverter(apiResponse).toSDKCustomField();
    }

    /**
     * Determine if a custom field already existed
     *
     * @param id of custom field
     * @return true if existed otherwise false
     */
    public boolean doesCustomFieldExist(String id) {
        return apiClient.doesCustomFieldExist(id);
    }

    /**
     * Get an account custom field.
     *
     * @param id of custom field to get
     * @return the custom field
     */
    public CustomField getCustomField(String id) {
        com.silanis.esl.api.model.CustomField apiCustomField = apiClient.getCustomField(id);
        return new CustomFieldConverter(apiCustomField).toSDKCustomField();
    }

    /**
     * Get the entire list of account custom fields.
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by id
     * @return the list of custom fields
     */
    public List<CustomField> getCustomFields(Direction direction) {
        return getCustomFields(direction, new PageRequest(0, -1));
    }

    /**
     * Get the list of account custom fields.
     *
     * @param direction of retrieved list to be sorted in ascending or descending order by id
     * @param request   identifying which page of results to return.
     * @return the list of custom fields
     */
    public List<CustomField> getCustomFields(Direction direction, PageRequest request) {
        List<com.silanis.esl.api.model.CustomField> customFieldList = apiClient.getCustomFields(direction, request);

        List<CustomField> result = new ArrayList<CustomField>();
        for (com.silanis.esl.api.model.CustomField apiCustomField : customFieldList) {
            result.add(new CustomFieldConverter(apiCustomField).toSDKCustomField());
        }

        return result;
    }

    /**
     * Delete an account custom field.
     *
     * @param id of custom field to delete.
     */
    public void deleteCustomField(String id) {
        apiClient.deleteCustomField(id);
    }

    /**
     * Create an user custom field.
     * If the custom field already existed then update it.
     *
     * @param customFieldValue
     * @return user custom field id
     */
    public CustomFieldValue submitCustomFieldValue(CustomFieldValue customFieldValue) {
        com.silanis.esl.api.model.UserCustomField userCustomField = new CustomFieldValueConverter(customFieldValue).toAPIUserCustomField();
        UserCustomField result = apiClient.submitCustomFieldValue(userCustomField);
        return new CustomFieldValueConverter(result).toSDKCustomFieldValue();
    }

    /**
     * Determine if an user custom field already existed
     *
     * @param id of user custom field
     * @return true if existed otherwise false
     */
    public boolean doesCustomFieldValueExist(String id) {
        return apiClient.doesCustomFieldValueExist(id);
    }

}
