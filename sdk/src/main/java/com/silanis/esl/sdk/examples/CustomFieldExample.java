package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.CustomFieldValueBuilder;
import com.silanis.esl.sdk.builder.FieldBuilder;

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import static com.silanis.esl.sdk.builder.CustomFieldBuilder.customFieldWithId;
import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static com.silanis.esl.sdk.builder.TranslationBuilder.newTranslation;

public class CustomFieldExample extends SDKSample {

    public final String email1;
    private InputStream documentInputStream1;

    public static final String DEFAULT_VALUE = "#12345";
    public static final String ENGLISH_LANGUAGE = "en";
    public static final String ENGLISH_NAME = "Player Number";
    public static final String ENGLISH_DESCRIPTION = "The number on your team jersey";
    public static final String FRENCH_LANGUAGE = "fr";
    public static final String FRENCH_NAME = "Numero du Joueur";
    public static final String FRENCH_DESCRIPTION = "Le numero dans le dos de votre chandail d'equipe";
    public static final String FIELD_VALUE = "99";

    public static final String DEFAULT_VALUE2 = "Red";
    public static final String ENGLISH_NAME2 = "Jersey Color";
    public static final String ENGLISH_DESCRIPTION2 = "The color of your team jersey";

    private String customFieldId1, customFieldId2;
    private CustomField retrieveCustomField;
    private List<CustomField> retrieveCustomFieldList1, retrieveCustomFieldList2;

    public static void main(String... args) {
        new CustomFieldExample(Props.get()).run();
    }

    public CustomFieldExample(Properties properties) {
        this(properties.getProperty("api.key"),
                properties.getProperty("api.url"),
                properties.getProperty("1.email"));
    }

    public CustomFieldExample(String apiKey, String apiUrl, String email1) {
        super(apiKey, apiUrl);
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream("document-with-fields.pdf");
    }

    public String getCustomFieldId1() {
        return customFieldId1;
    }

    public String getCustomFieldId2() {
        return customFieldId2;
    }

    public CustomField getRetrieveCustomField() {
        return retrieveCustomField;
    }

    public List<CustomField> getRetrieveCustomFieldList1() {
        return retrieveCustomFieldList1;
    }

    public List<CustomField> getRetrieveCustomFieldList2() {
        return retrieveCustomFieldList2;
    }

    @Override
    public void execute() {
        // First custom field
        customFieldId1 = UUID.randomUUID().toString().replaceAll("-", "");
        CustomField customField = eslClient.getCustomFieldService()
                .createCustomField(customFieldWithId(customFieldId1)
                                .withDefaultValue(DEFAULT_VALUE)
                                .withTranslation(newTranslation(ENGLISH_LANGUAGE)
                                        .withName(ENGLISH_NAME)
                                        .withDescription(ENGLISH_DESCRIPTION))
                                .withTranslation(newTranslation(FRENCH_LANGUAGE)
                                        .withName(FRENCH_NAME)
                                        .withDescription(FRENCH_DESCRIPTION))
                                .build()
                );

        CustomFieldValue customFieldValue = eslClient.getCustomFieldService()
                .submitCustomFieldValue(CustomFieldValueBuilder.customFieldValueWithId(customField.getId())
                                .withValue(FIELD_VALUE)
                                .build()
                );

        // Second custom field
        customFieldId2 = UUID.randomUUID().toString().replaceAll("-", "");
        CustomField customField2 = eslClient.getCustomFieldService()
                .createCustomField(customFieldWithId(customFieldId2)
                                .withDefaultValue(DEFAULT_VALUE2)
                                .withTranslation(newTranslation(ENGLISH_LANGUAGE)
                                        .withName(ENGLISH_NAME2)
                                        .withDescription(ENGLISH_DESCRIPTION2))
                                .build()
                );

        // Create and send package with two custom fields
        DocumentPackage superDuperPackage = newPackageNamed("Sample Insurance policy")
                .withSigner(newSignerWithEmail(email1)
                        .withFirstName("John")
                        .withLastName("Smith")
                        .withCustomId("signer1"))
                .withDocument(newDocumentWithName("First Document")
                                .fromStream(documentInputStream1, DocumentType.PDF)
                                .withSignature(signatureFor(email1)
                                        .onPage(0)
                                        .atPosition(100, 100)
                                        .withField(FieldBuilder.customField(customFieldValue.getId())
                                                .onPage(0)
                                                .atPosition(400, 200)))
                                .withSignature(signatureFor(email1)
                                        .onPage(0)
                                        .atPosition(100, 400)
                                        .withField(FieldBuilder.customField(customField2.getId())
                                                .onPage(0)
                                                .atPosition(400, 400)))
                )
                .build();

        packageId = eslClient.createPackage(superDuperPackage);
        eslClient.sendPackage(packageId);

        // Get the entire list of custom field from account
        retrieveCustomFieldList1 = eslClient.getCustomFieldService().getCustomFields(Direction.ASCENDING);

        // Get a list of custom fields from index [1, 2] sorted by its id (first two custom fields)
        retrieveCustomFieldList2 = eslClient.getCustomFieldService().getCustomFields(Direction.ASCENDING, 1, 2);

        // Get the first custom field from account
        retrieveCustomField = eslClient.getCustomFieldService().getCustomField(customFieldId1);

        // Delete the second custom field from account
        eslClient.getCustomFieldService().deleteCustomField(customFieldId2);
    }

}
