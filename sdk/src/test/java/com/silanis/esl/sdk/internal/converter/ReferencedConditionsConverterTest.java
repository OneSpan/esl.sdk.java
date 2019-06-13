package com.silanis.esl.sdk.internal.converter;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertTrue;

import com.silanis.esl.sdk.FieldCondition;
import com.silanis.esl.sdk.ReferencedConditions;
import com.silanis.esl.sdk.ReferencedDocument;
import com.silanis.esl.sdk.ReferencedField;
import com.silanis.esl.sdk.ReferencedFieldConditions;
import com.silanis.esl.sdk.builder.FieldConditionBuilder;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class ReferencedConditionsConverterTest implements StaticConverterTest {

    static final String PACKAGE_ID = "package1";
    static final String DOCUMENT_ID = "document1";
    static final String FIELD_1_ID = "field1";
    static final String FIELD_2_ID = "field2";

    static final String FIELD_3_ID = "field3";
    static final String CONDITION_1_ID = "Condition1";
    static final String CONDITION_2_ID = "Condition2";
    static final String CONDITION = "document['document1'].field['%s'].empty == true";

    static final String ACTION = "document['document1'].field['%s'].enabled = true";

    @Override
    @Test
    public void convertNullSDKToAPI() {
        com.silanis.esl.api.model.ReferencedConditions api = ReferencedConditionsConverter.toAPI(null);
        assertThat("Converter didn't return a null api object for a null sdk object", api, is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        com.silanis.esl.sdk.ReferencedConditions sdk = ReferencedConditionsConverter.toSDK(null);
        assertThat("Converter didn't return a null sdk object for a null api object", sdk, is(nullValue()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        List<com.silanis.esl.api.model.ReferencedDocument> apiDocuments = createApiReferencedDocumentsForTest();

        com.silanis.esl.api.model.ReferencedConditions api = new com.silanis.esl.api.model.ReferencedConditions();
        api.setPackageId(PACKAGE_ID);
        api.setDocuments(apiDocuments);

        ReferencedConditions sdk = ReferencedConditionsConverter.toSDK(api);

        assertThat("Package ID was not correctly converted", sdk.getPackageId(), is(PACKAGE_ID));
        assertTrue("Referenced Documents was not correctly converted", compareReferencedDocuments(apiDocuments, sdk.getDocuments()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        List<ReferencedDocument> sdkDocuments = createSdkReferencedDocumentsForTest();

        ReferencedConditions sdk = new ReferencedConditions();
        sdk.setPackageId(PACKAGE_ID);
        sdk.setDocuments(sdkDocuments);

        com.silanis.esl.api.model.ReferencedConditions api = ReferencedConditionsConverter.toAPI(sdk);

        assertThat("Package ID was not correctly converted", api.getPackageId(), is(PACKAGE_ID));
        assertTrue("Referenced Documents was not correctly converted", compareReferencedDocuments(api.getDocuments(), sdkDocuments));
    }

    static List<com.silanis.esl.api.model.ReferencedDocument> createApiReferencedDocumentsForTest() {
        List<com.silanis.esl.api.model.ReferencedDocument> documents = new ArrayList<com.silanis.esl.api.model.ReferencedDocument>();

        com.silanis.esl.api.model.ReferencedDocument referencedDocument = new com.silanis.esl.api.model.ReferencedDocument();
        referencedDocument.setDocumentId(DOCUMENT_ID);
        referencedDocument.setFields(createApiReferencedFieldsForTest());

        documents.add(referencedDocument);
        return documents;
    }

    static List<com.silanis.esl.api.model.ReferencedField> createApiReferencedFieldsForTest() {
        List<com.silanis.esl.api.model.ReferencedField> fields = new ArrayList<com.silanis.esl.api.model.ReferencedField>();

        com.silanis.esl.api.model.ReferencedField referencedField = new com.silanis.esl.api.model.ReferencedField();
        referencedField.setFieldId(FIELD_1_ID);
        referencedField.setConditions(createApiReferencedFieldConditionsForTest());

        return fields;
    }

    static com.silanis.esl.api.model.ReferencedFieldConditions createApiReferencedFieldConditionsForTest() {
        com.silanis.esl.api.model.ReferencedFieldConditions referencedFieldConditions = new com.silanis.esl.api.model.ReferencedFieldConditions();
        referencedFieldConditions.setReferencedInCondition(createApiFieldConditionsForTest(CONDITION_1_ID, FIELD_1_ID, FIELD_2_ID));
        referencedFieldConditions.setReferencedInAction(createApiFieldConditionsForTest(CONDITION_2_ID, FIELD_3_ID, FIELD_1_ID));
        return referencedFieldConditions;
    }

    static List<com.silanis.esl.api.model.FieldCondition> createApiFieldConditionsForTest(String conditionId, String fieldInConditionId, String fieldInActionId) {
        List<com.silanis.esl.api.model.FieldCondition> fieldConditions = new ArrayList<com.silanis.esl.api.model.FieldCondition>();

        com.silanis.esl.api.model.FieldCondition fieldCondition = new com.silanis.esl.api.model.FieldCondition();
        fieldCondition.setId(conditionId);
        fieldCondition.setCondition(String.format(CONDITION, fieldInConditionId));
        fieldCondition.setAction(String.format(ACTION, fieldInActionId));

        fieldConditions.add(fieldCondition);
        return fieldConditions;
    }

    static List<ReferencedDocument> createSdkReferencedDocumentsForTest() {
        List<ReferencedDocument> documents = new ArrayList<ReferencedDocument>();

        ReferencedDocument referencedDocument = new ReferencedDocument();
        referencedDocument.setDocumentId(DOCUMENT_ID);
        referencedDocument.setFields(createSdkReferencedFieldsForTest());

        documents.add(referencedDocument);
        return documents;
    }

    static List<ReferencedField> createSdkReferencedFieldsForTest() {
        List<ReferencedField> fields = new ArrayList<ReferencedField>();

        ReferencedField referencedField = new ReferencedField();
        referencedField.setFieldId(FIELD_1_ID);
        referencedField.setConditions(createSdkReferencedFieldConditionsForTest());

        fields.add(referencedField);
        return fields;
    }

    static ReferencedFieldConditions createSdkReferencedFieldConditionsForTest() {
        ReferencedFieldConditions referencedFieldConditions = new ReferencedFieldConditions();
        referencedFieldConditions.setReferencedInCondition(createSdkFieldConditionsForTest(CONDITION_1_ID, FIELD_1_ID, FIELD_2_ID));
        referencedFieldConditions.setReferencedInAction(createSdkFieldConditionsForTest(CONDITION_2_ID, FIELD_3_ID, FIELD_1_ID));
        return referencedFieldConditions;
    }

    static List<FieldCondition> createSdkFieldConditionsForTest(String conditionId, String fieldInConditionId, String fieldInActionId) {
        List<FieldCondition> fieldConditions = new ArrayList<FieldCondition>();

        fieldConditions.add(FieldConditionBuilder.newFieldCondition()
            .withId(conditionId)
            .withCondition(String.format(CONDITION, fieldInConditionId))
            .withAction(String.format(ACTION, fieldInActionId))
            .build());

        return fieldConditions;
    }

    static boolean compareReferencedDocuments(com.silanis.esl.api.model.ReferencedDocument apiRefDoc, ReferencedDocument sdkRefDoc) {
        return apiRefDoc.getDocumentId().equals(sdkRefDoc.getDocumentId()) &&
            compareReferencedFields(apiRefDoc.getFields(), sdkRefDoc.getFields());
    }

    static boolean compareReferencedDocuments(List<com.silanis.esl.api.model.ReferencedDocument> apiRefDocs, List<ReferencedDocument> sdkRefDocs) {
        if (apiRefDocs.size() != sdkRefDocs.size()) {
            return false;
        }

        for (int i = 0; i < apiRefDocs.size(); i++) {
            if (!compareReferencedDocuments(apiRefDocs.get(i), sdkRefDocs.get(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean compareReferencedFields(List<com.silanis.esl.api.model.ReferencedField> apiRefFields, List<ReferencedField> sdkRefFields) {
        if (apiRefFields.size() != sdkRefFields.size()) {
            return false;
        }

        for (int i = 0; i < apiRefFields.size(); i++) {
            if (!compareReferencedFields(apiRefFields.get(i), sdkRefFields.get(i))) {
                return false;
            }
        }
        return true;
    }

    static boolean compareReferencedFields(com.silanis.esl.api.model.ReferencedField apiRefField, ReferencedField sdkRefField) {
        return apiRefField.getFieldId().equals(sdkRefField.getFieldId()) &&
            compareReferencedFieldConditions(apiRefField.getConditions(), sdkRefField.getConditions());
    }

    static boolean compareReferencedFieldConditions(com.silanis.esl.api.model.ReferencedFieldConditions apiReferencedFieldConditions,
        ReferencedFieldConditions sdkReferencedFieldConditions) {
        List<com.silanis.esl.api.model.FieldCondition> apiReferencedInCondition = apiReferencedFieldConditions.getReferencedInCondition();
        List<com.silanis.esl.api.model.FieldCondition> apiReferencedInAction = apiReferencedFieldConditions.getReferencedInAction();
        List<FieldCondition> sdkReferencedInCondition = sdkReferencedFieldConditions.getReferencedInCondition();
        List<FieldCondition> sdkReferencedInAction = sdkReferencedFieldConditions.getReferencedInAction();

        return compareFieldConditions(apiReferencedInCondition, sdkReferencedInCondition) &&
            compareFieldConditions(apiReferencedInAction, sdkReferencedInAction);
    }

    static boolean compareFieldConditions(com.silanis.esl.api.model.FieldCondition apiFieldCondition, FieldCondition sdkFieldCondition) {
        return apiFieldCondition.getId().equals(sdkFieldCondition.getId()) &&
            apiFieldCondition.getCondition().equals(sdkFieldCondition.getCondition()) &&
            apiFieldCondition.getAction().equals(sdkFieldCondition.getAction());
    }

    static boolean compareFieldConditions(List<com.silanis.esl.api.model.FieldCondition> apiFieldConditions, List<FieldCondition> sdkFieldConditions) {
        if (apiFieldConditions.size() != sdkFieldConditions.size()) {
            return false;
        }

        for (int i = 0; i < apiFieldConditions.size(); i++) {
            if (!compareFieldConditions(apiFieldConditions.get(i), sdkFieldConditions.get(i))) {
                return false;
            }
        }
        return true;
    }
}
