package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.Page;
import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

import static com.silanis.esl.sdk.examples.GetCompletedPackagesWithinDateRangeExample.END_DATE;
import static com.silanis.esl.sdk.examples.GetCompletedPackagesWithinDateRangeExample.START_DATE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;

/**
 * Created by schoi on 1/16/15.
 */
public class GetCompletedPackagesWithinDateRangeExampleTest {
    @Test
    public void verifyResult() {
        GetCompletedPackagesWithinDateRangeExample example = new GetCompletedPackagesWithinDateRangeExample(Props.get());
        example.run();

        assertEqualsPackageUpdatedDate(example.draftPackages, START_DATE, END_DATE);
        assertEqualsPackageUpdatedDate(example.sentPackages, START_DATE, END_DATE);
        assertEqualsPackageUpdatedDate(example.declinedPackages, START_DATE, END_DATE);
        assertEqualsPackageUpdatedDate(example.archivedPackages, START_DATE, END_DATE);
        assertEqualsPackageUpdatedDate(example.completedPackages, START_DATE, END_DATE);
    }

    private void assertEqualsPackageUpdatedDate(Page<DocumentPackage> packages, Date startDate, Date endDate) {
        for(DocumentPackage draftPackage : packages) {
            assertThat(draftPackage.getUpdatedDate(), is(greaterThanOrEqualTo(new DateTime(startDate).withTimeAtStartOfDay().toDate())));
            assertThat(draftPackage.getUpdatedDate(), is(lessThan(new DateTime(endDate).plusDays(1).withTimeAtStartOfDay().toDate())));
        }
    }
}
