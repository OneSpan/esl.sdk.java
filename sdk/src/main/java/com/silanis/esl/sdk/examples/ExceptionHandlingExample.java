package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.builder.SignerBuilder;
import com.silanis.esl.sdk.internal.EslServerException;

public class ExceptionHandlingExample extends SDKSample {


    public static void main( String... args ) {
        new ExceptionHandlingExample().run();
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
