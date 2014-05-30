package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.PackageStatus;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-05-29.
 */
public class CompletionReportConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.CompletionReport sdkCompletionReport1 = null;
    private com.silanis.esl.sdk.CompletionReport sdkCompletionReport2 = null;
    private com.silanis.esl.api.model.CompletionReport apiCompletionReport1 = null;
    private com.silanis.esl.api.model.CompletionReport apiCompletionReport2 = null;
    private CompletionReportConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiCompletionReport1 = null;
        converter = new CompletionReportConverter(apiCompletionReport1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKCompletionReport(), is(nullValue()));
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {

    }

    @Override
    @Test
    public void convertSDKToSDK() {

    }

    @Override
    @Test
    public void convertAPIToAPI() {

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiCompletionReport1 = createTypicalAPICompletionReport();
        sdkCompletionReport1 = new CompletionReportConverter(apiCompletionReport1).toSDKCompletionReport();

        assertThat("Completion report has the wrong from date.", sdkCompletionReport1.getFrom(), is(apiCompletionReport1.getFrom()));
        assertThat("Completion report has the wrong to date.", sdkCompletionReport1.getTo(), is(apiCompletionReport1.getTo()));

        assertThat("Sender completion report has the wrong sender id.", sdkCompletionReport1.getSenders().get(0).getSender().getId(), is(apiCompletionReport1.getSenders().get(0).getSender().getId()));
        assertThat("Sender completion report has the wrong sender first name.", sdkCompletionReport1.getSenders().get(0).getSender().getFirstName(), is(apiCompletionReport1.getSenders().get(0).getSender().getFirstName()));
        assertThat("Sender completion report has the wrong sender last name.", sdkCompletionReport1.getSenders().get(0).getSender().getLastName(), is(apiCompletionReport1.getSenders().get(0).getSender().getLastName()));

        com.silanis.esl.api.model.PackageCompletionReport apiPackageCompletionReport = apiCompletionReport1.getSenders().get(0).getPackages().get(0);
        com.silanis.esl.sdk.PackageCompletionReport sdkPackageCompletionReport = sdkCompletionReport1.getSenders().get(0).getPackages().get(0);
        assertThat("Package completion report has the wrong id.", sdkPackageCompletionReport.getId(), is(apiPackageCompletionReport.getId()));
        assertThat("Package completion report has the wrong name.", sdkPackageCompletionReport.getName(), is(apiPackageCompletionReport.getName()));
        assertThat("Package completion report has the wrong package status.", sdkPackageCompletionReport.getPackageStatus(), is(apiPackageCompletionReport.getStatus()));
        assertThat("Package completion report has the wrong created date.", sdkPackageCompletionReport.getCreated(), is(apiPackageCompletionReport.getCreated()));
        assertThat("Package completion report should have 1 document completion report.", sdkPackageCompletionReport.getDocuments().size(), is(1));
        assertThat("Package completion report should have 1 signer completion report.", sdkPackageCompletionReport.getSigners().size(), is(1));

        com.silanis.esl.api.model.DocumentsCompletionReport apiDocumentsCompletionReport = apiPackageCompletionReport.getDocuments().get(0);
        com.silanis.esl.sdk.DocumentsCompletionReport sdkDocumentsCompletionReport = sdkPackageCompletionReport.getDocuments().get(0);
        assertThat("Document completion report has the wrong id.", sdkDocumentsCompletionReport.getId(), is(apiDocumentsCompletionReport.getId()));
        assertThat("Document completion report has the wrong name.", sdkDocumentsCompletionReport.getName(), is(apiDocumentsCompletionReport.getName()));
        assertThat("Document completion report has the wrong first signed date.", sdkDocumentsCompletionReport.getFirstSigned(), is(apiDocumentsCompletionReport.getFirstSigned()));
        assertThat("Document completion report has the wrong last signed date.", sdkDocumentsCompletionReport.getLastSigned(), is(apiDocumentsCompletionReport.getLastSigned()));

        com.silanis.esl.api.model.SignersCompletionReport apiSignersCompletionReport = apiPackageCompletionReport.getSigners().get(0);
        com.silanis.esl.sdk.SignersCompletionReport sdkSignersCompletionReport = sdkPackageCompletionReport.getSigners().get(0);
        assertThat("Signer completion report has the wrong id.", sdkSignersCompletionReport.getId(), is(apiSignersCompletionReport.getId()));
        assertThat("Signer completion report has the wrong email.", sdkSignersCompletionReport.getEmail(), is(apiSignersCompletionReport.getEmail()));
        assertThat("Signer completion report has the wrong first name.", sdkSignersCompletionReport.getFirstName(), is(apiSignersCompletionReport.getFirstName()));
        assertThat("Signer completion report has the wrong last name.", sdkSignersCompletionReport.getLastName(), is(apiSignersCompletionReport.getLastName()));
        assertThat("Signer completion report has the wrong last signed date.", sdkSignersCompletionReport.getLastSigned(), is(apiSignersCompletionReport.getLastSigned()));
        assertThat("Signer completion report has the wrong first signed date.", sdkSignersCompletionReport.getFirstSigned(), is(apiSignersCompletionReport.getFirstSigned()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

    }

    /**
     * Create a API CompletionReport object.
     *
     * @return APICompletionReport
     */
    private com.silanis.esl.api.model.CompletionReport createTypicalAPICompletionReport() {
        com.silanis.esl.api.model.DocumentsCompletionReport documentCompletionReport = new com.silanis.esl.api.model.DocumentsCompletionReport();
        documentCompletionReport.setId("docId");
        documentCompletionReport.setCompleted(false);
        documentCompletionReport.setName("documentName");
        documentCompletionReport.setFirstSigned(new Date(9));

        com.silanis.esl.api.model.SignersCompletionReport signersCompletionReport = new com.silanis.esl.api.model.SignersCompletionReport();
        signersCompletionReport.setId("signerId");
        signersCompletionReport.setEmail("email@email.com");
        signersCompletionReport.setFirstName("Patty");
        signersCompletionReport.setLastName("Galant");
        signersCompletionReport.setCompleted(false);

        com.silanis.esl.api.model.PackageCompletionReport packageCompletionReport = new com.silanis.esl.api.model.PackageCompletionReport();
        packageCompletionReport.setId("packageId");
        packageCompletionReport.setName("PackageName");
        packageCompletionReport.setStatus(PackageStatus.SENT);
        packageCompletionReport.addSigner(signersCompletionReport);
        packageCompletionReport.addDocument(documentCompletionReport);

        com.silanis.esl.api.model.Sender sender = new com.silanis.esl.api.model.Sender();
        sender.setEmail("sender@email.com");
        sender.setFirstName("SignerFirstName");
        sender.setLastName("SignerLastName");

        com.silanis.esl.api.model.SenderCompletionReport senderCompletionReport = new com.silanis.esl.api.model.SenderCompletionReport();
        senderCompletionReport.addPackage(packageCompletionReport);
        senderCompletionReport.setSender(sender);

        com.silanis.esl.api.model.CompletionReport completionReport = new com.silanis.esl.api.model.CompletionReport();
        completionReport.setTo(new Date(1234));
        completionReport.setFrom(new Date(5678));
        completionReport.addSender(senderCompletionReport);

        return completionReport;
    }
}
