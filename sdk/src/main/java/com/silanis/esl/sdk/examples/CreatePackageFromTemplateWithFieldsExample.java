package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;
import com.silanis.esl.sdk.builder.PackageBuilder;

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
 * Created by schoi on 5/26/15.
 */
public class CreatePackageFromTemplateWithFieldsExample extends SDKSample {
    private String email1, email2;
    private InputStream documentInputStream1;

    public static final String DOCUMENT_NAME = "First Document";
    public static final String DOCUMENT_ID = "doc1";
    public static final String TEMPLATE_NAME = "CreatePackageFromTemplateWithFieldsExample Template: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_NAME = "CreateTemplateFromPackageExample: " + new SimpleDateFormat("HH:mm:ss").format(new Date());
    public static final String PACKAGE_DESCRIPTION = "This is a package created using the e-SignLive SDK";
    public static final String PACKAGE_EMAIL_MESSAGE = "This message should be delivered to all signers";
    public static final String TEMPLATE_SIGNER1_FIRST = "John";
    public static final String TEMPLATE_SIGNER1_LAST = "Smith";

    public static final String PACKAGE_SIGNER2_FIRST = "Elvis";
    public static final String PACKAGE_SIGNER2_LAST = "Presley";
    public static final String PACKAGE_SIGNER2_TITLE = "The King";
    public static final String PACKAGE_SIGNER2_COMPANY = "Elvis Presley International";
    public static final String PACKAGE_SIGNER2_CUSTOM_ID = "Signer2";

    public static final String TEXTFIELD_ID = "textFieldId";
    public static final int TEXTFIELD_PAGE = 0;
    public static final String CHECKBOX_1_ID = "checkbox1Id";
    public static final int CHECKBOX_1_PAGE = 0;
    public static final String CHECKBOX_2_ID = "checkbox2Id";
    public static final int CHECKBOX_2_PAGE = 0;
    public static final boolean CHECKBOX_2_VALUE = true;
    public static final String RADIO_1_ID = "radio1Id";
    public static final int RADIO_1_PAGE = 0;
    public static final String RADIO_1_GROUP = "group";
    public static final String RADIO_2_ID = "radio2Id";
    public static final int RADIO_2_PAGE = 0;
    public static final boolean RADIO_2_VALUE = true;
    public static final String RADIO_2_GROUP = "group";
    public static final String DROP_LIST_ID = "dropListId";
    public static final int DROP_LIST_PAGE = 0;
    public static final String DROP_LIST_OPTION1 = "one";
    public static final String DROP_LIST_OPTION2 = "two";
    public static final String DROP_LIST_OPTION3 = "three";
    public static final String TEXT_AREA_ID = "textAreaId";
    public static final int TEXT_AREA_PAGE = 0;
    public static final String TEXT_AREA_VALUE = "textAreaValue";
    public static final String LABEL_FIELD_ID = "labelFieldId";
    public static final int LABEL_FIELD_PAGE = 0;
    public static final String LABEL_FIELD_VALUE = "labelFieldValue";

    private int textfieldPositionX = 400;
    private int textfieldPositionY = 200;
    private double checkbox1Width = 20;
    private double checkbox1Height = 20;
    private int checkbox1PositionX = 400;
    private int checkbox1PositionY = 300;
    private double checkbox2Width = 20;
    private double checkbox2Height = 20;
    private int checkbox2PositionX = 400;
    private int checkbox2PositionY = 350;
    private double radio1Width = 20;
    private double radio1Height = 20;
    private int radio1PositionX = 400;
    private int radio1PositionY = 400;
    private double radio2Width = 20;
    private double radio2Height = 20;
    private int radio2PositionX = 400;
    private int radio2PositionY = 450;
    private double dropListWidth = 100;
    private double dropListHeight = 200;
    private int dropListPositionX = 100;
    private int dropListPositionY = 100;
    private double textAreaWidth = 400;
    private double textAreaHeight = 600;
    private int textAreaPositionX = 200;
    private int textAreaPositionY = 200;
    private double labelFieldWidth = 100;
    private double labelFieldHeight = 60;
    private int labelFieldPositionX = 150;
    private int labelFieldPositionY = 150;

    public static void main( String... args ) {
        new CreatePackageFromTemplateWithFieldsExample(Props.get()).run();
    }

    public CreatePackageFromTemplateWithFieldsExample( Properties props ) {
        this( props.getProperty( "api.key" ),
              props.getProperty( "api.url" ),
              props.getProperty( "1.email" ),
              props.getProperty( "2.email" ));
    }

    public CreatePackageFromTemplateWithFieldsExample( String apiKey, String apiUrl, String email1, String email2 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        this.email2 = email2;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage template = newPackageNamed(TEMPLATE_NAME)
                .describedAs(PACKAGE_DESCRIPTION)
                .withSigner(newSignerWithEmail(email1)
                    .withFirstName(TEMPLATE_SIGNER1_FIRST)
                    .withLastName(TEMPLATE_SIGNER1_LAST))
                .withDocument(newDocumentWithName(DOCUMENT_NAME)
                    .fromStream(documentInputStream1, DocumentType.PDF)
                    .withSignature(signatureFor(email1)
                        .onPage(0)
                        .atPosition(400, 100)
                        .withField(textField()
                                           .withId(new FieldId(TEXTFIELD_ID))
                                           .onPage(TEXTFIELD_PAGE)
                                           .atPosition(textfieldPositionX, textfieldPositionY))
                        .withField(checkBox()
                                           .withId(new FieldId(CHECKBOX_1_ID))
                                           .onPage(CHECKBOX_1_PAGE)
                                           .withSize(checkbox1Width, checkbox1Height)
                                           .atPosition(checkbox1PositionX, checkbox1PositionY))
                        .withField(checkBox()
                                           .withId(new FieldId(CHECKBOX_2_ID))
                                           .withValue(CHECKBOX_2_VALUE)
                                           .onPage(CHECKBOX_2_PAGE)
                                           .withSize(checkbox2Width, checkbox2Height)
                                           .atPosition(checkbox2PositionX, checkbox2PositionY))
                        .withField(radioButton(RADIO_1_GROUP)
                                           .withId(new FieldId(RADIO_1_ID))
                                           .onPage(RADIO_1_PAGE)
                                           .withSize(radio1Width, radio1Height)
                                           .atPosition(radio1PositionX, radio1PositionY))
                        .withField(radioButton(RADIO_2_GROUP)
                                           .withId(new FieldId(RADIO_2_ID))
                                           .withValue(RADIO_2_VALUE)
                                           .onPage(RADIO_2_PAGE)
                                           .withSize(radio2Width, radio2Height)
                                           .atPosition(radio2PositionX, radio2PositionY))
                        .withField(dropList()
                                           .withId(new FieldId(DROP_LIST_ID))
                                           .withValue(DROP_LIST_OPTION2)
                                           .withValidation(FieldValidatorBuilder.basic()
                                                                                .withOption(DROP_LIST_OPTION1)
                                                                                .withOption(DROP_LIST_OPTION2)
                                                                                .withOption(DROP_LIST_OPTION3))
                                           .onPage(DROP_LIST_PAGE)
                                           .withSize(dropListWidth, dropListHeight)
                                           .atPosition(dropListPositionX, dropListPositionY))
                        .withField(textArea()
                                           .withId(new FieldId(TEXT_AREA_ID))
                                           .withValue(TEXT_AREA_VALUE)
                                           .onPage(TEXT_AREA_PAGE)
                                           .withSize(textAreaWidth, textAreaHeight)
                                           .atPosition(textAreaPositionX, textAreaPositionY))
                        .withField(labelfield()
                                           .withId(new FieldId(LABEL_FIELD_ID))
                                           .withValue(LABEL_FIELD_VALUE)
                                           .onPage(LABEL_FIELD_PAGE)
                                           .withSize(labelFieldWidth, labelFieldHeight)
                                           .atPosition(labelFieldPositionX, labelFieldPositionY))))
                .build();

        PackageId templateId = eslClient.getTemplateService().createTemplate(template);


        template.setId(templateId);

        DocumentPackage newPackage = PackageBuilder.newPackageNamed(PACKAGE_NAME)
                                        .describedAs(PACKAGE_DESCRIPTION)
                                        .withEmailMessage(PACKAGE_EMAIL_MESSAGE)
                                        .withSigner(newSignerWithEmail(email2)
                                                            .withFirstName(PACKAGE_SIGNER2_FIRST)
                                                            .withLastName(PACKAGE_SIGNER2_LAST)
                                                            .withTitle(PACKAGE_SIGNER2_TITLE)
                                                            .withCompany(PACKAGE_SIGNER2_COMPANY)
                                                            .withCustomId(PACKAGE_SIGNER2_CUSTOM_ID))
                                        .build();

        packageId = eslClient.getTemplateService().createPackageFromTemplate(templateId, newPackage);
        retrievedPackage = eslClient.getPackage( packageId );
    }
}
