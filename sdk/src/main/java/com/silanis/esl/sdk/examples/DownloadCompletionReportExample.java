package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.internal.Converter;

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
    private com.silanis.esl.sdk.CompletionReport sdkCompletionReport;

    public static void main(String... args) {
        new DownloadCompletionReportExample(Props.get()).run();
    }

    public com.silanis.esl.sdk.CompletionReport getSdkCompletionReport() {
        return sdkCompletionReport;
    }

    public DownloadCompletionReportExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"));
    }

    public DownloadCompletionReportExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        this.senderUID = Converter.apiKeyToUID(apiKey);
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

        packageId = eslClient.createPackage(superDuperPackage);

        // Date and time range to get completion report.
        Calendar fromCalendar = new GregorianCalendar();
        fromCalendar.add(Calendar.DATE, -1);
        Date from = fromCalendar.getTime();

        Calendar toCalendar = new GregorianCalendar();
        toCalendar.setTime(new Date(System.currentTimeMillis()));
        Date to = toCalendar.getTime();

        sdkCompletionReport = eslClient.getPackageService().downloadCompletionReport(com.silanis.esl.sdk.PackageStatus.DRAFT, senderUID, from, to);
        String csvCompletionReport = eslClient.getPackageService().downloadCompletionReportAsCSV(com.silanis.esl.sdk.PackageStatus.DRAFT, senderUID, from, to);
        System.out.println(" wtf" );
    }
}
