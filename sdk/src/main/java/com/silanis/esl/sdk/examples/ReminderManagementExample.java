package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.ReminderSchedule;
import com.silanis.esl.sdk.builder.ReminderScheduleBuilder;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;

public class ReminderManagementExample extends SDKSample {
    private String email1;
    private InputStream documentInputStream1;

    public ReminderSchedule reminderScheduleToCreate, reminderScheduleToUpdate;
    public ReminderSchedule createdReminderSchedule, updatedReminderSchedule, removedReminderSchedule;

    public static void main( String... args ) {
        new ReminderManagementExample( Props.get() ).run();
    }

    public ReminderManagementExample( Properties props ) {
        this( props.getProperty( "api.key" ),
                props.getProperty( "api.url" ),
                props.getProperty( "1.email" ) );
    }

    public ReminderManagementExample( String apiKey, String apiUrl, String email1 ) {
        super( apiKey, apiUrl );
        this.email1 = email1;
        documentInputStream1 = this.getClass().getClassLoader().getResourceAsStream( "document.pdf" );
    }

    public void execute() {
        DocumentPackage superDuperPackage = newPackageNamed( "ReminderManagementExample Package " + new SimpleDateFormat( "HH:mm:ss" ).format( new Date() ) )
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
                                                          .withNumberOfRepetitions( 10 )
                                                          .build();

        eslClient.getReminderService().updateReminderScheduleForPackage( reminderScheduleToUpdate );
        updatedReminderSchedule = eslClient.getReminderService().getReminderScheduleForPackage(packageId);

        eslClient.getReminderService().clearReminderScheduleForPackage( packageId );
        removedReminderSchedule = eslClient.getReminderService().getReminderScheduleForPackage(packageId);
    }
}
