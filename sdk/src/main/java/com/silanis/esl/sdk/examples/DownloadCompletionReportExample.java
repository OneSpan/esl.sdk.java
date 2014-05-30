package com.silanis.esl.sdk.examples;

import com.silanis.esl.api.model.PackageStatus;
import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class DownloadCompletionReportExample extends SDKSample {

    private String email1;
    private String senderUID;
    private com.silanis.esl.sdk.CompletionReport sdkCompetionReport;

    public static void main(String... args) {
        new DownloadCompletionReportExample(Props.get(), "senderUID").run();
    }

    public com.silanis.esl.sdk.CompletionReport getSdkCompetionReport() {
        return sdkCompetionReport;
    }

    public DownloadCompletionReportExample(Properties properties, String senderUID) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"),
                senderUID);
    }

    public DownloadCompletionReportExample(String apiKey, String apiUrl, String email1, String senderUID) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.senderUID = senderUID;
    }

    public String getEmail1() {
        return email1;
    }

    @Override
    public void execute() {
        InputStream documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");

        DocumentPackage superDuperPackage = newPackageNamed( "DownloadCompletionReport " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs("This is a package created using the e-SignLive SDK")
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

        packageId = eslClient.createAndSendPackage(superDuperPackage);

        // Date and time range to get the completion report

        Calendar fromCalendar = new GregorianCalendar(2014, 4, 20, 0, 0, 0);
        Date from = fromCalendar.getTime();

        Calendar toCalendar = new GregorianCalendar();
        toCalendar.setTime(new Date(System.currentTimeMillis()));
        Date to = toCalendar.getTime();

        sdkCompetionReport = eslClient.getPackageService().downloadCompletionReport(PackageStatus.SENT, senderUID, from, to);

    }
}
