package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;
import com.silanis.esl.sdk.VirtualRoom;
import com.silanis.esl.sdk.builder.PackageBuilder;
import com.silanis.esl.sdk.builder.SignerBuilder;

import java.util.Calendar;
import java.util.Date;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.VirtualRoomBuilder.newVirtualRoom;

public class VirtualRoomExample extends SDKSample  {

    public VirtualRoom packageVirtualRoomRoomAfterUpdate;

    public String hostUid;
    public Date startDateTime;

    public static void main(String... args) {
        new VirtualRoomExample().run();
    }

    @Override
    protected void execute() {

        Signer signer = SignerBuilder.newSignerWithEmail(email1)
                .withFirstName("Patty")
                .withLastName("Galant")
                .build();

        hostUid = signer.getId();
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 7);
        startDateTime = cal.getTime();

        VirtualRoom virtualRoom = newVirtualRoom()
                .withHostUid(hostUid)
                .withVideo(true)
                .withVideoRecording(true)
                .withStartDateTime(startDateTime)
                .build();

        DocumentPackage superDuperPackage = PackageBuilder.newPackageNamed(getPackageName())
                .describedAs("Description")
                .withSigner(SignerBuilder.newSignerWithEmail(email1)
                        .withFirstName("Patty")
                        .withLastName("Galant"))
                .withDocument(newDocumentWithName("Document")
                        .withId("DocumentId")
                        .fromStream(documentInputStream1, DocumentType.PDF))
                .build();

        PackageId packageId = eslClient.createPackageOneStep(superDuperPackage);
        retrievedPackage = eslClient.getPackage(packageId);

        eslClient.getVirtualRoomService().setVirtualRoom(packageId, virtualRoom);
        packageVirtualRoomRoomAfterUpdate = eslClient.getVirtualRoomService().getVirtualRoom(packageId);
    }
}
