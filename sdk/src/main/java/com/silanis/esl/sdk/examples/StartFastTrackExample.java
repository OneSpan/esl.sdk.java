package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.DocumentBuilder;
import com.silanis.esl.sdk.builder.FastTrackSignerBuilder;
import com.silanis.esl.sdk.builder.SignatureBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;

/**
 * Created by schoi on 1/23/15.
 */
public class StartFastTrackExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;

    public PackageId templateId;
    public String signingUrl;

    public static final String TEMPLATE_NAME = "StartFastTrackExample Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String TEMPLATE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String TEMPLATE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER_FIRST = "John";
    public static final String TEMPLATE_SIGNER_LAST = "Smith";
    public static final String PLACEHOLDER_ID = "PlaceholderId1";

    public static final String FAST_TRACK_SIGNER_FIRST = "Patty";
    public static final String FAST_TRACK_SIGNER_LAST = "Galant";

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";

    public static void main(String... args) {
        new StartFastTrackExample(Props.get()).run();
    }

    public StartFastTrackExample(Properties properties) {
        this(properties.getProperty("api.key"),
             properties.getProperty("api.url"),
             properties.getProperty("1.email"));
    }

    public StartFastTrackExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
    }

    @Override
    public void execute() {
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document.pdf");
        Signer signer1 = SignerBuilder.newSignerWithEmail(email1)
                                      .withFirstName(TEMPLATE_SIGNER_FIRST)
                                      .withLastName(TEMPLATE_SIGNER_LAST).build();
        Signer signer2 = SignerBuilder.newSignerPlaceholder(new Placeholder(PLACEHOLDER_ID)).build();

        DocumentPackage template = newPackageNamed(TEMPLATE_NAME)
                .describedAs(TEMPLATE_DESCRIPTION)
                .withEmailMessage(TEMPLATE_EMAIL_MESSAGE)
                .withSigner(signer1)
                .withSigner(signer2)
                .withDocument(DocumentBuilder.newDocumentWithName(DOCUMENT_NAME)
                                             .withId(DOCUMENT_ID)
                                             .fromStream(documentInputStream1, DocumentType.PDF)
                                             .withSignature(SignatureBuilder.signatureFor(new Placeholder(PLACEHOLDER_ID))
                                                                            .onPage(0)
                                                                            .atPosition(100,100))
                                             .build())
                .build();

        templateId = eslClient.getTemplateService().createTemplate(template);

        FastTrackSigner signer = FastTrackSignerBuilder.newSignerWithId(signer2.getId())
                .withEmail(getRandomEmail())
                .withFirstName(FAST_TRACK_SIGNER_FIRST)
                .withLastName(FAST_TRACK_SIGNER_LAST)
                .build();

        signingUrl = eslClient.getPackageService().startFastTrack(templateId, Collections.singletonList(signer));
//        retrievedPackage = eslClient.getPackage(templateId);
    }

}
