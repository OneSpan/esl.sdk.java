package com.silanis.esl.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.util.JacksonUtil;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

public class AttachmentVerificationResultTest {

    @Test
    public void deserializeBackendAttachmentVerificationPayload() {
        List<AttachmentVerificationResult> results = deserializeBackendPayloadWithContent();

        AttachmentVerificationResult result = results.get(0);
        assertThat(result.getAttachmentUuid(), is("attachment-uid"));
        assertThat(result.getFileName(), is("passport"));
        assertThat(result.getFileId(), is("42"));
        assertThat(result.getExtension(), is("pdf"));
        assertThat(result.isTypeMatch(), is(true));
        assertThat(result.isExtractionFailed(), is(true));
        assertThat(result.getExtractionErrorCode(), is("esl.error.attachment_verification.image_low_contrast"));

        AttachmentClassificationResult classificationResult = result.getClassificationResult();
        assertThat(classificationResult.getDocumentUuid(), is("verification-uuid"));
        assertThat(classificationResult.getDocumentType(), is("PASSPORT"));
        assertThat(classificationResult.getConfidenceScore(), is(0.95));
        assertThat(classificationResult.getConfidenceLevel(), is("HIGH"));
        assertThat(classificationResult.getProviderName(), is("bedrock"));
        assertThat(classificationResult.getFailed(), is(true));
        assertThat(classificationResult.getErrorCode(), is("esl.error.attachment_verification.image_low_contrast"));
        assertThat(classificationResult.getFailureMessage(), is("Low contrast"));

        ExtractionResult extractionResult = result.getExtractionResult();
        assertThat(extractionResult.getDocumentUuid(), is("verification-uuid"));
        assertThat(extractionResult.getProviderName(), is("bedrock"));
        assertThat(extractionResult.getExtractedFields().get("fullName"), is("Jane Doe"));
        assertThat(extractionResult.getFailed(), is(false));
        assertThat(extractionResult.getVerificationCheckResults(), notNullValue());
        assertThat(extractionResult.getVerificationCheckResults().size(), is(1));

        AttachmentVerificationCheckResult checkResult = extractionResult.getVerificationCheckResults().get(0);
        assertThat(checkResult.getRuleName(), is("expiry_check"));
        assertThat(checkResult.getField(), is("expiryDate"));
        assertThat(checkResult.getStatus(), is(AttachmentVerificationStatus.PASS));
        assertThat(checkResult.getMessage(), is("Document expires on 2099-01-01, still valid"));
    }

    @Test
    public void backendContentIsIgnoredBySdkVerificationResult() {
        AttachmentVerificationResult result = deserializeBackendPayloadWithContent().get(0);

        String sdkResultJson = JacksonUtil.serialize(result);

        assertThat(sdkResultJson, not(containsString("\"content\"")));
        assertThat(sdkResultJson, containsString("\"fileId\":\"42\""));
        assertThat(sdkResultJson, containsString("\"extractionFailed\":true"));
        assertThat(sdkResultJson, containsString("\"verificationCheckResults\""));
        assertThat(sdkResultJson, containsString("\"status\":\"PASS\""));
    }

    private List<AttachmentVerificationResult> deserializeBackendPayloadWithContent() {
        String response = "[{" +
                "\"attachmentUuid\":\"attachment-uid\"," +
                "\"fileName\":\"passport\"," +
                "\"fileId\":\"42\"," +
                "\"extension\":\"pdf\"," +
                "\"content\":\"ZmlsZSBjb250ZW50\"," +
                "\"typeMatch\":true," +
                "\"extractionFailed\":true," +
                "\"extractionErrorCode\":\"esl.error.attachment_verification.image_low_contrast\"," +
                "\"classificationResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"documentType\":\"PASSPORT\"," +
                "\"confidenceScore\":0.95," +
                "\"confidenceLevel\":\"HIGH\"," +
                "\"providerName\":\"bedrock\"," +
                "\"failed\":true," +
                "\"errorCode\":\"esl.error.attachment_verification.image_low_contrast\"," +
                "\"failureMessage\":\"Low contrast\"" +
                "}," +
                "\"extractionResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"providerName\":\"bedrock\"," +
                "\"extractedFields\":{\"fullName\":\"Jane Doe\",\"expiryDate\":\"2099-01-01\"}," +
                "\"verificationCheckResults\":[{" +
                "\"ruleName\":\"expiry_check\"," +
                "\"field\":\"expiryDate\"," +
                "\"status\":\"PASS\"," +
                "\"message\":\"Document expires on 2099-01-01, still valid\"" +
                "}]," +
                "\"failed\":false," +
                "\"errorCode\":null," +
                "\"failureMessage\":null" +
                "}" +
                "}]";

        return JacksonUtil.deserialize(response, new TypeReference<List<AttachmentVerificationResult>>() {});
    }
}
