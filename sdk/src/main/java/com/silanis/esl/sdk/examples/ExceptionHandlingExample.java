package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.builder.FieldBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.internal.EslServerException;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.joda.time.DateMidnight.now;

public class ExceptionHandlingExample extends SDKSample {


    public static void main( String... args ) {
        new BasicPackageCreationExample(Props.get()).run();
    }

    public ExceptionHandlingExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ) );
    }

    public ExceptionHandlingExample( String apiKey, String apiUrl ) {
        super( apiKey, apiUrl );
    }

    public void execute() {

        SignerBuilder signerBuilder = SignerBuilder.newSignerWithEmail( "john.smith@email.com" )
                .withFirstName("John")
                .withLastName("Smith")
                .withTitle("Managing Director")
                .withCompany("Acme Inc.");

        Signer signer = null;
        try {
            signer = signerBuilder.build();
        } catch( EslException eslException ) {
            System.out.println( eslException.getLocalizedMessage() );
            return;
        }

        try {
            eslClient.getPackageService().addSigner( new PackageId( "myPackageId" ), signer);
        } catch( EslServerException eslServerException ) {
            // The request was refused by the server for some reason...
            System.out.println(eslServerException.getLocalizedMessage());
            System.out.println(eslServerException.getServerError().getCode());
            System.out.println(eslServerException.getServerError().getMessage());
            System.out.println(eslServerException.getServerError().getTechnical());
            return;
        } catch( EslException eslException ) {
            System.out.println( eslException.getLocalizedMessage() );
            return;
        }
    }

}
