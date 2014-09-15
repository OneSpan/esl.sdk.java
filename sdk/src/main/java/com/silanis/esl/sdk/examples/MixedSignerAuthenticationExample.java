package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.SignerBuilder;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA;

/**
 * Created by schoi on 9/12/14.
 */
public class MixedSignerAuthenticationExample extends SDKSample {

    private InputStream documentInputStream;
    private DocumentPackage retrievedPackage;

    private final String PACKAGE_NAME = "MixedSignerAuthenticationExample " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    private final String PACKAGE_DESCRIPTION = "This is a mixed signer authentication example";
    private final String DOCUMENT_NAME = "First Document pdf";

    public Signer signerWithAuthenticationEquifaxCanada;
    public Signer signerWithAuthenticationEquifaxUSA;

    public final String SIGNER1_EMAIL;
    public final String SIGNER2_EMAIL;

    public static void main( String... args ) {
        new MixedSignerAuthenticationExample(Props.get()).run();
    }

    public MixedSignerAuthenticationExample(Properties props) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ),
                props.getProperty( "2.email" ));
    }

    public MixedSignerAuthenticationExample(String apiKey, String apiUrl, String email1, String email2) {
        super( apiKey, apiUrl );
        this.SIGNER1_EMAIL = email1;
        this.SIGNER2_EMAIL = email2;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

    public void execute() {
        signerWithAuthenticationEquifaxCanada = SignerBuilder.newSignerWithEmail(SIGNER1_EMAIL)
                        .withFirstName("Signer1")
                        .withLastName("Canada")
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxCanada()
                                .withFirstName("Signer1")
                                .withLastName("Canada")
                                .withAddress("1111")
                                .withCity("Montreal")
                                .withZip("A1A1A1")
                                .withState("QC")
                                .withTimeAtAddress("1")
                                .withDateOfBirth(new DateTime().minusYears(25).toDate())
                                .withDriversLicense("1234567")
                                .withSocialInsuranceNumber("123456798654321")
                                .withHomePhoneNumber("7943624658")
                                .build())
                        .challengedWithQuestions(SignerBuilder.ChallengeBuilder.firstQuestion("What's your favorite restaurant? (answer: Staffany)")
                        .answer("Staffany")
                        .secondQuestion("What sport do you play? (answer: hockey)")
                        .answer("hockey"))
                        .build();

        signerWithAuthenticationEquifaxUSA = SignerBuilder.newSignerWithEmail(SIGNER2_EMAIL)
                .withFirstName("Signer2")
                .withLastName("USA")
                .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxUSA()
                        .withFirstName("Signer1")
                        .withLastName("USA")
                        .withAddress("2222")
                        .withCity("New York")
                        .withZip("A1A1A1")
                        .withState("NY")
                        .withDateOfBirth(new DateTime().minusYears(37).toDate())
                        .withSocialSecurityNumber("123456798654321")
                        .withHomePhoneNumber("7943624658")
                        .build())
                .challengedWithQuestions(SignerBuilder.ChallengeBuilder.firstQuestion("What's your favorite sport? (answer: golf)")
                        .answer("golf")
                        .secondQuestion("What music instrument do you play? (answer: drums)")
                        .answer("drums"))
                .build();

        DocumentPackage superDuperPackage = newPackageNamed(PACKAGE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(signerWithAuthenticationEquifaxCanada)
                .withSigner(signerWithAuthenticationEquifaxUSA)
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream, DocumentType.PDF)
                        .withSignature(signatureFor(SIGNER1_EMAIL).build())
                        .withSignature(signatureFor(SIGNER2_EMAIL).build()))
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }

}
