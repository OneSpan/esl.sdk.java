package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationEvent;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConverter {

    private NotificationEvent sdkNotificationEvent = null;
    private String apiCallbackEvent = null;

    /**
     * Construct with SDK notification event object involved in conversion.
     *
     * @param sdkNotificationEvent
     */
    public EventNotificationConverter(com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent) {
        this.sdkNotificationEvent = sdkNotificationEvent;
    }

    /**
     * Construct with API callback event object involved in conversion.
     *
     * @param apiCallbackEvent
     */
    public EventNotificationConverter(String apiCallbackEvent) {
        this.apiCallbackEvent = apiCallbackEvent;
    }

    /**
     * Convert from SDK notification event to API callback event.
     *
     * @return an API callback event.
     */
    public String toAPICallbackEvent() {
        if (sdkNotificationEvent == null) {
            return apiCallbackEvent;
        }

        if(sdkNotificationEvent.getValue().equals("PACKAGE_ACTIVATE"))
            return "PACKAGE_ACTIVATE";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_COMPLETE"))
            return "PACKAGE_COMPLETE";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_OPT_OUT"))
            return "PACKAGE_OPT_OUT";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_DECLINE"))
            return "PACKAGE_DECLINE";
        else if (sdkNotificationEvent.getValue().equals("SIGNER_COMPLETE"))
            return "SIGNER_COMPLETE";
        else if (sdkNotificationEvent.getValue().equals("DOCUMENT_SIGNED"))
            return "DOCUMENT_SIGNED";
        else if (sdkNotificationEvent.getValue().equals("ROLE_REASSIGN"))
            return "ROLE_REASSIGN";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_CREATE"))
            return "PACKAGE_CREATE";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_DEACTIVATE"))
            return "PACKAGE_DEACTIVATE";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_READY_FOR_COMPLETE"))
            return "PACKAGE_READY_FOR_COMPLETE";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_TRASH"))
            return "PACKAGE_TRASH";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_RESTORE"))
            return "PACKAGE_RESTORE";
        else if (sdkNotificationEvent.getValue().equals("PACKAGE_DELETE"))
            return "PACKAGE_DELETE";
        else if (sdkNotificationEvent.getValue().equals("UNRECOGNIZED"))
            return sdkNotificationEvent.getUnknownValue();
        else
            return "";
    }

    /**
     * Convert from API callback event to SDK notification event.
     *
     * @return an SDK notification event.
     */
    public com.silanis.esl.sdk.NotificationEvent toSDKNotificationEvent() {
        if (apiCallbackEvent == null) {
            return sdkNotificationEvent;
        }

        if (apiCallbackEvent.equals("PACKAGE_ACTIVATE"))
            return NotificationEvent.PACKAGE_ACTIVATE;
        else if (apiCallbackEvent.equals("PACKAGE_COMPLETE"))
            return NotificationEvent.PACKAGE_COMPLETE;
        else if (apiCallbackEvent.equals("PACKAGE_EXPIRE"))
            return NotificationEvent.PACKAGE_EXPIRE;
        else if (apiCallbackEvent.equals("PACKAGE_OPT_OUT"))
            return NotificationEvent.PACKAGE_OPT_OUT;
        else if (apiCallbackEvent.equals("PACKAGE_DECLINE"))
            return NotificationEvent.PACKAGE_DECLINE;
        else if (apiCallbackEvent.equals("SIGNER_COMPLETE"))
            return NotificationEvent.SIGNER_COMPLETE;
        else if (apiCallbackEvent.equals("DOCUMENT_SIGNED"))
            return NotificationEvent.DOCUMENT_SIGNED;
        else if (apiCallbackEvent.equals("ROLE_REASSIGN"))
            return NotificationEvent.ROLE_REASSIGN;
        else if (apiCallbackEvent.equals("PACKAGE_CREATE"))
            return NotificationEvent.PACKAGE_CREATE;
        else if (apiCallbackEvent.equals("PACKAGE_DEACTIVATE"))
            return NotificationEvent.PACKAGE_DEACTIVATE;
        else if (apiCallbackEvent.equals("PACKAGE_READY_FOR_COMPLETION"))
            return NotificationEvent.PACKAGE_READY_FOR_COMPLETION;
        else if (apiCallbackEvent.equals("PACKAGE_TRASH"))
            return NotificationEvent.PACKAGE_TRASH;
        else if (apiCallbackEvent.equals("PACKAGE_RESTORE"))
            return NotificationEvent.PACKAGE_RESTORE;
        else if (apiCallbackEvent.equals("PACKAGE_DELETE"))
            return NotificationEvent.PACKAGE_DELETE;
        else
            return NotificationEvent.UNRECOGNIZED(apiCallbackEvent);
    }
}
