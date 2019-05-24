package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageStatus;
import com.silanis.esl.sdk.Page;
import com.silanis.esl.sdk.PageRequest;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.internal.converter.PackageStatusConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class GetPackageFieldsListExample  extends SDKSample {

    public Page<Map<String, String>> packages;

    public static void main( String... args ) {
        new GetPackageFieldsListExample().run();
    }

    @Override
    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .describedAs( "This is a package created using the eSignLive SDK" )
                .expiresAt( now().plusMonths( 1 ).toDate() )
                .withEmailMessage( "This message should be delivered to all signers" )
                .withSigner( newSignerWithEmail( email1 )
                        .withFirstName( "John" )
                        .withLastName( "Smith" )
                        .withTitle( "Managing Director" )
                        .withCompany( "Acme Inc." ) )
                .withDocument( newDocumentWithName( "First Document" )
                        .fromStream( documentInputStream1, DocumentType.PDF )
                        .withSignature( signatureFor( email1 )
                                .onPage( 0 )
                                .atPosition( 100, 100 )
                                .withField( FieldBuilder.textField()
                                        .onPage( 0 )
                                        .atPosition( 400, 100 )
                                        .withSize( 200, 50 ) ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        List<String> fields = new ArrayList<>();
        fields.add("id");
        packages = eslClient.getPackageService().getPackagesFields( new PackageStatusConverter(PackageStatus.DRAFT).toAPIPackageStatus(), new PageRequest( 1 ), fields);
    }
}
