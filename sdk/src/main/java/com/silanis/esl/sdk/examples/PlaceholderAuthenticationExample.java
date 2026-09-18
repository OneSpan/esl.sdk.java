package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Authentication;
import com.silanis.esl.sdk.AuthenticationMethod;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.PlaceholderSigner;
import com.silanis.esl.sdk.builder.SignerBuilder.QASMSBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.ChallengeBuilder.firstQuestion;
import static com.silanis.esl.sdk.builder.SignerBuilder.newPlaceholderSigner;
import static com.silanis.esl.sdk.builder.SignerInformationForLexisNexisBuilder.newSignerInformationForLexisNexis;

/**
 * Example class demonstrating a template with five PLACEHOLDER role signers, each configured
 * with a different authentication method: SMS, SSO, KBA, Q&amp;A and QASMS.
 * <p>
 * OneSpan Sign accepts a placeholder's SMS, KBA, Q&amp;A and QASMS authentication without the
 * detailed information (phone number, questions/answers, KBA identity information) being filled
 * in, since a placeholder does not have a known identity yet; that information is only required
 * once the placeholder is replaced by a named recipient.
 */
public class PlaceholderAuthenticationExample extends SDKSample {

    public static final String DOCUMENT_NAME = "Placeholder Authentication Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String TEMPLATE_NAME = "PlaceholderAuthenticationExample Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String TEMPLATE_DESCRIPTION = "This is a template created using OneSpan Sign SDK";

    public static final String SMS_PLACEHOLDER_ID = "sms-placeholder-id";
    public static final String SSO_PLACEHOLDER_ID = "sso-placeholder-id";
    public static final String KBA_PLACEHOLDER_ID = "kba-placeholder-id";
    public static final String QNA_PLACEHOLDER_ID = "qna-placeholder-id";
    public static final String QASMS_PLACEHOLDER_ID = "qasms-placeholder-id";

    public PackageId templateId;
    public DocumentPackage retrievedTemplate;

    public static void main(String... args) {
        new PlaceholderAuthenticationExample().run();
    }

    @Override
    public void execute() {
        PlaceholderSigner smsPlaceholder = new PlaceholderSigner(SMS_PLACEHOLDER_ID);
        PlaceholderSigner ssoPlaceholder = new PlaceholderSigner(SSO_PLACEHOLDER_ID);
        PlaceholderSigner kbaPlaceholder = new PlaceholderSigner(KBA_PLACEHOLDER_ID);
        PlaceholderSigner qnaPlaceholder = new PlaceholderSigner(QNA_PLACEHOLDER_ID);
        PlaceholderSigner qasmsPlaceholder = new PlaceholderSigner(QASMS_PLACEHOLDER_ID);

        DocumentPackage template = newPackageNamed(TEMPLATE_NAME)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withSigner(newPlaceholderSigner(smsPlaceholder)
                        .withAuthentication(new Authentication(AuthenticationMethod.SMS)))
                .withSigner(newPlaceholderSigner(ssoPlaceholder)
                        .withSSOAuthentication())
                .withSigner(newPlaceholderSigner(kbaPlaceholder)
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForLexisNexis()))
                .withSigner(newPlaceholderSigner(qnaPlaceholder)
                        .challengedWithQuestions(firstQuestion(null)))
                .withSigner(newPlaceholderSigner(qasmsPlaceholder)
                        .challengedWithQASMS(new QASMSBuilder()))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .withId(DOCUMENT_ID)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(smsPlaceholder)
                                .onPage(0)
                                .atPosition(100, 100))
                        .withSignature(signatureFor(ssoPlaceholder)
                                .onPage(0)
                                .atPosition(100, 200))
                        .withSignature(signatureFor(kbaPlaceholder)
                                .onPage(0)
                                .atPosition(100, 300))
                        .withSignature(signatureFor(qnaPlaceholder)
                                .onPage(0)
                                .atPosition(100, 400))
                        .withSignature(signatureFor(qasmsPlaceholder)
                                .onPage(0)
                                .atPosition(100, 500)))
                .build();

        templateId = eslClient.getTemplateService().createTemplate(template);
        retrievedTemplate = eslClient.getPackage(templateId);
    }
}
