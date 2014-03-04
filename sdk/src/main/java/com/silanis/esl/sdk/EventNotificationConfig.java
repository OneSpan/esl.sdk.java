package com.silanis.esl.sdk;

import com.silanis.esl.api.model.Callback;
import com.silanis.esl.api.model.CallbackEvent;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EventNotificationConfig {
    private String url;
    private Set<NotificationEvent> events;

    public EventNotificationConfig( String url ) {
        this.url = url;
        events = new HashSet<NotificationEvent>();
    }

    public Collection<NotificationEvent> getEvents() {
        return events;
    }

    public Callback toAPICallback() {
        Callback callback = new Callback();
        callback.setUrl( url );
        for ( NotificationEvent event : events ) {
            callback.addEvent( toAPICallbackEvent(event) );
        }
        return callback;
    }

    private static CallbackEvent toAPICallbackEvent( NotificationEvent event ) {
        switch ( event ) {
            case PACKAGE_ACTIVATE:
                return CallbackEvent.PACKAGE_ACTIVATE;
            case PACKAGE_COMPLETE:
                return CallbackEvent.PACKAGE_COMPLETE;
            case PACKAGE_EXPIRE:
                return CallbackEvent.PACKAGE_EXPIRE;
            case PACKAGE_OPT_OUT:
                return CallbackEvent.PACKAGE_OPT_OUT;
            case PACKAGE_DECLINE:
                return CallbackEvent.PACKAGE_DECLINE;
            case SIGNER_COMPLETE:
                return CallbackEvent.SIGNER_COMPLETE;
            case DOCUMENT_SIGNED:
                return CallbackEvent.DOCUMENT_SIGNED;
            case ROLE_REASSIGN:
                return CallbackEvent.ROLE_REASSIGN;
            case PACKAGE_CREATE:
                return CallbackEvent.PACKAGE_CREATE;
            case PACKAGE_DEACTIVATE:
                return CallbackEvent.PACKAGE_DEACTIVATE;
            case PACKAGE_READY_FOR_COMPLETION:
                return CallbackEvent.PACKAGE_READY_FOR_COMPLETE;
            case PACKAGE_TRASH:
                return CallbackEvent.PACKAGE_TRASH;
            case PACKAGE_RESTORE:
                return CallbackEvent.PACKAGE_RESTORE;
            case PACKAGE_DELETE:
                return CallbackEvent.PACKAGE_DELETE;
            default:
                return null;
        }
    }
}
