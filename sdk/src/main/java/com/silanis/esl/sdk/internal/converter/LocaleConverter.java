package com.silanis.esl.sdk.internal.converter;

import org.apache.commons.lang3.LocaleUtils;

import java.util.Locale;

import static org.apache.commons.lang3.StringUtils.*;

/**
 * Created by schoi on 2020-04-01.
 */
public class LocaleConverter {

    public static Locale convertToLocale(String lang) {
        if (isBlank(lang))
            return new Locale(EMPTY);

        return LocaleUtils.toLocale(lang.replaceAll("-", "_"));
    }

    public static String convertToString(Locale locale) {
        if (locale == null) {
            return null;
        }
        String languageCountry = locale.getCountry();
        return locale.getLanguage() + (isNotBlank(languageCountry) ? "-" + languageCountry : EMPTY);
    }

}