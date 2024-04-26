package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.ReminderSchedule;
import com.silanis.esl.sdk.builder.ReminderScheduleBuilder;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class ReminderExample extends SDKSample {

    public ReminderSchedule reminderScheduleToCreate, reminderScheduleToUpdate;
    public ReminderSchedule createdReminderSchedule, updatedReminderSchedule, removedReminderSchedule;

    public static void main( String... args ) {
        new ReminderExample().run();
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed(getPackageName())
                .withSigner( newSignerWithEmail( email1 )
                                     .withFirstName( "Patty" )
                                     .withLastName( "Galant" ) )
                .withDocument( newDocumentWithName( "First Document" )
                                       .fromStream( documentInputStream1, DocumentType.PDF )
                                       .withSignature( signatureFor( email1 )
                                                               .onPage( 0 )
                                                               .atPosition( 100, 100 ) ) )
                .build();

        packageId = eslClient.createPackage( superDuperPackage );

        reminderScheduleToCreate = ReminderScheduleBuilder.forPackageWithId( packageId )
                                                          .withDaysUntilFirstReminder( 2 )
                                                          .withDaysBetweenReminders( 1 )
                                                          .withNumberOfRepetitions( 5 )
                                                          .build();

        eslClient.getReminderService().createReminderScheduleForPackage( reminderScheduleToCreate );
        eslClient.sendPackage( packageId );
        createdReminderSchedule = eslClient.getReminderService().getReminderScheduleForPackage(packageId);

        reminderScheduleToUpdate = ReminderScheduleBuilder.forPackageWithId( packageId )
                                                          .withDaysUntilFirstReminder( 3 )
                                                          .withDaysBetweenReminders( 2 )
                                                          .withNumberOfRepetitions( 5 )
                                                          .build();

        eslClient.getReminderService().updateReminderScheduleForPackage( reminderScheduleToUpdate );
        updatedReminderSchedule = eslClient.getReminderService().getReminderScheduleForPackage(packageId);

        eslClient.getReminderService().clearReminderScheduleForPackage( packageId );
        removedReminderSchedule = eslClient.getReminderService().getReminderScheduleForPackage(packageId);
    }
}
