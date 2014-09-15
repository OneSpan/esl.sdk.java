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
import static com.silanis.esl.sdk.builder.SignerInformationForEquifaxCanadaBuilder.newSignerInformationForEquifaxCanada;

/**
 * Created by schoi on 9/5/14.
 */
public class SignerInformationForEquifaxCanadaExample extends SDKSample {

    private InputStream documentInputStream;
    private DocumentPackage retrievedPackage;

    private final String PACKAGE_NAME = "SignerInformationForEquifaxCanadaExample " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    private final String PACKAGE_DESCRIPTION = "This is a SignerInformation for EquifaxCanada example";

    public final String EMAIL;

    public static final String FIRST_NAME = "Patty";
    public static final String LAST_NAME = "Galant";
    public static final String ADDRESS = "123 rue av";
    public static final String CITY = "montreal";
    public static final String ZIP = "h2h3h2";
    public static final String STATE = "QU";
    public static final String TIME_AT_ADDRESS = "123";
    public static final Date   DATE_OF_BIRTH = new DateTime().minusYears(25).toDate();
    public static final String DRIVERS_LICENSE = "1234567";
    public static final String SOCIAL_INSURANCE_NUMBER = "123456798654321";
    public static final String HOME_PHONE_NUMBER = "6485923567";
    public static final String FIRST_DOCUMENT_NAME = "First Document pdf";

    public static void main( String... args ) {
        new SignerInformationForEquifaxCanadaExample(Props.get()).run();
    }

    public SignerInformationForEquifaxCanadaExample(Properties props) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ));
    }

    public SignerInformationForEquifaxCanadaExample(String apiKey, String apiUrl, String email) {
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
                        .challengedWithKnowledgeBasedAuthentication(newSignerInformationForEquifaxCanada()
                                .withFirstName(FIRST_NAME)
                                .withLastName(LAST_NAME)
                                .withAddress(ADDRESS)
                                .withCity(CITY)
                                .withZip(ZIP)
                                .withState(STATE)
                                .withTimeAtAddress(TIME_AT_ADDRESS)
                                .withDateOfBirth(DATE_OF_BIRTH)
                                .withDriversLicense(DRIVERS_LICENSE)
                                .withSocialInsuranceNumber(SOCIAL_INSURANCE_NUMBER)
                                .withHomePhoneNumber(HOME_PHONE_NUMBER)))
                        .withDocument(newDocumentWithName(FIRST_DOCUMENT_NAME)
                                .fromStream(documentInputStream, DocumentType.PDF)
                                .withSignature(signatureFor(EMAIL).build()))
                        .build();

        packageId = eslClient.createAndSendPackage(superDuperPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
