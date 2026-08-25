package com.silanis.esl.sdk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.util.JacksonUtil;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class ExtractionOutcomeTest {

    @Test
    public void completedHasNoReasonCode() {
        assertOutcome("COMPLETED", null, ExtractionStatus.COMPLETED, null);
    }

    @Test
    public void notPerformedBecauseExtractionIsNotEnabled() {
        assertOutcome("NOT_PERFORMED", "EXTRACTION_NOT_ENABLED",
                ExtractionStatus.NOT_PERFORMED, ExtractionReasonCode.EXTRACTION_NOT_ENABLED);
    }

    @Test
    public void notPerformedBecauseClassificationIsUnknown() {
        assertOutcome("NOT_PERFORMED", "CLASSIFICATION_UNKNOWN",
                ExtractionStatus.NOT_PERFORMED, ExtractionReasonCode.CLASSIFICATION_UNKNOWN);
    }

    @Test
    public void notPerformedBecauseClassifiedTypeIsUnsupported() {
        assertOutcome("NOT_PERFORMED", "CLASSIFICATION_UNSUPPORTED_TYPE",
                ExtractionStatus.NOT_PERFORMED, ExtractionReasonCode.CLASSIFICATION_UNSUPPORTED_TYPE);
    }

    @Test
    public void notPerformedBecauseClassificationQualityWarning() {
        assertOutcome("NOT_PERFORMED", "CLASSIFICATION_QUALITY_WARNING",
                ExtractionStatus.NOT_PERFORMED, ExtractionReasonCode.CLASSIFICATION_QUALITY_WARNING);
    }

    @Test
    public void failedBecauseExtractionErrored() {
        assertOutcome("FAILED", "EXTRACTION_ERROR",
                ExtractionStatus.FAILED, ExtractionReasonCode.EXTRACTION_ERROR);
    }

    @Test
    public void unknownEnumValuesAreReadAsNull() {
        assertOutcome("SOMETHING_NEW", "SOME_NEW_REASON", null, null);
    }

    @Test
    public void absentOutcomeIsNull() {
        String response = "[{\"attachmentUuid\":\"attachment-uid\",\"extractionResult\":{}}]";

        AttachmentVerificationResult result = deserialize(response).get(0);

        assertThat(result.getExtractionStatus(), is(nullValue()));
        assertThat(result.getReasonCode(), is(nullValue()));
        assertThat(result.getExtractionResult().getExtractionStatus(), is(nullValue()));
        assertThat(result.getExtractionResult().getReasonCode(), is(nullValue()));
    }

    @Test
    public void failureMessageIsExposedAlongsideTheReasonCode() {
        String response = "[{" +
                "\"extractionStatus\":\"FAILED\"," +
                "\"reasonCode\":\"EXTRACTION_ERROR\"," +
                "\"extractionResult\":{" +
                "\"extractionStatus\":\"FAILED\"," +
                "\"reasonCode\":\"EXTRACTION_ERROR\"," +
                "\"failureMessage\":\"Extraction timed out after 30s\"," +
                "\"failed\":true" +
                "}}]";

        ExtractionResult extractionResult = deserialize(response).get(0).getExtractionResult();

        assertThat(extractionResult.getReasonCode(), is(ExtractionReasonCode.EXTRACTION_ERROR));
        assertThat(extractionResult.getFailureMessage(), is("Extraction timed out after 30s"));
    }

    private void assertOutcome(String statusJson, String reasonCodeJson,
                               ExtractionStatus expectedStatus, ExtractionReasonCode expectedReasonCode) {
        AttachmentVerificationResult result = deserialize(payload(statusJson, reasonCodeJson)).get(0);

        assertThat("verification result status for " + statusJson + "/" + reasonCodeJson,
                result.getExtractionStatus(), is(expectedStatus));
        assertThat("verification result reason code for " + statusJson + "/" + reasonCodeJson,
                result.getReasonCode(), is(expectedReasonCode));

        ExtractionResult extractionResult = result.getExtractionResult();
        assertThat("extraction result status for " + statusJson + "/" + reasonCodeJson,
                extractionResult.getExtractionStatus(), is(expectedStatus));
        assertThat("extraction result reason code for " + statusJson + "/" + reasonCodeJson,
                extractionResult.getReasonCode(), is(expectedReasonCode));
    }

    private String payload(String statusJson, String reasonCodeJson) {
        String status = statusJson == null ? "null" : "\"" + statusJson + "\"";
        String reasonCode = reasonCodeJson == null ? "null" : "\"" + reasonCodeJson + "\"";
        return "[{" +
                "\"attachmentUuid\":\"attachment-uid\"," +
                "\"extractionStatus\":" + status + "," +
                "\"reasonCode\":" + reasonCode + "," +
                "\"extractionResult\":{" +
                "\"documentUuid\":\"verification-uuid\"," +
                "\"extractionStatus\":" + status + "," +
                "\"reasonCode\":" + reasonCode +
                "}}]";
    }

    private List<AttachmentVerificationResult> deserialize(String response) {
        return JacksonUtil.deserialize(response, new TypeReference<List<AttachmentVerificationResult>>() {});
    }
}
