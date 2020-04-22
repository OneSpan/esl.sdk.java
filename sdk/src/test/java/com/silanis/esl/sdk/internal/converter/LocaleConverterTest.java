package com.silanis.esl.sdk.internal.converter;

import org.junit.Test;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by schoi on 2020-04-02.
 */
public class LocaleConverterTest {

    @Test
    public void convertNullToLocale() {
        Locale locale = LocaleConverter.convertToLocale(null);
        assertThat(locale, notNullValue());
        assertThat(locale.getLanguage(), is(EMPTY));
    }

    @Test
    public void convertNullToString() {
        String lang = LocaleConverter.convertToString(null);
        assertThat(lang, nullValue());
    }

    @Test
    public void convertLocaleToString1() {
        String lang = LocaleConverter.convertToString(Locale.KOREAN);
        assertThat(lang, notNullValue());
        assertThat(lang, is("ko"));
    }

    @Test
    public void convertLocaleToString2() {
        String lang = LocaleConverter.convertToString(Locale.SIMPLIFIED_CHINESE);
        assertThat(lang, notNullValue());
        assertThat(lang, is("zh-CN"));
    }

    @Test
    public void convertStringToLocale1() {
        Locale locale = LocaleConverter.convertToLocale("fr");
        assertThat(locale, notNullValue());
        assertThat(locale, is(Locale.FRENCH));
    }

    @Test
    public void convertStringToLocale2() {
        Locale locale = LocaleConverter.convertToLocale("zh-TW");
        assertThat(locale, notNullValue());
        assertThat(locale, is(Locale.TRADITIONAL_CHINESE));
    }
}