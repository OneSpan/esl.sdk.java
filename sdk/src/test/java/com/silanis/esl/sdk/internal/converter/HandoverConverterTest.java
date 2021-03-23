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
    private com.silanis.esl.api.model.Handover apiHandover1 = null;
    private com.silanis.esl.api.model.Handover apiHandover2 = null;
    private com.silanis.esl.sdk.Handover sdkHandover1 = null;
    private com.silanis.esl.sdk.Handover sdkHandover2 = null;
    private HandoverConverter converter = null;
    private Locale lang = Locale.ENGLISH;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkHandover1 = null;
        converter = new HandoverConverter(sdkHandover1);
        assertThat(converter.toAPIHandover(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiHandover1 = null;
        converter = new HandoverConverter(apiHandover1);
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
        apiHandover1 = null;
        converter = new HandoverConverter(apiHandover1);
        assertThat(converter.toAPIHandover(), nullValue());
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
        apiHandover1 = createTypicalAPILink();
        apiHandover2 = new HandoverConverter(apiHandover1).toAPIHandover();

        assertThat(apiHandover2, notNullValue());
        assertThat(apiHandover2.getHref(), is(apiHandover1.getHref()));
        assertThat(apiHandover2.getText(), is(apiHandover1.getText()));
        assertThat(apiHandover2.getTitle(), is(apiHandover1.getTitle()));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiHandover1 = createTypicalAPILink();
        sdkHandover1 = new HandoverConverter(apiHandover1).toSDKHandover(lang);

        assertThat(sdkHandover1, notNullValue());
        assertThat(sdkHandover1.getLanguage(), is(lang));
        assertThat(sdkHandover1.getHref(), is(apiHandover1.getHref()));
        assertThat(sdkHandover1.getText(), is(apiHandover1.getText()));
        assertThat(sdkHandover1.getTitle(), is(apiHandover1.getTitle()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkHandover1 = createTypicalSDKHandover();
        apiHandover1 = new HandoverConverter(sdkHandover1).toAPIHandover();

        assertThat(apiHandover1, notNullValue());
        assertThat(apiHandover1.getHref(), is(sdkHandover1.getHref()));
        assertThat(apiHandover1.getText(), is(sdkHandover1.getText()));
        assertThat(apiHandover1.getTitle(), is(sdkHandover1.getTitle()));
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