package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.DelegationEventReport;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

/**
 * Created by schoi on 7/14/16.
 */
public class DelegationEventReportConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DelegationEventReport sdkDelegationEventReport = null;
    private com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport = null;
    private DelegationEventReportConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkDelegationEventReport = null;
        converter = new DelegationEventReportConverter(sdkDelegationEventReport);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIDelegationEventReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiDelegationEventReport = null;
        converter = new DelegationEventReportConverter(apiDelegationEventReport);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKDelegationEventReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkDelegationEventReport = null;
        converter = new DelegationEventReportConverter(sdkDelegationEventReport);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKDelegationEventReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiDelegationEventReport = null;
        converter = new DelegationEventReportConverter(apiDelegationEventReport);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIDelegationEventReport(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkDelegationEventReport = createTypicalSDKDelegationEventReport();
        sdkDelegationEventReport = new DelegationEventReportConverter(sdkDelegationEventReport).toSDKDelegationEventReport();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkDelegationEventReport, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkDelegationEventReport, is(sdkDelegationEventReport));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiDelegationEventReport = createTypicalAPIDelegationEventReport();
        apiDelegationEventReport = new DelegationEventReportConverter(apiDelegationEventReport).toAPIDelegationEventReport();

        assertThat("Converter returned a null api object for a non null api object", apiDelegationEventReport, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiDelegationEventReport, is(apiDelegationEventReport));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiDelegationEventReport = createTypicalAPIDelegationEventReport();
        sdkDelegationEventReport = new DelegationEventReportConverter(apiDelegationEventReport).toSDKDelegationEventReport();

        assertThat("Converter returned a null sdk object for a non null api object", sdkDelegationEventReport, notNullValue());
        assertThat("EventDate was not correctly set", sdkDelegationEventReport.getEventDate(), is(apiDelegationEventReport.getEventDate()));
        assertThat("EventDescription was not correctly set", sdkDelegationEventReport.getEventDescription(), is(apiDelegationEventReport.getEventDescription()));
        assertThat("EventType was not correctly set", sdkDelegationEventReport.getEventType(), is(apiDelegationEventReport.getEventType()));
        assertThat("EventUser was not correctly set", sdkDelegationEventReport.getEventUser(), is(apiDelegationEventReport.getEventUser()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkDelegationEventReport = createTypicalSDKDelegationEventReport();
        apiDelegationEventReport = new DelegationEventReportConverter(sdkDelegationEventReport).toAPIDelegationEventReport();

        assertThat("Converter returned a null sdk object for a non null api object", apiDelegationEventReport, notNullValue());
        assertThat("EventDate was not correctly set", apiDelegationEventReport.getEventDate(), is(sdkDelegationEventReport.getEventDate()));
        assertThat("EventDescription was not correctly set", apiDelegationEventReport.getEventDescription(), is(sdkDelegationEventReport.getEventDescription()));
        assertThat("EventType was not correctly set", apiDelegationEventReport.getEventType(), is(sdkDelegationEventReport.getEventType()));
        assertThat("EventUser was not correctly set", apiDelegationEventReport.getEventUser(), is(sdkDelegationEventReport.getEventUser()));
    }

    private com.silanis.esl.sdk.DelegationEventReport createTypicalSDKDelegationEventReport() {
        DelegationEventReport sdkDelegationEventReport = new DelegationEventReport();
        sdkDelegationEventReport.setEventDate(new DateTime().minusDays(5).toDate());
        sdkDelegationEventReport.setEventDescription("sdkEventDescription");
        sdkDelegationEventReport.setEventType("sdkEventType");
        sdkDelegationEventReport.setEventUser("sdkEventUser");
        return sdkDelegationEventReport;
    }

    private com.silanis.esl.api.model.DelegationEventReport createTypicalAPIDelegationEventReport() {
        com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport = new com.silanis.esl.api.model.DelegationEventReport();
        apiDelegationEventReport.setEventDate(new DateTime().minusDays(5).toDate());
        apiDelegationEventReport.setEventDescription("apiEventDescription");
        apiDelegationEventReport.setEventType("apiEventType");
        apiDelegationEventReport.setEventUser("apiEventUser");

        return apiDelegationEventReport;
    }
}
