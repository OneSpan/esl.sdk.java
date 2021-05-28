package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.AccountMember;
import com.silanis.esl.sdk.Sender;
import com.silanis.esl.sdk.SenderImageSignature;
import com.silanis.esl.sdk.builder.AccountMemberBuilder;
import org.apache.commons.codec.binary.Base64;

import java.io.IOException;
import java.io.InputStream;

public class SenderImageSignatureExample extends SDKSample  {

    public byte[] inputFileContentEncoded;
    public SenderImageSignature resultAfterUpdate;
    public SenderImageSignature resultAfterDelete;
    public static final String FILE_NAME = "exampleFile.jpg";

    public static void main( String... args ) {
        new SenderImageSignatureExample().run();
    }

    public SenderImageSignatureExample() {
        this.email1 = getRandomEmail();
    }

    @Override
    protected void execute() {
        AccountMember accountMember1 = AccountMemberBuilder.newAccountMember(email1)
                .withFirstName( "firstName1" )
                .withLastName( "lastName1" )
                .withCompany( "company1" )
                .withTitle( "title1" )
                .withLanguage( "language1" )
                .withPhoneNumber( "phoneNumber1" )
                .withTimezoneId( "GMT" )
                .build();

        Sender createdSender1 = eslClient.getAccountService().inviteUser(accountMember1);

        InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
        try {
            byte[] fileContent = new byte[fileInputStream.available()];
            fileInputStream.read(fileContent);
            inputFileContentEncoded = Base64.encodeBase64(fileContent);
            eslClient.getAccountService().updateSenderImageSignature(FILE_NAME, fileContent, createdSender1.getId());
            resultAfterUpdate = eslClient.getAccountService().getSenderImageSignature(createdSender1.getId());
            eslClient.getAccountService().deleteSenderImageSignature(createdSender1.getId());
            resultAfterDelete = eslClient.getAccountService().getSenderImageSignature(createdSender1.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
