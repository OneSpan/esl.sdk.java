package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.*;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example with a simple field and checkbox
 */
public class GenericFieldsExample extends SDKSample {

    private String email1;
    private InputStream documentInputStream1;
    public static final String DOCUMENT_NAME = "First Document";
    public static final int TEXTFIELD_PAGE = 0;
    public static final int TEXTFIELD_POSITION_X = 400;
    public static final int TEXTFIELD_POSITION_Y = 200;
    public static final int CHECKBOX_1_PAGE = 0;
    public static final double CHECKBOX_1_WIDTH = 20;
    public static final double CHECKBOX_1_HEIGHT = 20;
    public static final int CHECKBOX_1_POSITION_X = 400;
    public static final int CHECKBOX_1_POSITION_Y = 300;
    public static final int CHECKBOX_2_PAGE = 0;
    public static final double CHECKBOX_2_WIDTH = 20;
    public static final double CHECKBOX_2_HEIGHT = 20;
    public static final int CHECKBOX_2_POSITION_X = 400;
    public static final int CHECKBOX_2_POSITION_Y = 350;
    public static final boolean CHECKBOX_2_VALUE = true;
    public static final int RADIO_1_PAGE = 0;
    public static final double RADIO_1_WIDTH = 20;
    public static final double RADIO_1_HEIGHT = 20;
    public static final int RADIO_1_POSITION_X = 400;
    public static final int RADIO_1_POSITION_Y = 400;
    public static final String RADIO_1_GROUP = "group";
    public static final int RADIO_2_PAGE = 0;
    public static final double RADIO_2_WIDTH = 20;
    public static final double RADIO_2_HEIGHT = 20;
    public static final int RADIO_2_POSITION_X = 400;
    public static final int RADIO_2_POSITION_Y = 450;
    public static final boolean RADIO_2_VALUE = true;
    public static final String RADIO_2_GROUP = "group";

    public static void main( String... args ) {
        new GenericFieldsExample( Props.get() ).run();
    }

    public GenericFieldsExample( Properties properties ) {
        this( properties.getProperty( "api.key" ),
                properties.getProperty( "api.url" ),
                properties.getProperty( "1.email" ) );
    }

    public GenericFieldsExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "GenericFieldsExample Policy " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
                .describedAs("This is a package created using the e-SignLive SDK")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                        .fromStream(documentInputStream1, DocumentType.PDF)
                        .withSignature(signatureFor(email1)
                                .onPage(0)
                                .atPosition(400, 100)
                                .withField(textField()
                                        .onPage(TEXTFIELD_PAGE)
                                        .atPosition(TEXTFIELD_POSITION_X, TEXTFIELD_POSITION_Y))
                                .withField(checkBox()
                                        .onPage(CHECKBOX_1_PAGE)
                                        .withSize(CHECKBOX_1_WIDTH, CHECKBOX_1_HEIGHT)
                                        .atPosition(CHECKBOX_1_POSITION_X, CHECKBOX_1_POSITION_Y))
                                .withField(checkBox()
                                        .withValue(CHECKBOX_2_VALUE)
                                        .onPage(CHECKBOX_2_PAGE)
                                        .withSize(CHECKBOX_2_WIDTH, CHECKBOX_2_HEIGHT)
                                        .atPosition(CHECKBOX_2_POSITION_X, CHECKBOX_2_POSITION_Y))
                                .withField(radioButton(RADIO_1_GROUP)
                                        .onPage(RADIO_1_PAGE)
                                        .withSize(RADIO_1_WIDTH, RADIO_1_HEIGHT)
                                        .atPosition(RADIO_1_POSITION_X, RADIO_1_POSITION_Y))
                                .withField(radioButton(RADIO_1_GROUP)
                                        .withValue(RADIO_2_VALUE)
                                        .onPage(RADIO_2_PAGE)
                                        .withSize(RADIO_2_WIDTH, RADIO_2_HEIGHT)
                                        .atPosition(RADIO_2_POSITION_X, RADIO_2_POSITION_Y))))
                .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}