package com.silanis.esl.sdk.examples;

import com.silanis.awsng.web.rest.model.*;
import com.silanis.awsng.web.rest.model.Package;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.service.PackageService;

import java.io.File;
import java.io.IOException;

public class CreatePackageWithSigningDate {

    public static final String API_KEY = "YTUwOGQ5ZDktMDZmMi00MjM5LTkwNDQtYmZiZDI2MTdmNmQxOkJzYnAyeXNJQURnSA==";
    public static final String API_URL = "http://localhost:8080";

    public static void main(String[] args) throws EslException, IOException {

        // Create a E-SignLive client with api authentication token and staging/production environment
        EslClient eslClient = new EslClient(API_KEY, API_URL);

        Role role = new Role()
                .setName("First Signer")
                .addSigner(new Signer()
                        .setFirstName("John")
                        .setLastName("Smith")
                        .setEmail("etienne_hardy@silanis.com"))
                .setId("Role1");

        com.silanis.awsng.web.rest.model.Package samplePackage = new Package()
                .setName("Sample Insurance Policy")
                .addRole(role)
                .setAutocomplete(true);


        // Create a new package
        PackageService packageService = eslClient.getPackageService();
        PackageId packageId = packageService.createPackage(samplePackage);

        File file = new File("/Users/ehardy/Documents/test-docs/one_page_document_standard_size.pdf");
        byte[] fileContent = null; //FileHelper.readFile(file);
        Field firstSignatureField = new Field()
                .setType(FieldType.SIGNATURE)
                .setSubtype(FieldSubtype.FULLNAME)
                .setLeft(500.0)
                .setTop(100.0)
                .setHeight(50.0)
                .setWidth(200.0)
                .setName("Sign Here");

        Field signatureDateField = new Field()
                .setType(FieldType.INPUT)
                .setSubtype(FieldSubtype.LABEL)
                .setBinding("{approval.signed}")
                .setLeft(500.0)
                .setTop(200.0)
                .setHeight(50.0)
                .setWidth(200.0)
                .setName("Date");

        Field stateField = new Field()
                .setType(FieldType.INPUT)
                .setSubtype(FieldSubtype.TEXTFIELD)
                .setLeft(500.0)
                .setTop(300.0)
                .setHeight(50.0)
                .setWidth(200.0)
                .setName("Date");

        Approval firstApproval = new Approval()
                .addField(firstSignatureField)
                .addField(signatureDateField)
                .addField(stateField)
                .setRole("Role1");

        Document document = new Document()
                .addApproval(firstApproval)
                .setName("My Sample Document");

        // Upload documents to the package
        packageService.uploadDocument(packageId, file.getName(), fileContent, document);

        // Send package out for signing
        packageService.sendPackage(packageId);
    }
}