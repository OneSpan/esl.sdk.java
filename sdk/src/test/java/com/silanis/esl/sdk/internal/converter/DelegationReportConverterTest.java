package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.DelegationEventReport;
import com.silanis.esl.sdk.DelegationReport;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 7/14/16.
 */
public class DelegationReportConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.DelegationReport sdkDelegationReport = null;
    private com.silanis.esl.api.model.DelegationReport apiDelegationReport = null;
    private DelegationReportConverter converter;

    public static final String SDK_DELEGATION_EVENT_REPORTS_KEY = "SDKDelegationEventReportsKey";
    public static final String API_DELEGATION_EVENT_REPORTS_KEY = "APIDelegationEventReportsKey";

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkDelegationReport = null;
        converter = new DelegationReportConverter(sdkDelegationReport);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPIDelegationReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiDelegationReport = null;
        converter = new DelegationReportConverter(apiDelegationReport);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKDelegationReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkDelegationReport = null;
        converter = new DelegationReportConverter(sdkDelegationReport);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKDelegationReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiDelegationReport = null;
        converter = new DelegationReportConverter(apiDelegationReport);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPIDelegationReport(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkDelegationReport = createTypicalSDKDelegationReport();
        sdkDelegationReport = new DelegationReportConverter(sdkDelegationReport).toSDKDelegationReport();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkDelegationReport, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkDelegationReport, is(sdkDelegationReport));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiDelegationReport = createTypicalAPIDelegationReport();
        apiDelegationReport = new DelegationReportConverter(apiDelegationReport).toAPIDelegationReport();

        assertThat("Converter returned a null api object for a non null api object", apiDelegationReport, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiDelegationReport, is(apiDelegationReport));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiDelegationReport = createTypicalAPIDelegationReport();
        sdkDelegationReport = new DelegationReportConverter(apiDelegationReport).toSDKDelegationReport();

        assertThat("Converter returned a null sdk object for a non null api object", sdkDelegationReport, notNullValue());
        assertThat("DelegationEventReports was not correctly set", sdkDelegationReport.getDelegationEventReports(), notNullValue());
        assertThat("DelegationEventReports was not correctly set", sdkDelegationReport.getDelegationEventReports().get(API_DELEGATION_EVENT_REPORTS_KEY), notNullValue());

        DelegationEventReport sdkDelegationEventReport = sdkDelegationReport.getDelegationEventReports().get(API_DELEGATION_EVENT_REPORTS_KEY).get(0);
        com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport =
                ((List<com.silanis.esl.api.model.DelegationEventReport>) apiDelegationReport.getDelegationEvents().get(API_DELEGATION_EVENT_REPORTS_KEY)).get(0);

        assertThat("EventDate was not correctly set", sdkDelegationEventReport.getEventDate(), is(apiDelegationEventReport.getEventDate()));
        assertThat("EventDescription was not correctly set", sdkDelegationEventReport.getEventDescription(), is(apiDelegationEventReport.getEventDescription()));
        assertThat("EventType was not correctly set", sdkDelegationEventReport.getEventType(), is(apiDelegationEventReport.getEventType()));
        assertThat("EventUser was not correctly set", sdkDelegationEventReport.getEventUser(), is(apiDelegationEventReport.getEventUser()));

        assertThat("From date was not correctly set", sdkDelegationReport.getFrom(), is(apiDelegationReport.getFrom()));
        assertThat("To date was not correctly set", sdkDelegationReport.getTo(), is(apiDelegationReport.getTo()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkDelegationReport = createTypicalSDKDelegationReport();
        apiDelegationReport = new DelegationReportConverter(sdkDelegationReport).toAPIDelegationReport();

        assertThat("Converter returned a null sdk object for a non null api object", apiDelegationReport, notNullValue());
        assertThat("DelegationEventReports was not correctly set", apiDelegationReport.getDelegationEvents(), notNullValue());
        assertThat("DelegationEventReports was not correctly set", apiDelegationReport.getDelegationEvents().get(SDK_DELEGATION_EVENT_REPORTS_KEY), notNullValue());

        com.silanis.esl.api.model.DelegationEventReport apiDelegationEventReport =
                ((List<com.silanis.esl.api.model.DelegationEventReport>) apiDelegationReport.getDelegationEvents().get(SDK_DELEGATION_EVENT_REPORTS_KEY)).get(0);
        DelegationEventReport sdkDelegationEventReport = sdkDelegationReport.getDelegationEventReports().get(SDK_DELEGATION_EVENT_REPORTS_KEY).get(0);

        assertThat("EventDate was not correctly set", apiDelegationEventReport.getEventDate(), is(sdkDelegationEventReport.getEventDate()));
        assertThat("EventDescription was not correctly set", apiDelegationEventReport.getEventDescription(), is(sdkDelegationEventReport.getEventDescription()));
        assertThat("EventType was not correctly set", apiDelegationEventReport.getEventType(), is(sdkDelegationEventReport.getEventType()));
        assertThat("EventUser was not correctly set", apiDelegationEventReport.getEventUser(), is(sdkDelegationEventReport.getEventUser()));

        assertThat("From date was not correctly set", apiDelegationReport.getFrom(), is(sdkDelegationReport.getFrom()));
        assertThat("To date was not correctly set", apiDelegationReport.getTo(), is(sdkDelegationReport.getTo()));
    }

    private com.silanis.esl.sdk.DelegationReport createTypicalSDKDelegationReport() {
        DelegationReport sdkDelegationReport = new DelegationReport();
        sdkDelegationReport.setDelegationEventReports(new HashMap<String, List<DelegationEventReport>>() {{
            put(SDK_DELEGATION_EVENT_REPORTS_KEY, Collections.singletonList(createTypicalSDKDelegationEventReport()));
        }});
        sdkDelegationReport.setFrom(new DateTime().minusDays(15).toDate());
        sdkDelegationReport.setTo(new DateTime().minusDays(3).toDate());
        return sdkDelegationReport;
    }

    private com.silanis.esl.api.model.DelegationReport createTypicalAPIDelegationReport() {
        com.silanis.esl.api.model.DelegationReport apiDelegationReport = new com.silanis.esl.api.model.DelegationReport();
        apiDelegationReport.setDelegationEvents(new HashMap<String, Collection<com.silanis.esl.api.model.DelegationEventReport>>() {{
            put(API_DELEGATION_EVENT_REPORTS_KEY, Collections.singletonList(createTypicalAPIDelegationEventReport()));
        }});
        apiDelegationReport.setFrom(new DateTime().minusDays(12).toDate());
        apiDelegationReport.setTo(new DateTime().minusDays(5).toDate());

        return apiDelegationReport;
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
