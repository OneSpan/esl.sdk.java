package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.FieldId;
import com.silanis.esl.sdk.builder.DocumentPackageSettingsBuilder;
import com.silanis.esl.sdk.builder.FieldValidatorBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.checkBox;
import static com.silanis.esl.sdk.builder.FieldBuilder.datepicker;
import static com.silanis.esl.sdk.builder.FieldBuilder.dropList;
import static com.silanis.esl.sdk.builder.FieldBuilder.label;
import static com.silanis.esl.sdk.builder.FieldBuilder.radioButton;
import static com.silanis.esl.sdk.builder.FieldBuilder.textArea;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

/**
 * Example with a simple field and checkbox
 */
public class GenericFieldsExample extends SDKSample {

    public static final String DOCUMENT_NAME = "First Document";
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
    public static final String LABEL_ID = "labelId";
    public static final String LABEL_NAME = "labelName";
    public static final int LABEL_PAGE = 0;
    public static final String LABEL_VALUE = "labelValue";
    public static final String DATEPICKER_ID = "datepickerId";
    public static final String DATEPICKER_NAME = "datepickerName";
    public static final int DATEPICKER_PAGE = 0;
    public static final String DATEPICKER_VALUE = "datepickerValue";
    public static final String DATEPICKER_FORMAT = "MM-dd-YYYY";

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
    private double datepickerFieldWidth = 100;
    private double datepickerFieldHeight = 60;
    private int datepickerFieldPositionX = 150;
    private int datepickerFieldPositionY = 150;

    public static void main( String... args ) {
        new GenericFieldsExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using the e-SignLive SDK")
            .withSettings(DocumentPackageSettingsBuilder.newDocumentPackageSettings().withInPerson())
            .withSigner(newSignerWithEmail(email1)
                .withFirstName("John")
                .withLastName("Smith"))
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
                    .withField(label()
                        .withId(new FieldId(LABEL_ID))
                        .withName(LABEL_NAME)
                        .withValue(LABEL_VALUE)
                        .onPage(LABEL_PAGE)
                        .withSize(labelFieldWidth, labelFieldHeight)
                        .atPosition(labelFieldPositionX, labelFieldPositionY))
                    .withField(datepicker()
                        .withId(new FieldId(DATEPICKER_ID))
                        .withName(DATEPICKER_NAME)
                        .withValue(DATEPICKER_VALUE)
                        .onPage(DATEPICKER_PAGE)
                        .withSize(datepickerFieldWidth, datepickerFieldHeight)
                        .atPosition(datepickerFieldPositionX, datepickerFieldPositionY)
                        .withValidation(FieldValidatorBuilder.datepickerFormat(DATEPICKER_FORMAT)
                                                             .required()))))
            .build();

        packageId = eslClient.createPackage( superDuperPackage );
        eslClient.sendPackage( packageId );
    }
}