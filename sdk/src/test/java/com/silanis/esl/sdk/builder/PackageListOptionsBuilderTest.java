package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.PackageListOptions;
import com.silanis.esl.sdk.Visibility;
import org.junit.Test;

import java.util.Date;

import static com.silanis.esl.sdk.builder.PackageListOptionsBuilder.newPackageListOptions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PackageListOptionsBuilderTest {

    @Test
    public void buildWithoutValuesLeavesEverythingUnset() {
        PackageListOptions options = newPackageListOptions().build();

        assertFalse(options.isTemplates());
        assertNull(options.getStatus());
        assertNull(options.getStartDate());
        assertNull(options.getUseTransactionTimezone());
    }

    @Test
    public void buildWithSpecifiedValues() {
        Date start = new Date(0);
        Date end = new Date();

        PackageListOptions options = newPackageListOptions()
                .forTemplates()
                .withStatus("inbox")
                .withVisibility(Visibility.ACCOUNT)
                .withDateRange(start, end)
                .withTransactionTimezone()
                .build();

        assertTrue(options.isTemplates());
        assertEquals("inbox", options.getStatus());
        assertEquals(Visibility.ACCOUNT, options.getVisibility());
        assertEquals(start, options.getStartDate());
        assertEquals(end, options.getEndDate());
        assertTrue(options.getUseTransactionTimezone());
    }
}
