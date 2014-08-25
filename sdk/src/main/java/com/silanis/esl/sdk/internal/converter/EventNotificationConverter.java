package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.CallbackEvent;
import com.silanis.esl.sdk.NotificationEvent;
import com.silanis.esl.sdk.builder.BuilderException;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConverter {

    private com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent = null;
    private com.silanis.esl.api.model.CallbackEvent apiCallbackEvent = null;

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
    public EventNotificationConverter(com.silanis.esl.api.model.CallbackEvent apiCallbackEvent) {
        this.apiCallbackEvent = apiCallbackEvent;
    }

    /**
     * Convert from SDK notification event to API callback event.
     *
     * @return an API callback event.
     */
    public com.silanis.esl.api.model.CallbackEvent toAPICallbackEvent() {
        if (sdkNotificationEvent == null) {
            return apiCallbackEvent;
        }

        switch (sdkNotificationEvent) {
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
                throw new BuilderException("Unrecognized event notification type.");
        }
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

        switch (apiCallbackEvent) {
            case PACKAGE_ACTIVATE:
                return NotificationEvent.PACKAGE_ACTIVATE;
            case PACKAGE_COMPLETE:
                return NotificationEvent.PACKAGE_COMPLETE;
            case PACKAGE_EXPIRE:
                return NotificationEvent.PACKAGE_EXPIRE;
            case PACKAGE_OPT_OUT:
                return NotificationEvent.PACKAGE_OPT_OUT;
            case PACKAGE_DECLINE:
                return NotificationEvent.PACKAGE_DECLINE;
            case SIGNER_COMPLETE:
                return NotificationEvent.SIGNER_COMPLETE;
            case DOCUMENT_SIGNED:
                return NotificationEvent.DOCUMENT_SIGNED;
            case ROLE_REASSIGN:
                return NotificationEvent.ROLE_REASSIGN;
            case PACKAGE_CREATE:
                return NotificationEvent.PACKAGE_CREATE;
            case PACKAGE_DEACTIVATE:
                return NotificationEvent.PACKAGE_DEACTIVATE;
            case PACKAGE_READY_FOR_COMPLETE:
                return NotificationEvent.PACKAGE_READY_FOR_COMPLETION;
            case PACKAGE_TRASH:
                return NotificationEvent.PACKAGE_TRASH;
            case PACKAGE_RESTORE:
                return NotificationEvent.PACKAGE_RESTORE;
            case PACKAGE_DELETE:
                return NotificationEvent.PACKAGE_DELETE;
            default:
                throw new BuilderException("Unrecognized event notification type.");
        }
    }
}
