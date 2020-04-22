package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.api.model.Link;
import com.silanis.esl.sdk.Handover;
import com.silanis.esl.sdk.builder.HandoverBuilder;
import org.junit.Test;

import java.util.Locale;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 2020-04-02.
 */
public class HandoverConverterTest implements ConverterTest {
    private Link apiLink1 = null;
    private Link apiLink2 = null;
    private Handover sdkHandover1 = null;
    private Handover sdkHandover2 = null;
    private HandoverConverter converter = null;
    private Locale lang = Locale.ENGLISH;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkHandover1 = null;
        converter = new HandoverConverter(sdkHandover1);
        assertThat(converter.toAPILink(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiLink1 = null;
        converter = new HandoverConverter(apiLink1);
        assertThat(converter.toSDKHandover(Locale.KOREAN), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkHandover1 = null;
        converter = new HandoverConverter(sdkHandover1);
        assertThat(converter.toSDKHandover(Locale.CHINESE), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiLink1 = null;
        converter = new HandoverConverter(apiLink1);
        assertThat(converter.toAPILink(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkHandover1 = createTypicalSDKHandover();
        sdkHandover2 = new HandoverConverter(sdkHandover1).toSDKHandover(lang);

        assertThat(sdkHandover2, notNullValue());
        assertThat(sdkHandover2.getHref(), is(sdkHandover1.getHref()));
        assertThat(sdkHandover2.getText(), is(sdkHandover1.getText()));
        assertThat(sdkHandover2.getTitle(), is(sdkHandover1.getTitle()));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiLink1 = createTypicalAPILink();
        apiLink2 = new HandoverConverter(apiLink1).toAPILink();

        assertThat(apiLink2, notNullValue());
        assertThat(apiLink2.getHref(), is(apiLink1.getHref()));
        assertThat(apiLink2.getText(), is(apiLink1.getText()));
        assertThat(apiLink2.getTitle(), is(apiLink1.getTitle()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiLink1 = createTypicalAPILink();
        sdkHandover1 = new HandoverConverter(apiLink1).toSDKHandover(lang);

        assertThat(sdkHandover1, notNullValue());
        assertThat(sdkHandover1.getLanguage(), is(lang));
        assertThat(sdkHandover1.getHref(), is(apiLink1.getHref()));
        assertThat(sdkHandover1.getText(), is(apiLink1.getText()));
        assertThat(sdkHandover1.getTitle(), is(apiLink1.getTitle()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkHandover1 = createTypicalSDKHandover();
        apiLink1 = new HandoverConverter(sdkHandover1).toAPILink();

        assertThat(apiLink1, notNullValue());
        assertThat(apiLink1.getHref(), is(sdkHandover1.getHref()));
        assertThat(apiLink1.getText(), is(sdkHandover1.getText()));
        assertThat(apiLink1.getTitle(), is(sdkHandover1.getTitle()));
    }

    private Handover createTypicalSDKHandover() {
        return HandoverBuilder.newHandover(lang)
                .withHref("sdkHref")
                .withTitle("sdkTitle")
                .withText("sdkText")
                .build();
    }

    private Link createTypicalAPILink() {
        Link link = new Link();
        link.setHref("apiHref");
        link.setTitle("apiTitle");
        link.setText("apiText");
        return link;
    }
}