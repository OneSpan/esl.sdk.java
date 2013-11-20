package com.silanis.esl.sdk;

import com.silanis.esl.api.model.LayoutOptions;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CeremonyLayoutSettingsTest {

    private CeremonyLayoutSettings settings;

    @Before
    public void before() {
        settings = new CeremonyLayoutSettings();
    }

    @Test
    public void setiFrame() {
        settings.setiFrame( true );
        assertThat( "iFrame wasn't set to true as expected", settings.getiFrame(), is( equalTo( true ) ) );
        settings.setiFrame( false );
        assertThat( "iFrame wasn't set to false as expected", settings.getiFrame(), is( equalTo( false ) ) );
    }

    @Test
    public void setBreadCrumbs() {
        settings.setBreadCrumbs( true );
        assertThat( "breadCrumbs wasn't set to true as expected", settings.getBreadCrumbs(), is( equalTo( true ) ) );
        settings.setBreadCrumbs( false );
        assertThat( "breadCrumbs wasn't set to false as expected", settings.getBreadCrumbs(), is( equalTo( false ) ) );
    }

    @Test
    public void setSessionBar() {
        settings.setSessionBar( true );
        assertThat( "SessionBar wasn't set to true as expected", settings.getSessionBar(), is( equalTo( true ) ) );
        settings.setSessionBar( false );
        assertThat( "SessionBar wasn't set to false as expected", settings.getSessionBar(), is( equalTo( false ) ) );
    }

    @Test
    public void setGlobalNavigation() {
        settings.setGlobalNavigation( true );
        assertThat( "GlobalNavigation wasn't set to true as expected", settings.getGlobalNavigation(), is( equalTo( true ) ) );
        settings.setGlobalNavigation( false );
        assertThat( "GlobalNavigation wasn't set to false as expected", settings.getGlobalNavigation(), is( equalTo( false ) ) );
    }

    @Test
    public void setProgressBar() {
        settings.setProgressBar( true );
        assertThat( "ProgressBar wasn't set to true as expected", settings.getProgressBar(), is( equalTo( true ) ) );
        settings.setProgressBar( false );
        assertThat( "ProgressBar wasn't set to false as expected", settings.getProgressBar(), is( equalTo( false ) ) );
    }

    @Test
    public void setShowTitle() {
        settings.setShowTitle( true );
        assertThat( "ShowTitle wasn't set to true as expected", settings.getShowTitle(), is( equalTo( true ) ) );
        settings.setShowTitle( false );
        assertThat( "ShowTitle wasn't set to false as expected", settings.getShowTitle(), is( equalTo( false ) ) );
    }

    @Test
    public void setNavigator() {
        settings.setNavigator( true );
        assertThat( "Navigator wasn't set to true as expected", settings.getNavigator(), is( equalTo( true ) ) );
        settings.setNavigator( false );
        assertThat( "Navigator wasn't set to false as expected", settings.getNavigator(), is( equalTo( false ) ) );
    }

    @Test
    public void setShowGlobalConfirmButton() {
        settings.setShowGlobalConfirmButton( true );
        assertThat( "ShowGlobalConfirmButton wasn't set to true as expected", settings.getShowGlobalConfirmButton(), is( equalTo( true ) ) );
        settings.setShowGlobalConfirmButton( false );
        assertThat( "ShowGlobalConfirmButton wasn't set to false as expected", settings.getShowGlobalConfirmButton(), is( equalTo( false ) ) );
    }

    @Test
    public void setShowGlobalDownloadButton() {
        settings.setShowGlobalDownloadButton( true );
        assertThat( "ShowGlobalDownloadButton wasn't set to true as expected", settings.getShowGlobalDownloadButton(), is( equalTo( true ) ) );
        settings.setShowGlobalDownloadButton( false );
        assertThat( "ShowGlobalDownloadButton wasn't set to false as expected", settings.getShowGlobalDownloadButton(), is( equalTo( false ) ) );
    }

    @Test
    public void setShowSaveAsLayoutButton() {
        settings.setShowGlobalSaveAsLayoutButton( true );
        assertThat( "ShowGlobalSaveAsLayoutButton wasn't set to true as expected", settings.getShowGlobalSaveAsLayoutButton(), is( equalTo( true ) ) );
        settings.setShowGlobalSaveAsLayoutButton( false );
        assertThat( "ShowGlobalSaveAsLayoutButton wasn't set to false as expected", settings.getShowGlobalSaveAsLayoutButton(), is( equalTo( false ) ) );
    }

    @Test
    public void setLogoImageSource() {
        String value = "logoImageSource";
        settings.setLogoImageSource( value );
        assertThat( "SetLogoImageSource wasn't set to the expected value", settings.getLogoImageSource(), is( equalTo( value ) ) );
    }

    @Test
    public void setLogoImageLink() {
        String value = "logoImageLink";
        settings.setLogoImageLink( value );
        assertThat( "SetLogoImageLink wasn't set to the expected value", settings.getLogoImageLink(), is( equalTo( value ) ) );
    }

    @Test
    public void toAPILayoutOptions() {
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
        LayoutOptions layoutOptions = settings.toAPILayoutOptions();
        assertThat( "", layoutOptions.getBrandingBar().getLogo().getLink(), is( equalTo( settings.getLogoImageLink() ) ) );
    }

}
