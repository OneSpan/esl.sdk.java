package com.silanis.esl.sdk.examples;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

import com.silanis.esl.api.model.Delivery;
import com.silanis.esl.api.model.Group;
import com.silanis.esl.api.model.Role;
import com.silanis.esl.api.model.Signer;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.SupportConfiguration;
import java.util.Date;
import java.util.List;


public class AdhocGroupExample extends SDKSample {

    public static void main( String... args ) {
        new AdhocGroupExample().run();
    }

    public void execute() {
        final DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
            .describedAs("This is a package created using OneSpan Sign SDK")
            .withSigner(newSignerWithEmail(email1)
                .withFirstName("John1")
                .withLastName("Smith1"))
            .withDocument(newDocumentWithName("First Document")
                .fromStream(documentInputStream1, DocumentType.PDF)
                .withSignature(signatureFor(email1)
                                       .onPage(0)
                                       .atPosition(100, 100)))
            .build();

        //this.packageId = this.eslClient.createPackage( superDuperPackage );
        final Role adhocGroup = new Role();
        adhocGroup.setId("68c42bed-d7f0-406d-a016-a8a7525aeb08");
        final Signer e = new Signer();
        e.setFirstName("Adhoc Group TEST 7");
        e.setGroup(new Group());
        e.getGroup().setName("Adhoc Group TEST 7");
        e.getGroup().setCreated(new Date());
        e.getGroup().setUpdated(new Date());
        e.setSignerType("AD_HOC_GROUP_SIGNER");
        e.setCreated(new Date());
        e.setUpdated(new Date());
        e.setDelivery(new Delivery());
        adhocGroup.getSigners().add(e);
        //this.eslClient.getAdhocGroupService().createAdhocGroupWithAdhoc("doZ-O-9w3VSdgLrlzBRm-bQdC_E=", adhocGroup);
        final Signer adhocGroupMember = new Signer();
        adhocGroupMember.setFirstName("test99 first name");
        adhocGroupMember.setLastName("test99 last name");
        adhocGroupMember.setEmail("test99@test.com");

       //this.eslClient.getAdhocGroupService().addAdhocGroupMember(this.packageId.getId(), adhocGroup.getId(),
       //     adhocGroupMember);
        //this.eslClient.sendPackage( packageId );

        final Signer adhocGroupMember1 = new Signer();
        adhocGroupMember1.setId("8452b056-9680-45e6-9b42-2203dfe7eb44");
        //this.eslClient.getAdhocGroupService().deleteAdhocGroupMember2("doZ-O-9w3VSdgLrlzBRm-bQdC_E=","68c42bed-d7f0-406d-a016-a8a7525aeb08", adhocGroupMember1);

        final List<Role> roles = this.eslClient.getPackageService()
            .getRoles(new PackageId("doZ-O-9w3VSdgLrlzBRm-bQdC_E="));
        System.out.println(roles);

        this.eslClient.getAdhocGroupService().deleteAdhocGroupWithAdhoc("doZ-O-9w3VSdgLrlzBRm-bQdC_E=","abc");
    }
}
