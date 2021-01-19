package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.*;
import com.silanis.esl.sdk.builder.*;
import org.junit.Test;

import static com.silanis.esl.sdk.builder.SigningUiOptionsBuilder.newSigningUiOptions;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class SigningUiOptionsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SigningUiOptions sdkSigningUiOptions = null;
    private SigningUiOptions apiSigningUiOptions = null;
    private SigningUiOptionsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSigningUiOptions = null;
        converter = new SigningUiOptionsConverter(sdkSigningUiOptions);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISigningUiOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSigningUiOptions = null;
        converter = new SigningUiOptionsConverter(apiSigningUiOptions);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSigningUiOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSigningUiOptions = null;
        converter = new SigningUiOptionsConverter(sdkSigningUiOptions);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSigningUiOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSigningUiOptions = null;
        converter = new SigningUiOptionsConverter(apiSigningUiOptions);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISigningUiOptions(), is(nullValue()));
    }

    @Override
    @Test
    public void convertSDKToSDK() {

        sdkSigningUiOptions = createTypicalSDKSigningUiOptions();
        com.silanis.esl.sdk.SigningUiOptions sdkSigningUiOptions2 = new SigningUiOptionsConverter(sdkSigningUiOptions).toSDKSigningUiOptions();
        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSigningUiOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSigningUiOptions2, is(sdkSigningUiOptions));
    }

    @Override
    @Test
    public void convertAPIToAPI() {

        apiSigningUiOptions = createTypicalAPISigningUiOptions();
        SigningUiOptions apiSigningUiOptions2 = new SigningUiOptionsConverter(apiSigningUiOptions).toAPISigningUiOptions();

        assertThat("Converter returned a null api object for a non null api object", apiSigningUiOptions2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiSigningUiOptions2, is(apiSigningUiOptions));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSigningUiOptions = createTypicalAPISigningUiOptions();
        sdkSigningUiOptions = new SigningUiOptionsConverter(apiSigningUiOptions).toSDKSigningUiOptions();

        assertThat("Converter returned a null api object for a non null api object", apiSigningUiOptions, notNullValue());
        assertThat("'from' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getFrom(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getFrom()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getTitle(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getTitle()));
        assertThat("'message' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getMessage(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getMessage()));
        assertThat("'download' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getDownload(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getDownload()));
        assertThat("'review' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getReview(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getReview()));
        assertThat("'continue' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getContinue(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getContinue()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getTitle(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getBody(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientName(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientEmail(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientActionRequired(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientActionRequired()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientRole(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientStatus(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientStatus()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getTitle(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getBody(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientName(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientEmail(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientRole(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientStatus(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getDownloadButton(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getReviewDocumentsButton(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getReviewDocumentsButton()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getTitle(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getBody(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientName(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientEmail(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientActionRequired(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientActionRequired()));
        assertThat("'notaryTag' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getNotaryTag(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getNotaryTag()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientRole(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientStatus(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientStatus()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getTitle(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getBody(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientName(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientEmail(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientRole(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientRole()));
        assertThat("'notaryTag' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getNotaryTag(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getNotaryTag()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientStatus(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getDownloadButton(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getReviewDocumentsButton(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getReviewDocumentsButton()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getOverviewOptions().getTitle(), is(sdkSigningUiOptions.getOverviewOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getOverviewOptions().getBody(), is(sdkSigningUiOptions.getOverviewOptions().getBody()));
        assertThat("'documentSection' was not correctly set", apiSigningUiOptions.getOverviewOptions().getDocumentSection(), is(sdkSigningUiOptions.getOverviewOptions().getDocumentSection()));
        assertThat("'uploadSection' was not correctly set", apiSigningUiOptions.getOverviewOptions().getUploadSection(), is(sdkSigningUiOptions.getOverviewOptions().getUploadSection()));

    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkSigningUiOptions = createTypicalSDKSigningUiOptions();
        apiSigningUiOptions = new SigningUiOptionsConverter(sdkSigningUiOptions).toAPISigningUiOptions();

        assertThat("Converter returned a null api object for a non null sdk object", apiSigningUiOptions, is(notNullValue()));
        assertThat("'from' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getFrom(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getFrom()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getTitle(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getTitle()));
        assertThat("'message' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getMessage(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getMessage()));
        assertThat("'download' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getDownload(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getDownload()));
        assertThat("'review' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getReview(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getReview()));
        assertThat("'continue' was not correctly set", apiSigningUiOptions.getCompleteSummaryOptions().getContinue(), is(sdkSigningUiOptions.getCompleteSummaryOptions().getContinue()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getTitle(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getBody(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientName(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientEmail(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientActionRequired(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientActionRequired()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientRole(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getInpersonWelcomeOptions().getRecipientStatus(), is(sdkSigningUiOptions.getInpersonWelcomeOptions().getRecipientStatus()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getTitle(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getBody(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientName(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientEmail(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientRole(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getRecipientStatus(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getDownloadButton(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiSigningUiOptions.getInpersonHostThankYouOptions().getReviewDocumentsButton(), is(sdkSigningUiOptions.getInpersonHostThankYouOptions().getReviewDocumentsButton()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getTitle(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getBody(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientName(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientEmail(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientEmail()));
        assertThat("'recipientActionRequired' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientActionRequired(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientActionRequired()));
        assertThat("'notaryTag' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getNotaryTag(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getNotaryTag()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientRole(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientRole()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getNotaryWelcomeOptions().getRecipientStatus(), is(sdkSigningUiOptions.getNotaryWelcomeOptions().getRecipientStatus()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getTitle(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getBody(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getBody()));
        assertThat("'recipientName' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientName(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientName()));
        assertThat("'recipientEmail' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientEmail(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientEmail()));
        assertThat("'recipientRole' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientRole(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientRole()));
        assertThat("'notaryTag' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getNotaryTag(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getNotaryTag()));
        assertThat("'recipientStatus' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getRecipientStatus(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getRecipientStatus()));
        assertThat("'downloadButton' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getDownloadButton(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getDownloadButton()));
        assertThat("'reviewDocumentsButton' was not correctly set", apiSigningUiOptions.getNotaryHostThankYouOptions().getReviewDocumentsButton(), is(sdkSigningUiOptions.getNotaryHostThankYouOptions().getReviewDocumentsButton()));
        assertThat("'title' was not correctly set", apiSigningUiOptions.getOverviewOptions().getTitle(), is(sdkSigningUiOptions.getOverviewOptions().getTitle()));
        assertThat("'body' was not correctly set", apiSigningUiOptions.getOverviewOptions().getBody(), is(sdkSigningUiOptions.getOverviewOptions().getBody()));
        assertThat("'documentSection' was not correctly set", apiSigningUiOptions.getOverviewOptions().getDocumentSection(), is(sdkSigningUiOptions.getOverviewOptions().getDocumentSection()));
        assertThat("'uploadSection' was not correctly set", apiSigningUiOptions.getOverviewOptions().getUploadSection(), is(sdkSigningUiOptions.getOverviewOptions().getUploadSection()));

    }

    /**
     * Create an SDK SigningUiOptions.
     *
     * @return SDK SigningUiOptions.
     */
    private com.silanis.esl.sdk.SigningUiOptions createTypicalSDKSigningUiOptions() {
        return newSigningUiOptions()
                .withCompleteSummaryOptions(CompleteSummaryOptionsBuilder.newCompleteSummaryOptions()
                        .withFrom()
                        .withTitle()
                        .withMessage()
                        .withDownload()
                        .withReview()
                        .withContinue()
                        .build())
                .withInpersonWelcomeOptions(InpersonWelcomeOptionsBuilder.newInpersonWelcomeOptions()
                        .withTitle()
                        .withBody()
                        .withRecipientName()
                        .withRecipientEmail()
                        .withRecipientActionRequired()
                        .withRecipientRole()
                        .withRecipientStatus()
                        .build())
                .withInpersonHostThankYouOptions(InpersonHostThankYouOptionsBuilder.newInpersonHostThankYouOptions()
                        .withTitle()
                        .withBody()
                        .withRecipientName()
                        .withRecipientEmail()
                        .withRecipientRole()
                        .withRecipientStatus()
                        .withDownloadButton()
                        .withReviewDocumentsButton()
                        .build())
                .withNotaryWelcomeOptions(NotaryWelcomeOptionsBuilder.newNotaryWelcomeOptions()
                        .withTitle()
                        .withBody()
                        .withRecipientName()
                        .withRecipientEmail()
                        .withRecipientActionRequired()
                        .withNotaryTag()
                        .withRecipientRole()
                        .withRecipientStatus()
                        .build())
                .withNotaryHostThankYouOptions(NotaryHostThankYouOptionsBuilder.newNotaryHostThankYouOptions()
                        .withTitle()
                        .withBody()
                        .withRecipientName()
                        .withRecipientEmail()
                        .withRecipientRole()
                        .withNotaryTag()
                        .withRecipientStatus()
                        .withDownloadButton()
                        .withReviewDocumentsButton()
                        .build())
                .withOverviewOptions(OverviewOptionsBuilder.newOverviewOptions()
                        .withoutTitle()
                        .withBody()
                        .withDocumentSection()
                        .withUploadSection()
                        .build())
                .build();
    }

    /**
     * Create an API SigningUiOptions.
     *
     * @return API SigningUiOptions.
     */
    private SigningUiOptions createTypicalAPISigningUiOptions() {

        SigningUiOptions apiSigningUiOptions = new SigningUiOptions();

        apiSigningUiOptions.setCompleteSummaryOptions(new CompleteSummaryOptions()
                .setTitle(true)
                .setFrom(true)
                .setMessage(true)
                .setDownload(true)
                .setReview(true)
                .setContinue(true));
        apiSigningUiOptions.setInpersonWelcomeOptions(new InpersonWelcomeOptions()
                .setTitle(true)
                .setBody(true)
                .setRecipientName(true)
                .setRecipientEmail(true)
                .setRecipientActionRequired(true)
                .setRecipientRole(true)
                .setRecipientStatus(true));
        apiSigningUiOptions.setInpersonHostThankYouOptions(new InpersonHostThankYouOptions()
                .setTitle(true)
                .setBody(true)
                .setRecipientName(true)
                .setRecipientEmail(true)
                .setRecipientRole(true)
                .setRecipientStatus(true)
                .setDownloadButton(true)
                .setReviewDocumentsButton(true));
        apiSigningUiOptions.setNotaryWelcomeOptions(new NotaryWelcomeOptions()
                .setTitle(true)
                .setBody(true)
                .setRecipientName(true)
                .setRecipientEmail(true)
                .setRecipientActionRequired(true)
                .setNotaryTag(true)
                .setRecipientRole(true)
                .setRecipientStatus(true));
        apiSigningUiOptions.setNotaryHostThankYouOptions(new NotaryHostThankYouOptions()
                .setTitle(true)
                .setBody(true)
                .setRecipientName(true)
                .setRecipientEmail(true)
                .setRecipientRole(true)
                .setNotaryTag(true)
                .setRecipientStatus(true)
                .setDownloadButton(true)
                .setReviewDocumentsButton(true));
        apiSigningUiOptions.setOverviewOptions(new OverviewOptions()
                .setTitle(true)
                .setBody(true)
                .setUploadSection(true)
                .setDocumentSection(true));

        return apiSigningUiOptions;
    }
}
