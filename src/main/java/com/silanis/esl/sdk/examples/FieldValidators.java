package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.FieldBuilder.textField;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.EMAIL_REGEX;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.alphabetic;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.alphanumeric;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.email;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.regex;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.numeric;
import static com.silanis.esl.sdk.builder.FieldValidatorBuilder.url;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class FieldValidators {
    public static final String API_KEY = "YjA0ODY5MDItZjM4NC00MTA2LTk0OTgtYWVhNmZkZGQ4YjJlOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "https://sandbox.e-signlive.com/api";
    private static final SimpleDateFormat format = new SimpleDateFormat( "HH:mm:ss" );

    public static void main( String... args ) {
        EslClient eslClient = new EslClient( API_KEY, API_URL );

        DocumentPackage superDuperPackage = newPackageNamed( "Policy " + format.format( new Date() ) )
                .withSigner(newSignerWithEmail("etienne_hardy@silanis.com")
                        .withFirstName("John")
                        .withLastName("Smith"))
                .withDocument(newDocumentWithName("First Document")
                        .fromFile("src/main/resources/document.pdf")
                        .withSignature(signatureFor("etienne_hardy@silanis.com")
                                .onPage(0)
                                .atPosition(500, 100)
                                .withField(textField()
                                        .atPosition(500, 200)
                                        .onPage(0)
                                        .withValidation(numeric().maxLength(10)))
                                .withField(textField()
                                        .atPosition(500, 300)
                                        .onPage(0)
                                        .withValidation(alphabetic()
                                                .minLength(3)
                                                .maxLength(10)
                                                .required()))
                                .withField(textField()
                                        .atPosition(500, 400)
                                        .onPage(0)
                                        .withValidation(alphanumeric().minLength(5)))
                                .withField(textField()
                                        .atPosition(500, 500)
                                        .onPage(0)
                                        .withValidation(url().withErrorMessage("This is not a URL")))
                                .withField(textField()
                                        .atPosition(500, 600)
                                        .onPage(0)
                                        .withValidation(email()))
                                .withField(textField()
                                        .atPosition(500, 700)
                                        .onPage(0)
                                        .withValidation(regex(EMAIL_REGEX).withErrorMessage("This is not a valid email")))
                        ))
                .build();

        PackageId packageId = eslClient.createPackage( superDuperPackage );

        eslClient.sendPackage( packageId );
    }
}
