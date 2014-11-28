package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import org.joda.time.DateTime;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxUSABuilder.newSignerInformationForEquifaxUSA;

/**
 * Created by schoi on 9/8/14.
 */
public class SignerInformationForEquifaxUSAExample extends SDKSample {

    private InputStream documentInputStream;
    private DocumentPackage retrievedPackage;

    private final String PACKAGE_NAME = "SignerInformationForEquifaxUSAExample " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    private final String PACKAGE_DESCRIPTION = "This is a SignerInformation for EquifaxUSA example";

    public final String EMAIL;

    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Smith";
    public static final String ADDRESS = "456666 asdfasdf";
    public static final String CITY = "Montreal";
    public static final String ZIP = "12311";
    public static final String STATE = "CA";
    public static final String SOCIAL_SECURITY_NUMBER = "123132123";
    public static final Date   DATE_OF_BIRTH = new DateTime().minusYears(15).toDate();
    public static final String HOME_PHONE_NUMBER = "123456789";
    public static final String DRIVERS_LICENSE_NUMBER = "465323785";
    public static final Integer TIME_AT_ADDRESS = 32;
    public static final String FIRST_DOCUMENT_NAME = "First Document pdf";

    public static void main( String... args ) {
        new SignerInformationForEquifaxUSAExample(Props.get()).run();
    }

    public SignerInformationForEquifaxUSAExample(Properties props) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ) );
    }

    public SignerInformationForEquifaxUSAExample(String apiKey, String apiUrl, String email) {
        super( apiKey, apiUrl );
        this.EMAIL = email;
        documentInputStream = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public DocumentPackage getRetrievedPackage() {
        return retrievedPackage;
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(PACKAGE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(EMAIL)
                        .withFirstName(FIRST_NAME)
                        .withLastName(LAST_NAME)
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxUSA()
                                .withFirstName(FIRST_NAME)
                                .withLastName(LAST_NAME)
                                .withStreetAddress(ADDRESS)
                                .withCity(CITY)
                                .withZip(ZIP)
                                .withState(STATE)
                                .withSocialSecurityNumber(SOCIAL_SECURITY_NUMBER)
                                .withDateOfBirth(DATE_OF_BIRTH)
                                .withHomePhoneNumber(HOME_PHONE_NUMBER)
                                .withDriversLicenseNumber(DRIVERS_LICENSE_NUMBER)
                                .withTimeAtAddress(TIME_AT_ADDRESS)))
                .withDocument(newDocumentWithName(FIRST_DOCUMENT_NAME)
                        .fromStream(documentInputStream, DocumentType.PDF)
                        .withSignature(signatureFor(EMAIL).build()))
                .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
