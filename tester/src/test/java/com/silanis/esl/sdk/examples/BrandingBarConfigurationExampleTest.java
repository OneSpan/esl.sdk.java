package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.DocumentPackage;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

import static org.hamcrest.MatcherAssert.assertThat;

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
    
        assertThat( "Opt out button was not set correctly.", documentPackage.getSettings().getEnableOptOut(), is( false ) );
        assertThat( "Tool bar download button was not set correctly.", documentPackage.getSettings().getShowDocumentToolbarDownloadButton(), is( false ) );
        assertThat( "Global navigation button was not set correctly.", documentPackage.getSettings().getCeremonyLayoutSettings().getGlobalNavigation(), is( false ) );
        assertThat( "Global download button was not set correctly.", documentPackage.getSettings().getCeremonyLayoutSettings().getShowGlobalDownloadButton(), is( false ) );
    }
    
}
