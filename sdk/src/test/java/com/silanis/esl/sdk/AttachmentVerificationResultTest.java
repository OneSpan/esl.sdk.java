package com.silanis.esl.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.util.JacksonUtil;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

public class AttachmentVerificationResultTest {

    @Test
    public void deserializeBackendAttachmentVerificationPayload() {
        AttachmentVerificationResult result = deserializeBackendPayloadWithContent().get(0);

        assertThat(result.getAttachmentUuid(), is("attachment-uid"));
        assertThat(result.getFileName(), is("passport"));
        assertThat(result.getFileId(), is("42"));
        assertThat(result.getExtension(), is("pdf"));
        assertThat(result.isTypeMatch(), is(true));
        assertThat(result.getExtractionStatus(), is(ExtractionStatus.COMPLETED));
        assertThat(result.getReasonCode(), is(nullValue()));

        AttachmentClassificationResult classificationResult = result.getClassificationResult();
        assertThat(classificationResult.getDocumentUuid(), is("verification-uuid"));
        assertThat(classificationResult.getDocumentType(), is("PASSPORT"));
        assertThat(classificationResult.getConfidenceScore(), is(0.95));
        assertThat(classificationResult.getConfidenceLevel(), is("HIGH"));
        assertThat(classificationResult.getProviderName(), is("bedrock"));
        assertThat(classificationResult.getFailed(), is(false));

        ExtractionResult extractionResult = result.getExtractionResult();
        assertThat(extractionResult.getDocumentUuid(), is("verification-uuid"));
        assertThat(extractionResult.getProviderName(), is("bedrock"));
        assertThat(extractionResult.getExtractedFields().get("fullName"), is("Jane Doe"));
        assertThat(extractionResult.getExtractionStatus(), is(ExtractionStatus.COMPLETED));
        assertThat(extractionResult.getReasonCode(), is(nullValue()));
        assertThat(extractionResult.getFailed(), is(false));
        assertThat(extractionResult.getVerificationCheckResults(), notNullValue());
        assertThat(extractionResult.getVerificationCheckResults().size(), is(1));

        AttachmentVerificationCheckResult checkResult = extractionResult.getVerificationCheckResults().get(0);
        assertThat(checkResult.getRuleName(), is("expiry_check"));
        assertThat(checkResult.getFields(), is(Collections.singletonList("expiryDate")));
        assertThat(checkResult.getStatus(), is(AttachmentVerificationStatus.PASS));
        assertThat(checkResult.getMessage(), is("Document expires on 2099-01-01, still valid"));
    }

    @Test
    public void backendContentIsIgnoredBySdkVerificationResult() {
        AttachmentVerificationResult result = deserializeBackendPayloadWithContent().get(0);

        String sdkResultJson = JacksonUtil.serialize(result);

        assertThat(sdkResultJson, not(containsString("\"content\"")));
        assertThat(sdkResultJson, containsString("\"fileId\":\"42\""));
        assertThat(sdkResultJson, containsString("\"extractionStatus\":\"COMPLETED\""));
        assertThat(sdkResultJson, containsString("\"verificationCheckResults\""));
        assertThat(sdkResultJson, containsString("\"status\":\"PASS\""));
    }

    /**
     * The server no longer sends the {@code preflight} object modelled by newer verification
     * work, and never sent {@code someFutureField}; neither may break an existing consumer.
     */
    @Test
    public void unmodelledFieldsAreIgnored() {
        String response = "[{" +
                "\"attachmentUuid\":\"attachment-uid\"," +
                "\"extractionStatus\":\"COMPLETED\"," +
                "\"someFutureField\":\"whatever\"," +
                "\"extractionResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"extractionStatus\":\"COMPLETED\"," +
                "\"preflight\":{\"tier\":\"BASIC\",\"checks\":[]}" +
                "}" +
                "}]";

        AttachmentVerificationResult result = deserialize(response).get(0);

        assertThat(result.getAttachmentUuid(), is("attachment-uid"));
        assertThat(result.getExtractionStatus(), is(ExtractionStatus.COMPLETED));
        assertThat(result.getExtractionResult().getExtractionStatus(), is(ExtractionStatus.COMPLETED));
    }

    /**
     * {@code extractionFailed} and {@code extractionErrorCode} were replaced by the structured
     * outcome, but a payload from an older server must still deserialize without error.
     */
    @Test
    @SuppressWarnings("deprecation")
    public void legacyExtractionFailureFieldsStillDeserialize() {
        String response = "[{" +
                "\"attachmentUuid\":\"attachment-uid\"," +
                "\"typeMatch\":false," +
                "\"extractionFailed\":true," +
                "\"extractionErrorCode\":\"esl.error.attachment_verification.image_low_contrast\"," +
                "\"extractionResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"failed\":true," +
                "\"errorCode\":\"esl.error.attachment_verification.image_low_contrast\"" +
                "}" +
                "}]";

        AttachmentVerificationResult result = deserialize(response).get(0);

        assertThat(result.isExtractionFailed(), is(true));
        assertThat(result.getExtractionErrorCode(), is("esl.error.attachment_verification.image_low_contrast"));
        assertThat(result.getExtractionResult().getFailed(), is(true));
        assertThat(result.getExtractionResult().getErrorCode(),
                is("esl.error.attachment_verification.image_low_contrast"));
        assertThat("no structured outcome is present on a legacy payload",
                result.getExtractionStatus(), is(nullValue()));
        assertThat(result.getReasonCode(), is(nullValue()));
    }

    private List<AttachmentVerificationResult> deserializeBackendPayloadWithContent() {
        String response = "[{" +
                "\"attachmentUuid\":\"attachment-uid\"," +
                "\"fileName\":\"passport\"," +
                "\"fileId\":\"42\"," +
                "\"extension\":\"pdf\"," +
                "\"content\":\"ZmlsZSBjb250ZW50\"," +
                "\"typeMatch\":true," +
                "\"extractionStatus\":\"COMPLETED\"," +
                "\"reasonCode\":null," +
                "\"classificationResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"documentType\":\"PASSPORT\"," +
                "\"confidenceScore\":0.95," +
                "\"confidenceLevel\":\"HIGH\"," +
                "\"providerName\":\"bedrock\"," +
                "\"failed\":false," +
                "\"errorCode\":null," +
                "\"failureMessage\":null" +
                "}," +
                "\"extractionResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"providerName\":\"bedrock\"," +
                "\"extractedFields\":{\"fullName\":\"Jane Doe\",\"expiryDate\":\"2099-01-01\"}," +
                "\"verificationCheckResults\":[{" +
                "\"ruleName\":\"expiry_check\"," +
                "\"fields\":[\"expiryDate\"]," +
                "\"status\":\"PASS\"," +
                "\"message\":\"Document expires on 2099-01-01, still valid\"" +
                "}]," +
                "\"extractionStatus\":\"COMPLETED\"," +
                "\"reasonCode\":null," +
                "\"failureMessage\":null," +
                "\"failed\":false" +
                "}" +
                "}]";

        return deserialize(response);
    }

    private List<AttachmentVerificationResult> deserialize(String response) {
        return JacksonUtil.deserialize(response, new TypeReference<List<AttachmentVerificationResult>>() {});
    }
}
