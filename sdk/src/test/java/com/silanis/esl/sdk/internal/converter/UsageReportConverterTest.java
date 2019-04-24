package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.UsageReportCategory;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by lena on 2014-07-25.
 */
public class UsageReportConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.UsageReport sdkUsageReport1 = null;
    private com.silanis.esl.api.model.UsageReport apiUsageReport1 = null;
    private UsageReportConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {

    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiUsageReport1 = null;
        converter = new UsageReportConverter(apiUsageReport1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKUsageReport(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {

    }

    @Override
    @Test
    public void convertNullAPIToAPI() {

    }

    @Override
    @Test
    public void convertSDKToSDK() {

    }

    @Override
    @Test
    public void convertAPIToAPI() {

    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiUsageReport1 = createTypicalAPIUsageReport();
        sdkUsageReport1 = new UsageReportConverter(apiUsageReport1).toSDKUsageReport();

        assertThat("Usage report has the wrong from date.", sdkUsageReport1.getFrom(), is(apiUsageReport1.getFrom()));
        assertThat("Usage report has the wrong to date.", sdkUsageReport1.getTo(), is(apiUsageReport1.getTo()));

        com.silanis.esl.api.model.Sender apiSender = apiUsageReport1.getSenders().get(0).getSender();
        com.silanis.esl.sdk.Sender sdkSender = sdkUsageReport1.getSenderUsageReports().get(0).getSender();
        assertThat("Sender first name was not set correctly.", sdkSender.getFirstName(), is(apiSender.getFirstName()));
        assertThat("Sender last name was not set correctly.", sdkSender.getLastName(), is(apiSender.getLastName()));
        assertThat("Sender email name was not set correctly.", sdkSender.getEmail(), is(apiSender.getEmail()));

        Map<String, Object> apiPackagesMap = apiUsageReport1.getSenders().get(0).getPackages();
        EnumMap<UsageReportCategory, Integer> sdkPackagesMap = sdkUsageReport1.getSenderUsageReports().get(0).getCountByUsageReportCategory();
        assertThat("Package map has wrong number of active packages.", sdkPackagesMap.get(UsageReportCategory.ACTIVE), is(apiPackagesMap.get("active")));
        assertThat("Package map has wrong number of draft packages.", sdkPackagesMap.get(UsageReportCategory.DRAFT), is(apiPackagesMap.get("draft")));
        assertThat("Package map has wrong number of declined packages.", sdkPackagesMap.get(UsageReportCategory.DECLINED), is(apiPackagesMap.get("declined")));
    }

    @Override
    @Test
    public void convertSDKToAPI() {

    }

    // Create a API Usage Report object.
    private com.silanis.esl.api.model.UsageReport createTypicalAPIUsageReport() {
        com.silanis.esl.api.model.UsageReport usageReport = new com.silanis.esl.api.model.UsageReport();
        usageReport.setFrom(new Date(1234));
        usageReport.setTo(new Date(5678));

        com.silanis.esl.api.model.Sender sender = new com.silanis.esl.api.model.Sender();
        sender.setEmail("sender@email.com");
        sender.setFirstName("SignerFirstName");
        sender.setLastName("SignerLastName");

        Map<String, Object> packages = new HashMap<String, Object>();
        packages.put("active", 7);
        packages.put("draft", 3);
        packages.put("declined", 1);

        com.silanis.esl.api.model.SenderUsageReport senderUsageReport = new com.silanis.esl.api.model.SenderUsageReport();
        senderUsageReport.setSender(sender);
        senderUsageReport.setPackages(packages);

        List<com.silanis.esl.api.model.SenderUsageReport> senderUsageReports = new ArrayList<com.silanis.esl.api.model.SenderUsageReport>();
        senderUsageReports.add(senderUsageReport);
        usageReport.setSenders(senderUsageReports);

        return usageReport;
    }
}
