package com.silanis.esl.sdk.internal;

import com.google.common.net.PercentEscaper;

import java.net.URLDecoder;

/**
 * Created by schoi on 8/19/15.
 */
public class HttpUtil {
    private static final PercentEscaper percentEncoder = new PercentEscaper("-._~", false);

    public static String percentEncode(String s) {
        if (s == null) {
            return "";
        }
        return percentEncoder.escape(s);
    }

    public static String percentDecode(String s) {
        try {
            if (s == null) {
                return "";
            }
            return URLDecoder.decode(s, "UTF-8");
            // This implements http://oauth.pbwiki.com/FlexibleDecoding
        } catch (java.io.UnsupportedEncodingException wow) {
            throw new RuntimeException(wow.getMessage(), wow);
        }
    }
}
