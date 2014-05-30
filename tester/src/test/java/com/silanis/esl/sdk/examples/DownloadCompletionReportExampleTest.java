package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.CompletionReport;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

/**
 * Created by lena on 2014-05-27.
 */
public class DownloadCompletionReportExampleTest {

    @Test
    public void verifyResult() {
        DownloadCompletionReportExample example = new DownloadCompletionReportExample(Props.get(), "lhsieh");
        example.run();

        CompletionReport completionReport = example.getSdkCompetionReport();

        assertThat("There should be only 1 sender.", completionReport.getSenders().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of package completion reports should be greater than 1.", completionReport.getSenders().get(0).getPackages().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of document completion report should be greater than 1.", completionReport.getSenders().get(0).getPackages().get(0).getDocuments().size(), is(greaterThanOrEqualTo(1)));
        assertThat("Number of signer completion report should be greater than 1.", completionReport.getSenders().get(0).getPackages().get(0).getSigners().size(), is(greaterThanOrEqualTo(1)));
    }
}
