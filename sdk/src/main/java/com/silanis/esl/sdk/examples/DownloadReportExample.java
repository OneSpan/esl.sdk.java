package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CompletionReport;
import com.silanis.esl.sdk.DelegationReport;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.UsageReport;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class DownloadReportExample extends SDKSample {

    public PackageId package2Id;
    public CompletionReport sdkCompletionReportForSenderDraft, sdkCompletionReportForSenderSent, sdkCompletionReportDraft, sdkCompletionReportSent;
    public UsageReport sdkUsageReport;
    public DelegationReport sdkDelegationReportForAccountWithoutDate, sdkDelegationReportForAccount, sdkDelegationReportForSender;

    public String csvCompletionReportForSenderDraft, csvCompletionReportForSenderSent, csvCompletionReportDraft, csvCompletionReportSent;
    public String csvUsageReport;
    public String csvDelegationReportForAccountWithoutDate, csvDelegationReportForAccount, csvDelegationReportForSender;

    public static void main(String... args) {
        new DownloadReportExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs("This is a package created using OneSpan Sign SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withTitle("Managing Director")
                        .withCustomId("signer1")
                        .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName("First Document")
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(100, 100)
                                .withField(FieldBuilder.textField()
                                        .onPage(0)
                                        .atPosition(400, 100)
                                        .withSize(200, 50))))
                .build();

        packageId = eslClient.createPackage(superDuperPackage);

        DocumentPackage superDuperPackage2 = newPackageNamed( "DownloadReportForSent " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs("This is a package created using OneSpan Sign SDK")
                .expiresAt(now().plusMonths(1).toDate())
                .withEmailMessage("This message should be delivered to all signers")
                .withSigner(newSignerWithEmail(email1)
                    .withFirstName("John")
                    .withLastName("Smith")
                    .withTitle("Managing Director")
                    .withCustomId("signer1")
                    .withCompany("Acme Inc."))
                .withDocument(newDocumentWithName("First Document")
                    .fromStream(documentInputStream2, DocumentType.PDF)
                    .withSignature(signatureFor(email1)
                        .onPage(0)
                        .atPosition(100, 100)
                        .withField(FieldBuilder.textField()
                            .onPage(0)
                            .atPosition(400, 100)
                            .withSize(200, 50))))
                .build();

        package2Id = eslClient.createAndSendPackage(superDuperPackage2);

        // Date and time range to get completion/usage report.
        Calendar fromCalendar = new GregorianCalendar();
        fromCalendar.add(Calendar.MINUTE, -5);
        Date from = fromCalendar.getTime();

        Calendar toCalendar = new GregorianCalendar();
        toCalendar.setTime(new Date(System.currentTimeMillis()));
        toCalendar.add(Calendar.MINUTE, 5);
        Date to = toCalendar.getTime();

        // Download the completion report for a sender
        sdkCompletionReportForSenderDraft = eslClient.getReportService().downloadCompletionReport(PackageStatus.DRAFT, senderUID, from, to);
        csvCompletionReportForSenderDraft = eslClient.getReportService().downloadCompletionReportAsCSV(PackageStatus.DRAFT, senderUID, from, to);

        sdkCompletionReportForSenderSent = eslClient.getReportService().downloadCompletionReport(PackageStatus.SENT, senderUID, from, to);
        csvCompletionReportForSenderSent = eslClient.getReportService().downloadCompletionReportAsCSV(PackageStatus.SENT, senderUID, from, to);

        // Download the completion report for all senders
        sdkCompletionReportDraft = eslClient.getReportService().downloadCompletionReport(PackageStatus.DRAFT, from, to);
        csvCompletionReportDraft = eslClient.getReportService().downloadCompletionReportAsCSV(PackageStatus.DRAFT, from, to);

        sdkCompletionReportSent = eslClient.getReportService().downloadCompletionReport(PackageStatus.SENT, from, to);
        csvCompletionReportSent = eslClient.getReportService().downloadCompletionReportAsCSV(PackageStatus.SENT, from, to);

        // Download the usage report
        sdkUsageReport = eslClient.getReportService().downloadUsageReport(from, to);
        csvUsageReport = eslClient.getReportService().downloadUsageReportAsCSV(from, to);

        // Download the delegation report for a sender
        sdkDelegationReportForAccountWithoutDate = eslClient.getReportService().downloadDelegationReport();
        sdkDelegationReportForAccount = eslClient.getReportService().downloadDelegationReport(from, to);
        sdkDelegationReportForSender = eslClient.getReportService().downloadDelegationReport(senderUID, from, to);

        csvDelegationReportForAccountWithoutDate = eslClient.getReportService().downloadDelegationReportAsCSV();
        csvDelegationReportForAccount = eslClient.getReportService().downloadDelegationReportAsCSV(from, to);
        csvDelegationReportForSender = eslClient.getReportService().downloadDelegationReportAsCSV(senderUID, from, to);
    }
}
