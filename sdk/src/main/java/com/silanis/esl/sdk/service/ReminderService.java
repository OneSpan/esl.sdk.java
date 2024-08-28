package com.silanis.esl.sdk.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.silanis.esl.api.model.PackageReminderSchedule;
import com.silanis.esl.api.util.JacksonUtil;
import com.silanis.esl.sdk.EslException;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.ReminderSchedule;
import com.silanis.esl.sdk.internal.*;
import com.silanis.esl.sdk.internal.converter.ReminderScheduleConverter;

public class ReminderService extends EslComponent {

    public ReminderService( RestClient client, String baseUrl ) {
        super( client, baseUrl);
    }

    public ReminderSchedule getReminderScheduleForPackage( PackageId packageId ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", packageId.getId() ).build();
        try {
            String stringResponse = getClient().get( path );
            if (stringResponse != null) {
                PackageReminderSchedule apiReminderSchedule = JacksonUtil.deserialize( stringResponse, new TypeReference<PackageReminderSchedule>() {} );
                ReminderSchedule sdkReminder = new ReminderScheduleConverter( apiReminderSchedule ).toSDKReminderSchedule();
                return sdkReminder;
            }
            else {
                return null;
            }
        } catch ( RequestException e ) {
            throw new EslServerException( "Failed to retrieve reminder.", e );
        } catch ( Exception e ) {
            throw new EslException( "Failed to retrieve reminder.", e );
        }
    }

    /**
     * @deprecated  This method was replaced by {@link #createReminderScheduleForPackage(ReminderSchedule)}
     */
    @Deprecated
    public ReminderSchedule setReminderScheduleForPackage( ReminderSchedule reminderSchedule ) {
        return createReminderScheduleForPackage(reminderSchedule);
    }

    public ReminderSchedule createReminderScheduleForPackage( ReminderSchedule reminderSchedule ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", reminderSchedule.getPackageId().getId() ).build();
        PackageReminderSchedule apiReminderSchedule = new ReminderScheduleConverter( reminderSchedule ).toAPIPackageReminderSchedule();
        try {
            String stringResponse = getClient().post( path, Serialization.toJson( apiReminderSchedule ) );
            PackageReminderSchedule apiResponse = Serialization.fromJson( stringResponse, PackageReminderSchedule.class );
            return new ReminderScheduleConverter( apiResponse ).toSDKReminderSchedule();
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to create a new reminder.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to create a new reminder.", e );
        }
    }

    public ReminderSchedule updateReminderScheduleForPackage( ReminderSchedule reminderSchedule ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", reminderSchedule.getPackageId().getId() ).build();
        PackageReminderSchedule apiReminderSchedule = new ReminderScheduleConverter( reminderSchedule ).toAPIPackageReminderSchedule();
        try {
            String stringResponse = getClient().put( path, Serialization.toJson( apiReminderSchedule ) );
            PackageReminderSchedule apiResponse = Serialization.fromJson( stringResponse, PackageReminderSchedule.class );
            return new ReminderScheduleConverter( apiResponse ).toSDKReminderSchedule();
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to update the reminder.", e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to update the reminder.", e );
        }
    }

    public void clearReminderScheduleForPackage( PackageId packageId ) {
        String path = new UrlTemplate(getBaseUrl()).urlFor( UrlTemplate.REMINDERS_PATH ).replace( "{packageId}", packageId.getId() ).build();
        try {
            getClient().delete( path );
        } catch ( RequestException e ) {
            throw new EslServerException( "Unable to clear reminder schedule for package with ID: " + packageId.getId(), e );
        } catch ( Exception e ) {
            throw new EslException( "Unable to clear reminder schedule for package with ID: " + packageId.getId(), e );
        }
    }
}
