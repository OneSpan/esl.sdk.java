package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.PackageReminder;
import com.silanis.esl.sdk.Reminder;

public class ReminderConverter {
    final private PackageReminder apiReminder;
    final private Reminder sdkReminder;

    public ReminderConverter( PackageReminder apiReminder ) {
        this.apiReminder = apiReminder;
        this.sdkReminder = null;
    }

    public ReminderConverter( Reminder sdkReminder ) {
        this.apiReminder = null;
        this.sdkReminder = sdkReminder;
    }

    public PackageReminder toAPIPackageReminder() {
        if ( apiReminder != null ) {
            return apiReminder;
        } else {
            PackageReminder result = new PackageReminder();
            result.setDate( sdkReminder.getDate() );
            result.setSentDate( sdkReminder.getSentDate() );
            return result;

        }
    }

    public Reminder toSDKReminder() {
        if ( sdkReminder != null ) {
            return sdkReminder;
        } else {
            Reminder result = new Reminder( apiReminder.getDate(), apiReminder.getSentDate() );
            return result;
        }
    }
}
