package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackageRequestExtension;
import com.silanis.esl.sdk.PackageRetrievalOptions;
import org.junit.Test;

import java.util.Collections;

import static com.silanis.esl.sdk.builder.PackageRetrievalOptionsBuilder.newPackageRetrievalOptions;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PackageRetrievalOptionsBuilderTest {

    @Test
    public void buildWithoutValuesLeavesEverythingUnset() {
        PackageRetrievalOptions options = newPackageRetrievalOptions().build();

        assertNull(options.getRawSignatureCapture());
        assertTrue(options.getExtensions().isEmpty());
        assertNull(options.getUseTransactionTimezone());
    }

    @Test
    public void buildWithSpecifiedValues() {
        PackageRetrievalOptions options = newPackageRetrievalOptions()
                .withRawSignatureCapture()
                .withExtensions(DocumentPackageRequestExtension.ALERTS)
                .withTransactionTimezone()
                .build();

        assertTrue(options.getRawSignatureCapture());
        assertEquals(Collections.singleton(DocumentPackageRequestExtension.ALERTS), options.getExtensions());
        assertTrue(options.getUseTransactionTimezone());
    }
}
