package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

/**
 * User: jessica
 * Date: 12/12/13
 * Time: 4:54 PM
 * 
 * Test BrandingBarConfigurationExample.
 * 
 */
public class BrandingBarConfigurationExampleTest {

    @Test
    public void verifyResult() {
    
        // Verify if the branding bar configuration was set up correctly.
        BrandingBarConfigurationExample brandingBarConfigurationExample = new BrandingBarConfigurationExample( Props.get() );
        brandingBarConfigurationExample.run();

        DocumentPackage documentPackage = brandingBarConfigurationExample.getRetrievedPackage();
    
        assertFalse("Opt out button was not set correctly.", documentPackage.getSettings().getEnableOptOut());
        assertFalse("Tool bar download button was not set correctly.", documentPackage.getSettings().getShowDocumentToolbarDownloadButton());
        assertFalse("Global navigation button was not set correctly.", documentPackage.getSettings().getCeremonyLayoutSettings().getGlobalNavigation());
        assertFalse("Global download button was not set correctly.", documentPackage.getSettings().getCeremonyLayoutSettings().getShowGlobalDownloadButton());
    }
    
}
