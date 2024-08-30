package com.silanis.esl.sdk.service.apiclient;

import com.silanis.esl.api.model.Approval;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SignatureId;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.service.EslComponent;

import java.util.List;

/**
 * Created by dave on 11/08/14.
 */
public class ApprovalApiClient extends EslComponent {

    public ApprovalApiClient(RestClient restClient, String apiUrl) {
        super(restClient, apiUrl);
    }

    public void deleteSignature(String packageId, String documentId, String signatureId) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId)
                .build();
        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete signature from document.", e);
        }
    }

    public Approval addSignature(String packageId, String documentId, Approval approval) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPROVAL_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .build();
        try {
            String json = Serialization.toJson(approval);
            String stringResponse = getClient().post(path, json);

            Approval apiResponse = Serialization.fromJson(stringResponse, Approval.class);
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Could not add signature to document.", e);
        } catch (Exception e) {
            throw new EslException("Could not add signature to document.", e);
        }
    }

    public void modifySignature(String packageId, String documentId, Approval approval) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", approval.getId())
                .build();
        try {
            String json = Serialization.toJson(approval);
            getClient().put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not modify signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not modify signature from document.", e);
        }
    }

    public void updateSignatures(String packageId, String documentId, List<Approval> approvalList) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPROVAL_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .build();

        try {
            String json = Serialization.toJson(approvalList);
            getClient().put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update signatures from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not update signatures from document.", e);
        }
    }

    public Approval getSignature(String packageId, String documentId, String approvalId) throws EslException {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.APPROVAL_ID_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", approvalId)
                .build();
        try {
            String stringResponse = getClient().get(path);
            Approval apiResponse = Serialization.fromJson(stringResponse, Approval.class);
            return apiResponse;
        } catch (RequestException e) {
            throw new EslServerException("Could not get signature from document.", e);
        } catch (Exception e) {
            throw new EslException("Could not get signature from document.", e);
        }
    }

    public String addField(PackageId packageId, String documentId, SignatureId signatureId, com.silanis.esl.api.model.Field apiField) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.FIELD_PATH)
                .replace("{packageId}", packageId.getId())
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId.getId())
                .build();

        try {
            String json = Serialization.toJson(apiField);
            String stringResponse = getClient().post(path, json);
            com.silanis.esl.api.model.Field apiResponse = Serialization.fromJson(stringResponse, com.silanis.esl.api.model.Field.class);
            return apiResponse.getId();

        } catch (RequestException e) {
            throw new EslServerException("Could not add field to signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not add field to signature.", e);
        }
    }

    public void updateField(String packageId, String documentId, String signatureId, com.silanis.esl.api.model.Field field) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.FIELD_ID_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId)
                .replace("{fieldId}", field.getId())
                .build();

        try {
            String json = Serialization.toJson(field);
            getClient().put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update field from signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not update field from signature.", e);
        }
    }

    public void updateConditionalField(String packageId, String documentId, String signatureId, com.silanis.esl.api.model.ConditionalField field) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.CONDITIONAL_FIELD_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", signatureId)
                .replace("{fieldId}", field.getId())
                .build();

        try {
            String json = Serialization.toJson(field);
            getClient().put(path, json);
        } catch (RequestException e) {
            throw new EslServerException("Could not update conditional field from signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not update conditional field from signature.", e);
        }
    }

    public void deleteField(String packageId, String documentId, String approvalId, String fieldId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.FIELD_ID_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", approvalId)
                .replace("{fieldId}", fieldId)
                .build();

        try {
            getClient().delete(path);
        } catch (RequestException e) {
            throw new EslServerException("Could not delete field from signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not delete field from signature.", e);
        }
    }

    public com.silanis.esl.api.model.Field getField(String packageId, String documentId, String approvalId, String fieldId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.FIELD_ID_PATH)
                .replace("{packageId}", packageId)
                .replace("{documentId}", documentId)
                .replace("{approvalId}", approvalId)
                .replace("{fieldId}", fieldId)
                .build();

        try {
            String stringResponse = getClient().get(path);
            return Serialization.fromJson(stringResponse, com.silanis.esl.api.model.Field.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get field from signature.", e);
        } catch (Exception e) {
            throw new EslException("Could not get field from signature.", e);
        }
    }

    public List<Approval> getAllSignableApprovals(String packageId, String documentId, String signerId) {
        String path = new UrlTemplate(getBaseUrl()).urlFor(UrlTemplate.SIGNABLE_APPROVAL_PATH)
                              .replace("{packageId}", packageId)
                              .replace("{documentId}", documentId)
                              .replace("{signerId}", signerId)
                              .build();

        List<Approval> approvals;

        try {
            String stringResponse = getClient().get(path);
            approvals = Serialization.fromJsonToList(stringResponse, Approval.class);
        } catch (RequestException e) {
            throw new EslServerException("Could not get all signable signatures.", e);
        } catch (Exception e) {
            throw new EslException("Could not get all signable signatures.", e);
        }

        return approvals;
    }
}
