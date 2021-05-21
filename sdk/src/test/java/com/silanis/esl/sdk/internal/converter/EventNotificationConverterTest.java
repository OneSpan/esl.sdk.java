package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.NotificationEvent;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-06-17.
 */
public class EventNotificationConverterTest implements ConverterTest {

    private String apiCallbackEvent1 = null;
    private String apiCallbackEvent2 = null;
    private com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent1 = null;
    private com.silanis.esl.sdk.NotificationEvent sdkNotificationEvent2 = null;
    private EventNotificationConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkNotificationEvent1 = null;
        converter = new EventNotificationConverter(sdkNotificationEvent1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPICallbackEvent(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiCallbackEvent1 = null;
        converter = new EventNotificationConverter(apiCallbackEvent1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKNotificationEvent(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkNotificationEvent1 = null;
        converter = new EventNotificationConverter(sdkNotificationEvent1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKNotificationEvent(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiCallbackEvent1 = null;
        converter = new EventNotificationConverter(apiCallbackEvent1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPICallbackEvent(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkNotificationEvent1 = NotificationEvent.PACKAGE_DECLINE;
        sdkNotificationEvent2 = new EventNotificationConverter(sdkNotificationEvent1).toSDKNotificationEvent();

        assertThat("Converter returned a null sdk object for  a non null sdk object", sdkNotificationEvent2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkNotificationEvent2, is(sdkNotificationEvent1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiCallbackEvent1 = "PACKAGE_COMPLETE";
        apiCallbackEvent2 = new EventNotificationConverter(apiCallbackEvent1).toAPICallbackEvent();

        assertThat("Converter returned a null api object for a non null api object", apiCallbackEvent2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiCallbackEvent2, is(apiCallbackEvent1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {

        apiCallbackEvent1 = "PACKAGE_ACTIVATE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_ACTIVATE));

        apiCallbackEvent1 = "PACKAGE_COMPLETE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_COMPLETE));

        apiCallbackEvent1 = "PACKAGE_EXPIRE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_EXPIRE));

        apiCallbackEvent1 = "PACKAGE_OPT_OUT";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_OPT_OUT));

        apiCallbackEvent1 = "PACKAGE_DECLINE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_DECLINE));

        apiCallbackEvent1 = "SIGNER_COMPLETE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.SIGNER_COMPLETE));

        apiCallbackEvent1 = "DOCUMENT_SIGNED";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.DOCUMENT_SIGNED));

        apiCallbackEvent1 = "DOCUMENT_VIEWED";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.DOCUMENT_VIEWED));

        apiCallbackEvent1 = "ROLE_REASSIGN";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.ROLE_REASSIGN));

        apiCallbackEvent1 = "PACKAGE_CREATE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_CREATE));

        apiCallbackEvent1 = "PACKAGE_DEACTIVATE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_DEACTIVATE));

        apiCallbackEvent1 = "PACKAGE_READY_FOR_COMPLETE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_READY_FOR_COMPLETION));

        apiCallbackEvent1 = "PACKAGE_TRASH";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_TRASH));

        apiCallbackEvent1 = "PACKAGE_RESTORE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_RESTORE));

        apiCallbackEvent1 = "PACKAGE_DELETE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_DELETE));

        apiCallbackEvent1 = "KBA_FAILURE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.KBA_FAILURE));

        apiCallbackEvent1 = "EMAIL_BOUNCE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.EMAIL_BOUNCE));

        apiCallbackEvent1 = "PACKAGE_ATTACHMENT";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_ATTACHMENT));

        apiCallbackEvent1 = "SIGNER_LOCKED";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.SIGNER_LOCKED));

        apiCallbackEvent1 = "PACKAGE_ARCHIVE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.PACKAGE_ARCHIVE));

        apiCallbackEvent1 = "TEMPLATE_CREATE";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1, is(NotificationEvent.TEMPLATE_CREATE));

        apiCallbackEvent1 = "UNKNOWN";
        sdkNotificationEvent1 = new EventNotificationConverter(apiCallbackEvent1).toSDKNotificationEvent();
        assertThat("Callback event enum was not converted correctly", sdkNotificationEvent1.toString(), is(NotificationEvent.UNRECOGNIZED("UNKNOWN").toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_ACTIVATE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_ACTIVATE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_COMPLETE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_COMPLETE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_EXPIRE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_EXPIRE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_OPT_OUT;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_OPT_OUT"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_DECLINE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_DECLINE"));

        sdkNotificationEvent1 = NotificationEvent.SIGNER_COMPLETE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("SIGNER_COMPLETE"));

        sdkNotificationEvent1 = NotificationEvent.DOCUMENT_SIGNED;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("DOCUMENT_SIGNED"));

        sdkNotificationEvent1 = NotificationEvent.DOCUMENT_VIEWED;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("DOCUMENT_VIEWED"));

        sdkNotificationEvent1 = NotificationEvent.ROLE_REASSIGN;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("ROLE_REASSIGN"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_CREATE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_CREATE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_DEACTIVATE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_DEACTIVATE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_READY_FOR_COMPLETION;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_READY_FOR_COMPLETE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_TRASH;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_TRASH"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_RESTORE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_RESTORE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_DELETE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_DELETE"));

        sdkNotificationEvent1 = NotificationEvent.KBA_FAILURE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("KBA_FAILURE"));

        sdkNotificationEvent1 = NotificationEvent.EMAIL_BOUNCE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("EMAIL_BOUNCE"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_ATTACHMENT;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_ATTACHMENT"));

        sdkNotificationEvent1 = NotificationEvent.SIGNER_LOCKED;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("SIGNER_LOCKED"));

        sdkNotificationEvent1 = NotificationEvent.PACKAGE_ARCHIVE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("PACKAGE_ARCHIVE"));

        sdkNotificationEvent1 = NotificationEvent.TEMPLATE_CREATE;
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("TEMPLATE_CREATE"));

        sdkNotificationEvent1 = NotificationEvent.UNRECOGNIZED("UNKNOWN");
        apiCallbackEvent1 = new EventNotificationConverter(sdkNotificationEvent1).toAPICallbackEvent();
        assertThat("Notification event enum was not converted correctly", apiCallbackEvent1, is("UNKNOWN"));
    }
}
