package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZoneOffset;

import static com.silanis.esl.sdk.examples.GetPackageInTransactionTimezoneExample.PACKAGE_TIMEZONE_ID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class GetPackageInTransactionTimezoneExampleTest {
    @Test
    public void verifyResult() {
        GetPackageInTransactionTimezoneExample example = new GetPackageInTransactionTimezoneExample();
        example.run();

        DocumentPackage packageInUtc = example.getPackageInUtc();
        DocumentPackage packageInTransactionTimezone = example.getPackageInTransactionTimezone();
        DocumentPackage listedPackage = example.getListedPackageInTransactionTimezone();

        // Without the option, only the UTC expiry date is returned.
        assertThat(packageInUtc.getExpiryDateWithOffset(), is(nullValue()));

        // With the option, the expiry date carries the offset of the transaction time zone,
        // and still represents the same moment.
        ZoneOffset expectedOffset = ZoneId.of(PACKAGE_TIMEZONE_ID).getRules()
                .getOffset(packageInUtc.getExpiryDate().toInstant());
        assertThat(packageInTransactionTimezone.getExpiryDateWithOffset().getOffset(), is(expectedOffset));
        assertThat(packageInTransactionTimezone.getExpiryDateWithOffset().toInstant(), is(packageInUtc.getExpiryDate().toInstant()));

        // The same applies to a page of packages.
        assertThat(listedPackage, is(notNullValue()));
        if (listedPackage.getExpiryDateWithOffset() != null) {
            assertThat(listedPackage.getExpiryDateWithOffset().getOffset(), is(expectedOffset));
        }
    }
}
