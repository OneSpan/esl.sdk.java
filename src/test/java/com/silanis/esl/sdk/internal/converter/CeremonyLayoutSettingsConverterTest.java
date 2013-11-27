package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.LayoutOptions;
import org.hamcrest.core.IsEqual;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * User: jessica
 * Date: 26/11/13
 * Time: 11:10 AM
 *
 * Test CeremonyLayoutSettingsConverter.
 *
 */
public class CeremonyLayoutSettingsConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.CeremonyLayoutSettings sdkCeremonyLayoutSettings1 = null;
    private com.silanis.esl.sdk.CeremonyLayoutSettings sdkCeremonyLayoutSettings2 = null;
    private com.silanis.esl.api.model.LayoutOptions apiLayoutOptions1 = null;
    private com.silanis.esl.api.model.LayoutOptions apiLayoutOptions2 = null;
    private CeremonyLayoutSettingsConverter converter = null;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkCeremonyLayoutSettings1 = null;
        converter = new CeremonyLayoutSettingsConverter(sdkCeremonyLayoutSettings1);
        assertThat( "Converter didn't return a null api object for a null sdk object", converter.toAPILayoutOptions(), is( nullValue() ) );
    }

    @Override
    public void convertNullAPIToSDK() {
        // Not applicable.
    }

    @Override
    public void convertNullSDKToSDK() {
        // Not applicable.
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiLayoutOptions1 = null;
        converter = new CeremonyLayoutSettingsConverter(apiLayoutOptions1);
        assertThat( "Converter didn't return a null api object for a null api object", converter.toAPILayoutOptions(), is( nullValue() ) );
    }

    @Override
    public void convertSDKToSDK() {
        // Not applicable.
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiLayoutOptions1 = createTypicalAPICeremonyLayoutSettings();
        apiLayoutOptions2 = new CeremonyLayoutSettingsConverter(apiLayoutOptions1).toAPILayoutOptions();
        assertThat( "Converter returned a null api object for a non null api object", apiLayoutOptions2, is( notNullValue() ) );
        assertThat( "Converter didn't return the same non-null api object it was given", apiLayoutOptions2, is( IsEqual.equalTo(apiLayoutOptions1) ) );
    }

    @Override
    public void convertAPIToSDK() {
        // Not applicable.
    }

    @Override
    public void convertSDKToAPI() {
        sdkCeremonyLayoutSettings1 = createTypicalSDKCeremonyLayoutSettings();
        apiLayoutOptions2 = new CeremonyLayoutSettingsConverter(sdkCeremonyLayoutSettings1).toAPILayoutOptions();
        assertThat( "", apiLayoutOptions2.getBrandingBar().getLogo().getLink(), is( equalTo( sdkCeremonyLayoutSettings1.getLogoImageLink() ) ) );
    }

    /**
     * Create a typical API CeremonyLayoutSettings
     *
     * @return CeremonyLayoutSettings
     */
    private com.silanis.esl.sdk.CeremonyLayoutSettings createTypicalSDKCeremonyLayoutSettings() {

        com.silanis.esl.sdk.CeremonyLayoutSettings settings = new com.silanis.esl.sdk.CeremonyLayoutSettings();
        settings.setLogoImageLink( "logoImageLink" );
        settings.setLogoImageSource( "logoImageSource" );
        settings.setiFrame( true );
        settings.setShowTitle( true );
        settings.setSessionBar( true );
        settings.setProgressBar( true );
        settings.setBreadCrumbs( true );
        settings.setGlobalNavigation( true );
        settings.setShowGlobalConfirmButton( true );
        settings.setShowGlobalDownloadButton( true );
        settings.setShowGlobalSaveAsLayoutButton( true );

        return settings;

    }

    private com.silanis.esl.api.model.LayoutOptions createTypicalAPICeremonyLayoutSettings() {
        com.silanis.esl.api.model.LayoutOptions layoutOptions = new LayoutOptions();
        return layoutOptions;
    }

}
